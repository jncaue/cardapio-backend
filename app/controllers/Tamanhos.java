package controllers;

import java.util.List;

import model.Tamanho;
import model.Tamanho;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)

public class Tamanhos extends Controller {

	public static void form() {
		List<Tamanho> listaDeTamanhos = Tamanho.findAll();
		render(listaDeTamanhos);
	}	
	
	public static void detalhar(Tamanho tamanho) {
		render(tamanho);
	}

	public static void salvar(Tamanho tamanho) {
		flash.success(tamanho.nome + " foi cadastrada com sucesso.");
		tamanho.save();
	}

	public static void listar(String termo) {
		List<Tamanho> listaDeTamanhos = null;
		render(listaDeTamanhos);
	}

	public static void remover(Long id) {
		Tamanho tamanho = Tamanho.findById(id);
		tamanho.save();
		listar(null);
	}

	public static void editar(Long id) {
		Tamanho c = Tamanho.findById(id);
		List<Tamanho> listaDeTamanhos = Tamanho.findAll();
		renderTemplate("Produtos/form.html", c, listaDeTamanhos);
	}
}
