package model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model {

	@Required (message = "required.matricula")
	public String matricula;

	@Required (message = "required.nome")
	public String nome;

	@MinSize(4)
	@Required (message = "required.senha")
	public String senha;
	
	@Enumerated(EnumType.STRING)
	public Perfil perfil;

	public void Gerasenha(String s) {
		senha = Crypto.passwordHash(s);
	}

//	public Usuario usuario;

	public Usuario(String nome, String matricula, String senha) {
		this.nome = nome;
		this.matricula = matricula;
		this.senha = senha;
	}


	public Usuario() {
		this.perfil = Perfil.CLIENTE;
	}
}
