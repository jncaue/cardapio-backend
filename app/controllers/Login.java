package controllers;

import model.Usuario;
import play.libs.Crypto;
import play.mvc.Controller;

public class Login extends Controller {
	
	public static void form(){
		render();
	}
	
	public static void logar(String matricula, String senha){
		
		Usuario usu = Usuario.find("matricula = ?1 and senha = ?2 ",
									matricula, Crypto.passwordHash(senha) ).first();
		
		if (usu == null){
			
			flash.error("Login ou senha inválidos");
			form();
			
		} else {
			session.put("usuario.matricula", usu.matricula);
          	session.put("usuarioPerfil", usu.perfil.name());
			flash.success("Logado com sucesso!");
			Produtos.home();
		}
	}
	
	public static void sair(){
		session.clear();
		Login.form();
	}
	
}
