package jobs;

import model.Produto;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job{
		
		@Override
		public void doJob() throws Exception {
			
			if (Produto.count() == 0) {
				Produto hamburguer = new Produto("Hamburger", 100, "rh");
				hamburguer.save();
				
		}

	}

}
