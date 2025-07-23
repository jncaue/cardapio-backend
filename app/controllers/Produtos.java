package controllers;

import java.util.List;

import model.Categoria;
import model.Produto;
import play.mvc.Controller;

public class Produtos extends Controller{

	
	//Lista de categorias
	public static void form() {
		List<Categoria> listaDeCategoria = Categoria.findAll();
		render(listaDeCategoria);
		
	}
	
	public static void detalhar(Produto produto) {
		render(produto);
	}
	
	public static void salvar(Produto produto) {
		produto.save();
		listar();	
	}
	
	public static void listar() {
		List<Produto> listaDeProdutos = Produto.findAll();
		render(listaDeProdutos);
	}
	
	public static void remover(Long id) {
		Produto produto = Produto.findById(id);
		produto.delete();
		listar();
	}
	
	public static void editar(Long id) {
		Produto p = Produto.findById(id);
		List<Categoria> listaDeCategoria = Categoria.findAll();
		
		
		renderTemplate("Produtos/form.html", p, listaDeCategoria);
	}
}
