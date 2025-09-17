package model;

import javax.persistence.Entity;

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
}
