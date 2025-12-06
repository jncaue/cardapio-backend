package controllers;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
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

	public static void detalhar(Long id) {
	    if (id == null) {
	        error("ID inválido.");
	    }

	    Produto produto = Produto.findById(id);
	    if (produto == null) {
	        notFound("Produto não encontrado.");
	    }

	    render(produto);
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

	public static void adicionarItem(Long id) {
	    if(id == null) {
	        error("ID inválido.");
	    }

	    Produto produto = Produto.findById(id);
	    if(produto == null) {
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
	    
	    detalhar(produto.id);
	    cardapio(null);
	}
	
	public static void verCarrinho() {
	    String carrinhoStr = session.get("carrinho");
	    List<Produto> itens = new ArrayList<>();

	    if(carrinhoStr != null) {
	        for(String s : carrinhoStr.split(",")) {
	            Produto p = Produto.findById(Long.parseLong(s));
	            if(p != null) itens.add(p);
	        }
	    }

	    render(itens);
	}


	private static String listaParaString(List<Long> lista) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < lista.size(); i++) {
	        sb.append(lista.get(i));
	        if (i < lista.size() - 1) sb.append(",");
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
