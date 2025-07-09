package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Produto extends Model {

	public String nome;
	public int preco;

	@ManyToOne
	public Categoria categoria;

	public Produto(String nome, int preco, Categoria categoria) {
		this.nome = nome;
		this.preco = preco;
		this.categoria = categoria;
	}
	
	
}
