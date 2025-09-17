package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Segurancas extends Controller{
	
	@Before(unless={"Usuarios.listar"})
	
	static void verificar(){
		
		if (session.contains("usuario.matricula") == false){
			Login.form();
		}
	}
	

}
