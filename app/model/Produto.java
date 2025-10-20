package model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Produto extends Model {

	public String nome;
	public int preco;
	public String tamanho;
	public String descricao;
//	public int quantidade;
	
	// Relacionamento produto --> categoria
	@ManyToOne
	public Categoria categoria;

	// produto ativo e inativo
	@Enumerated(EnumType.STRING)
	public Status status;

	//	construtor que inicializa o produto como ativo
	public Produto() {
		this.status = Status.ATIVO;
	}

	public Produto(String nome, int preco, Categoria categoria) {
		this.nome = nome;
		this.preco = preco;
		this.categoria = categoria;
	}

}
