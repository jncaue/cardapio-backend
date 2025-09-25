package controllers;

import java.util.List;

import model.Usuario;
import play.mvc.Controller;

public class Usuarios extends Controller{
	
		
		public static void form() {
			render();
		}
		
		
		public static void salvar(Usuario usuario, String senha) {
			
			if (senha.equals("") == false){
				usuario.senha = senha;
			}
			
			usuario.save();
			listar();
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



