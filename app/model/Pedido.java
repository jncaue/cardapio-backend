package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    public static List<Pedido> pedidos = new ArrayList<>();

    public List<ItemCarrinho> itens;
    public double total;
    public String horario;

    public Pedido(List<ItemCarrinho> itens, double total, String horario) {
        this.itens = itens;
        this.total = total;
        this.horario = horario;
    }

    public static void registrar(Pedido p) {
        pedidos.add(p);
    }

    public static List<Pedido> listarTodos() {
        return pedidos;
    }
}
