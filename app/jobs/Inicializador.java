package jobs;

import model.Categoria;
import model.Perfil;
import model.Produto;
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

			Categoria bebida = new Categoria("Bebida");
			bebida.save();

			Categoria sobremesa = new Categoria("Sobremesa");
			sobremesa.save();

//			inicia dois produtos para auxilio de execucao do site
			if (Produto.count() == 0) {
				Produto pepsi = new Produto();
				pepsi.nome = "Pepsi";
				pepsi.preco = 12;
				pepsi.categoria = bebida;
				pepsi.save();
				
				Produto hamburguer = new Produto();
				hamburguer.nome = "Hamburguer ";
				hamburguer.preco = 20;
				hamburguer.categoria = comida;
				hamburguer.save();
				
//				inicia um usuario raiz para acesso ao site
			if (Usuario.count() == 0) {
				Usuario Admin= new Usuario("admin", "123123", "1234");
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
