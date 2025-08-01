package jobs;

import model.Categoria;
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
		}
	}
}
