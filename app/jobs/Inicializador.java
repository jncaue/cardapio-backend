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
			Categoria comida = new Categoria("Comida", 100);
			comida.save();

			Categoria bebida = new Categoria("Bebida", 101);
			bebida.save();

			Categoria sobremesa = new Categoria("Sobremesa", 102);
			sobremesa.save();

		}
	}
}
