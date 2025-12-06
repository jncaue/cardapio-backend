package controllers;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Categoria;
import model.ItemCarrinho;
import model.Perfil;
import model.Produto;
import model.Status;
import model.Tamanho;
import notes.Administrador;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)

public class Produtos extends Controller {

	@Administrador
	public static void form() {
		List<Categoria> listaDeCategorias = Categoria.findAll();
		List<Tamanho> listaDeTamanhos = Tamanho.findAll();
		render(listaDeCategorias, listaDeTamanhos);
	}

	public static void cardapio(String termo) {
		List<Categoria> listaDeCategorias = Categoria.findAll();
		List<Produto> listaDeProdutos;

		List<Produto> listaDeBebidas = Produto.find("categoria.nome = ?1 and status = ?2", "Bebida", Status.ATIVO)
				.fetch();
		List<Produto> listaDeSobremesas = Produto.find("categoria.nome = ?1 and status = ?2", "Sobremesa", Status.ATIVO)
				.fetch();

		List<Produto> listaDePizzas = Produto.find("categoria.nome = ?1 and status = ?2", "Pizza", Status.ATIVO)
				.fetch();
		List<Produto> listaDeHamburguers = Produto
				.find("categoria.nome = ?1 and status = ?2", "Hamburguer", Status.ATIVO).fetch();
		List<Produto> listaDeCalzones = Produto.find("categoria.nome = ?1 and status = ?2", "Calzone", Status.ATIVO)
				.fetch();

		if (termo == null) {
			listaDeProdutos = Produto.find("status is null or status <> 	?1", Status.INATIVO).fetch();
		} else {
			listaDeProdutos = Produto.find("(lower(nome) like ?1 " + "or lower(categoria) like ?1) and status <> ?2",
					"%" + termo.toLowerCase() + "%", Status.INATIVO).fetch();
		}
		render(listaDeProdutos, listaDeCategorias, listaDeBebidas, listaDeSobremesas, listaDePizzas, listaDeHamburguers,
				listaDeCalzones);
	}

	public static void salvar(@Valid Produto produto) {
		if (validation.hasErrors()) {
			params.flash();
			validation.keep();
			form();
		} else {
			flash.success(produto.nome + " foi cadastrada com sucesso.");
			produto.save();
			form();
		}
	}

	@Administrador
	public static void listar(String termo) {
		List<Produto> listaDeProdutos = null;

		if (termo == null) {
			listaDeProdutos = Produto.find("status <> ?1", Status.INATIVO).fetch();
		} else {
			listaDeProdutos = Produto.find("(lower(nome) like ?1 " + "or lower(categoria) like ?1) and status <> ?2",
					"%" + termo.toLowerCase() + "%", Status.INATIVO).fetch();
		}
		render(listaDeProdutos, termo);
	}

	public static void detalhar(Long id) {
		if (id == null) {
			error("ID inválido.");
		}

		Produto produto = Produto.findById(id);

		if (produto == null) {
			notFound("Produto não encontrado.");
		}

		// --- MONTAR LISTA DO CARRINHO ---
		String carrinhoStr = session.get("carrinho");
		List<Produto> itensCarrinho = new ArrayList<>();

		if (carrinhoStr != null && !carrinhoStr.isEmpty()) {
			for (String s : carrinhoStr.split(",")) {
				Produto p = Produto.findById(Long.parseLong(s));
				if (p != null)
					itensCarrinho.add(p);
			}
		}

		// envia produto E itensCarrinho para o HTML
		render(produto, itensCarrinho);
	}
	
	public static void adicionarItem(Long id) {
		if (id == null) {
			error("ID inválido.");
		}

		// busca o produto no banco
		Produto produto = Produto.findById(id);
		if (produto == null) {
			notFound("Produto não encontrado.");
		}

		// recuperar carrinho (string)
		String carrinhoStr = session.get("carrinho");

		// converter para lista
		List<Long> carrinho = new ArrayList<>();

		if (carrinhoStr != null && !carrinhoStr.isEmpty()) {
			for (String s : carrinhoStr.split(",")) {
				carrinho.add(Long.parseLong(s));
			}
		}

		// adicionar item
		carrinho.add(produto.id);

		// converter de volta para string
		session.put("carrinho", listaParaString(carrinho));

		flash.success(produto.nome + " adicionado ao carrinho.");

//		detalhar(produto.id);
		cardapio(null);
	}


	public static void verCarrinho() {
	    String carrinhoStr = session.get("carrinho");

	    Map<Long, Integer> mapa = new HashMap<>();
	    Map<Long, Produto> produtosMap = new HashMap<>();

	    double total = 0.0;

	    if (carrinhoStr != null && !carrinhoStr.trim().isEmpty()) {

	        for (String s : carrinhoStr.split(",")) {
	            if (s == null || s.trim().isEmpty()) continue;

	            Long id = Long.parseLong(s.trim());
	            Produto p = Produto.findById(id);

	            
	            if (p != null) {
	                mapa.put(id, mapa.getOrDefault(id, 0) + 1);
	                produtosMap.put(id, p);
	                total += Double.parseDouble(p.preco);
	                
	                
	            }
	        }
	    }

	    // Transformar em lista de ItemCarrinho
	    List<ItemCarrinho> itensAgrupados = new ArrayList<>();
	    for (Long id : mapa.keySet()) {
	        itensAgrupados.add(new ItemCarrinho(produtosMap.get(id), mapa.get(id)));
	    }

	    render(itensAgrupados, total);
	}

	public static void removerItemCarrinho(Long id) {
		if (id == null) {
			error("ID inválido.");
		}

		// recuperar carrinho
		String carrinhoStr = session.get("carrinho");
		if (carrinhoStr == null || carrinhoStr.isEmpty()) {
			flash.error("Carrinho vazio.");
			verCarrinho();
		}

		// transformar em lista
		List<Long> carrinho = new ArrayList<>();
		for (String s : carrinhoStr.split(",")) {
			carrinho.add(Long.parseLong(s));
		}

		// remover apenas UMA ocorrência
		boolean removed = carrinho.remove(id);

		if (!removed) {
			flash.error("Item não estava no carrinho.");
		} else {
			flash.success("Item removido.");
		}

		// salvar novamente na sessão
		session.put("carrinho", listaParaString(carrinho));

		verCarrinho();
	}
	
	public static void limparCarrinho() {
	    session.remove("carrinho");
	    flash.success("Carrinho esvaziado.");
	    verCarrinho();
	}

	public static String listaParaString(List<Long> lista) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lista.size(); i++) {
			sb.append(lista.get(i));
			if (i < lista.size() - 1)
				sb.append(",");
		}
		return sb.toString();
	}

	public static void remover(Long id) {
		Produto produto = Produto.findById(id);
		produto.status = Status.INATIVO;
		flash.success(produto.nome + " foi removido com sucesso.");
		produto.save();
		listar(null);
	}

	@Administrador
	public static void editar(Long id) {
		Produto p = Produto.findById(id);
		List<Categoria> listaDeCategorias = Categoria.findAll();
		List<Tamanho> listaDeTamanhos = Tamanho.findAll();
		renderTemplate("Produtos/form.html", p, listaDeCategorias, listaDeTamanhos);
	}

	public static void statusH() {
		LocalTime agora = LocalTime.now();

		// horário de funcionamento: das 19h até 22h
		LocalTime inicio = LocalTime.of(19, 0);
		LocalTime fim = LocalTime.of(22, 0);

		String status = (agora.isAfter(inicio) && agora.isBefore(fim)) ? "Aberto" : "Fechado";

		return;
	}

}
