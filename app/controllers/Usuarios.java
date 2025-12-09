package controllers;

import java.util.List;

import model.Usuario;
import notes.Administrador;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Usuarios extends Controller {
	public static void form() {
		render();
	}

	public static void salvar(@Valid Usuario usuario) {

	    // Verificar matrícula repetida
	    Usuario existente = Usuario.find("matricula = ?1", usuario.matricula).first();

	    if (existente != null && (usuario.id == null || !existente.id.equals(usuario.id))) {
	        validation.addError("usuario.matricula", "Matrícula já cadastrada, escolha outra ou faça login");
	    }

	    // Se houver qualquer erro (inclusiva matrícula repetida)
	    if (validation.hasErrors()) {
	        params.flash();
	        validation.keep();
	        form();
	    }

	    usuario.save();
	    flash.success("Cadastrado com sucesso!");
	    Login.form();
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
		List<Usuario> listaDeUsuarios = Usuario.findAll();
		render(listaDeUsuarios);
	}
}
