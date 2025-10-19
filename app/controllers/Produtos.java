package controllers;

import java.util.List;

import model.Categoria;
import model.Perfil;
import model.Produto;
import model.Status;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)

public class Produtos extends Controller {

	// Lista de categorias
	public static void form() {
		List<Categoria> listaDeCategoria = Categoria.findAll();
		render(listaDeCategoria);
	}

	public static void cardapio(String termo) {
		List<Categoria> listaDeCategoria = Categoria.findAll();
		List<Produto> listaDeProdutos;
		
		List<Produto> listaDeComidas = Produto.find("categoria.nome = ?1 and status = ?2", "Comida", Status.ATIVO).fetch();
		List<Produto> listaDeBebidas = Produto.find("categoria.nome = ?1 and status = ?2", "Bebida", Status.ATIVO).fetch();
		List<Produto> listaDeSobremesas = Produto.find("categoria.nome = ?1 and status = ?2", "Sobremesa", Status.ATIVO).fetch();

		if (termo == null) {
			listaDeProdutos = Produto.find("status is null or status <> ?1", Status.INATIVO).fetch();
		} else {
			listaDeProdutos = Produto.find("(lower(nome) like ?1 " + "or lower(categoria) like ?1) and status <> ?2",
					"%" + termo.toLowerCase() + "%", Status.INATIVO).fetch();
		}
		render(listaDeProdutos, listaDeCategoria, listaDeComidas, listaDeBebidas, listaDeSobremesas);
	}

	public static void detalhar(Produto produto) {
		render(produto);
	}

	public static void salvar(Produto produto) {
//		listar(null);

		flash.success(produto.nome + " foi cadastrada com sucesso.");
		produto.save();
		form();
	}

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

	public static void remover(Long id) {
		Produto produto = Produto.findById(id);
		produto.status = Status.INATIVO;
		flash.success(produto.nome + " foi removido com sucesso.");
		produto.save();
		listar(null);
	}

	public static void editar(Long id) {
		Produto p = Produto.findById(id);
		List<Categoria> listaDeCategoria = Categoria.findAll();
		
		renderTemplate("Produtos/form.html", p, listaDeCategoria);
	}
}
