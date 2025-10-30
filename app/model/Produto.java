package model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Produto extends Model {

	@Required(message = "required.nome")
	public String nome;

	@Required(message = "required.preco")
	public String preco;

	public String descricao;

	// Relacionamento varios produtos --> uma categoria
	@Required(message = "required.categoria")
	@ManyToOne
	public Categoria categoria;

	// Relacionamento varios produtos --> tamanho
	@Required(message = "required.tamanho")
	@ManyToOne
	public Tamanho tamanho;

	// produto ativo e inativo
	@Enumerated(EnumType.STRING)
	public Status status;

	// construtor que inicializa o produto como ativo
	public Produto() {
		this.status = Status.ATIVO;
	}

	public Produto(String nome, String preco, Categoria categoria, Tamanho tamanho, String descricao) {
		this.nome = nome;
		this.preco = preco;
		this.categoria = categoria;
		this.tamanho = tamanho;
		this.descricao = descricao;
	}

}
