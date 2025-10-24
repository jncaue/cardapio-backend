package jobs;

import controllers.Tamanhos;
import model.Categoria;
import model.Perfil;
import model.Produto;
import model.Tamanho;
import model.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {

	@Override
	public void doJob() throws Exception {
			
//		CATEGORIAS DOS PRODUTOS
		if (Categoria.count() == 0) {

			Categoria pizza = new Categoria("Pizza");
			pizza.save();

			Categoria hamburguer = new Categoria("Hamburguer");
			hamburguer.save();
			
			Categoria calzone = new Categoria("Calzone");
			calzone.save();

			Categoria bebida = new Categoria("Bebida");
			bebida.save();

			Categoria sobremesa = new Categoria("Sobremesa");
			sobremesa.save();

			
//			TAMANHO DOS PRODUTOS
			if (Tamanho.count() == 0) {

//				TAMANHO PARA COMIDAS
				
				Tamanho p = new Tamanho("P");
				p.save();

				Tamanho m = new Tamanho("M");
				m.save();

				Tamanho g = new Tamanho("G");
				g.save();
				
//				TAMANHO PARA BEBIDAS
				Tamanho ml1 = new Tamanho("350ml");
				ml1.save();
				
				Tamanho ml2 = new Tamanho("500ml");
				ml2.save();

				Tamanho l1 = new Tamanho("1L");
				l1.save();

				Tamanho l2 = new Tamanho("2L");
				l2.save();

//				PRODUTOS
				if (Produto.count() == 0) {
					Produto pepsi = new Produto();
					pepsi.nome = "Pepsi";
					pepsi.preco = "12";
					pepsi.tamanho = m;
					pepsi.descricao = "Refrigerante";
					pepsi.categoria = bebida;
					pepsi.save();

					Produto açai = new Produto();
					açai.nome = "Açaí";
					açai.preco = "22";
					açai.tamanho = g;
					açai.descricao = "Açaí 500g, banana, leite em pó, granola";
					açai.categoria = sobremesa;
					açai.save();
					
					Produto cheddarbacon = new Produto();
					cheddarbacon.nome = "Cheddar bacon";
					cheddarbacon.preco = "44";
					cheddarbacon.tamanho = g;
					cheddarbacon.descricao = "cheddar, bacon, pao gergelim, pimenta";
					cheddarbacon.categoria = hamburguer;
					cheddarbacon.save();
					
					Produto calzonef = new Produto();
					calzonef.nome = "Frango com catupiry";
					calzonef.preco = "22";
					calzonef.tamanho = g;
					calzonef.descricao = "frango com catupiry, cebola crispy";
					calzonef.categoria = calzone;
					calzonef.save();
					
//					PIZZAS
					Produto pizzag = new Produto();
					pizzag.nome = "Pizza";
					pizzag.tamanho = g;
					pizzag.preco = "40";
					pizzag.descricao = "Pizza com até 2 sabores e 8 fatias";
					pizzag.categoria = pizza;
					pizzag.save();
					
					Produto pizzam = new Produto();
					pizzam.nome = "Pizza";
					pizzam.tamanho = m;
					pizzam.preco = "40";
					pizzam.descricao = "Pizza com até 2 sabores e 6 fatias";
					pizzam.categoria = pizza;
					pizzam.save();
					
					Produto pizzap = new Produto();
					pizzap.nome = "Pizza";
					pizzap.tamanho = m;
					pizzap.preco = "40";
					pizzap.descricao = "Pizza com até 2 sabores e 4 fatias";
					pizzap.categoria = pizza;
					pizzap.save();

//					USUARIOS
					if (Usuario.count() == 0) {

						Usuario Root = new Usuario("root", "1", "1111");
						Root.perfil = Perfil.ADMINISTRADOR;
						Root.save();

						Usuario Admin = new Usuario("admin", "123123", "1234");
						Admin.perfil = Perfil.ADMINISTRADOR;
						Admin.save();

						Usuario Cliente = new Usuario("cliente", "12312", "1234");
						Cliente.perfil = Perfil.CLIENTE;
						Cliente.save();

					}
				}
			}
		}
	}
}
