package model;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class ItemCarrinho extends Model {
    public Produto produto;
    public int quantidade;

    public ItemCarrinho(Produto p, int q) {
        this.produto = p;
        this.quantidade = q;
    }
}