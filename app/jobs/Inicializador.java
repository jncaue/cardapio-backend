package jobs;

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

//		se o numero de categorias for 0, crie 3 categorias
		if (Categoria.count() == 0) {
			Categoria comida = new Categoria("Comida");
			comida.save();
			
			Categoria pizza = new Categoria("Pizza");
			pizza.save();
			
			Categoria hamburguer = new Categoria("Hamburguer");
			hamburguer.save();
			
			Categoria cachorroQuente = new Categoria("Cachorro quente");
			cachorroQuente.save();

			Categoria bebida = new Categoria("Bebida");
			bebida.save();
			
			Categoria suco = new Categoria("Suco");
			suco.save();

			Categoria sobremesa = new Categoria("Sobremesa");
			sobremesa.save();
			

//			inicia dois produtos para auxilio de execucao do site
			if (Produto.count() == 0) {
				Produto pepsi = new Produto();
				pepsi.nome = "Pepsi";
				pepsi.preco = 12;
				pepsi.descricao= "Refrigerante";
				pepsi.categoria = bebida;
				pepsi.save();

				Produto xbacon = new Produto();
				xbacon.nome = "Xbacon ";
				xbacon.preco = 20;
				xbacon.descricao= "Hamburguer 90g, bacon, cheddar, salada";
				xbacon.categoria = hamburguer;
				xbacon.save();
				
				Produto açai = new Produto();
				açai.nome = "Açaí";
				açai.preco = 22;
				açai.descricao= "Açaí 500g, banana, leite em pó, granola";
				açai.categoria = sobremesa;
				açai.save();
				
//				inicia um usuario raiz para acesso ao site
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
					
					if (Tamanho.count() == 0) {

						Tamanho m = new Tamanho ("P");
						m.save();

						Tamanho p = new Tamanho ("M");
						p.save();
						
						Tamanho g = new Tamanho ("G");
						g.save();
					}
				}

			}

		}

	}
}
