package model;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Categoria extends Model {
	public String nome;

	public Categoria(String nome) {
		this.nome = nome;
	}

}
