package controllers;

import java.util.List;

import model.Categoria;
import model.Produto;
import model.Status;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Categorias extends Controller {

	public static void form() {
		List<Categoria> listaDeCategoria = Categoria.findAll();
		render(listaDeCategoria);
	}	
	
	public static void detalhar(Categoria categoria) {
		render(categoria);
	}

	public static void salvar(Categoria categoria) {
		flash.success(categoria.nome + " foi cadastrada com sucesso.");
		categoria.save();
	}

	public static void listar(String termo) {
		List<Categoria> listaDeCategorias = null;
		render(listaDeCategorias);
	}

	public static void remover(Long id) {
		Categoria categoria = Categoria.findById(id);
		categoria.save();
		listar(null);
	}

	public static void editar(Long id) {
		Categoria c = Categoria.findById(id);
		List<Categoria> listaDeCategoria = Categoria.findAll();
		renderTemplate("Produtos/form.html", c, listaDeCategoria);
	}
}
