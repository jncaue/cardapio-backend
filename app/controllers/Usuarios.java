package controllers;

import java.util.List;

import model.Usuario;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Usuarios extends Controller {
	public static void form() {
		render();
	}
	public static void salvar(@Valid Usuario usuario) {
		if (validation.hasErrors()) {
			params.flash();
			validation.keep();
			form();
		}
		usuario.save();
		Login.form();
		flash.success("Logado com sucesso!");
	}
	public static void editar(Long id) {
		Usuario usuario = Usuario.findById(id);
		renderTemplate("Usuarios/form.html", usuario);
	}
	public static void remover(Long id) {
		Usuario usuario = Usuario.findById(id);
		usuario.delete();
		listar();
	}
	public static void listar() {
		List<Usuario> usuarios = Usuario.findAll();
		render(usuarios);
	}
}

