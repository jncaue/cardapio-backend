package model;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Tamanho extends Model {
	public String nome;

	public Tamanho(String nome) {
		this.nome = nome;
	}

}
