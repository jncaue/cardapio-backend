package model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model{
	
	public String nome;
	public String matricula;
	public String senha;
	
	public void setSenha(String s){
		senha = Crypto.passwordHash(s);
	}
	
	public Usuario (String nome, String matricula, String senha) {
		this.nome = nome;
		this.matricula = matricula;
		this.senha = senha;
	}
	
	@Enumerated(EnumType.STRING)
	public Perfil perfil;
	
	
	public Usuario (){
		
		this.perfil = Perfil.CLIENTE;
		
	}
}
