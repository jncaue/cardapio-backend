package controllers;

import java.util.List;

import model.Perfil;
import model.Produto;
import model.Usuario;
import play.libs.Crypto;
import play.mvc.Controller;

public class Login extends Controller {

	public static void form() {
		render();
	}

	public static void logar(String matricula, String senha) {
		Usuario usu = Usuario.find("matricula = ?1 and senha = ?2 ", matricula, senha).first();
//		Usuario usu = Usuario.find("matricula = ?1 and senha = ?2 ", matricula, Crypto.passwordHash(senha)).first();

		if (usu == null) {
			flash.error("Matrícula ou senha inválidos!");
			form();
		} else {
//			local onde os atributos de usuario logado estão sendo alocados dentro de uma sessao
//			não se recomenda salvar senha, ainda mais em texto puro (sem criptografia)
//			a sessao so salva String, não salva objetos

			session.put("usuario.matricula", usu.matricula);
			session.put("usuario.nome", usu.nome);
			session.put("usuarioPerfil", usu.perfil.name());

			if (session.get("usuarioPerfil").equals(Perfil.ADMINISTRADOR.name())) {
				session.put("admin", usu.perfil.name());
			}

			flash.success("Logado com sucesso. " + "Bem vindo, " + usu.nome + "!");
			redirect("Produtos.cardapio");

		}
	}

	public static void sair() {
		session.clear();
		flash.success("Você saiu do sistema!");
		Login.form();
	}

}