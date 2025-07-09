package model;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Categoria extends Model{
	public String nome;
	public Integer ramal;
	
	public Categoria(String nome, Integer ramal) {
		this.nome = nome;
		this.ramal = ramal;
	}

	
}
