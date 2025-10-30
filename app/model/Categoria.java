package model;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Categoria extends Model {
	public String nome;

//	caso eu queira ter uma lista de produtos que esteja dentro de uma categoria X, entao eu crio um atributo de lista
//	@OneToMany
//	list<Produto> listaDeProdutos;
	
	
	
	public Categoria(String nome) {
		this.nome = nome;
	}

}
