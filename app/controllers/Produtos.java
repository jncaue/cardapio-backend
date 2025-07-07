package controllers;

import java.util.List;

import model.Produto;
import play.mvc.Controller;

public class Produtos extends Controller{

	public static void form() {
		render();
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
}
