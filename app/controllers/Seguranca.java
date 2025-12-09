package controllers;

import model.Perfil;
import notes.Administrador;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {

	@Before(unless = {"Usuarios.form", "Login.form", "Usuarios.salvar" })
	static void verificar() {
		if (!session.contains("usuarioPerfil")) {
			flash.error("Você precisa fazer o login");
			Login.form();
		}
	}

	@Before
	static void verificarAdministrador() {
		String perfil = session.get("usuarioPerfil");
		Administrador adminAnnotation = getActionAnnotation(Administrador.class);

		if (adminAnnotation != null && !Perfil.ADMINISTRADOR.name().equals(perfil)) {
			forbidden("Acesso restrito aos administradores do sistema");
		}
	}

}
