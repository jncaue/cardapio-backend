package controllers;

import java.time.LocalTime;

import java.util.*;
import model.ItemCarrinho;
import model.Pedido;
import model.Produto;
import notes.Administrador;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)

public class Pedidos extends Controller {

	@Administrador
    public static void listar() {
        List<Pedido> pedidos = Pedido.listarTodos();
        render(pedidos);
    }

    public static void limparPedidos() {
        List<Pedido> pedidos = Pedido.listarTodos();
        pedidos.clear();
        Pedidos.listar();
    }
}
	