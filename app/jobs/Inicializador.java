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

			Categoria pastel = new Categoria("Pastel");
			pastel.save();

			Categoria coxinha = new Categoria("Coxinha");
			coxinha.save();

			Categoria hamburguer = new Categoria("Hamburguer");
			hamburguer.save();

			Categoria bebida = new Categoria("Bebida");
			bebida.save();

			Categoria sobremesa = new Categoria("Sobremesa");
			sobremesa.save();

//			TAMANHO DOS PRODUTOS
			if (Tamanho.count() == 0) {

//				TAMANHO PARA COMIDAS

				Tamanho x = new Tamanho(" ");
				x.save();
				
				Tamanho p = new Tamanho("P");
				p.save();

				Tamanho m = new Tamanho("M");
				m.save();

				Tamanho g = new Tamanho("G");
				g.save();

//				TAMANHO PARA BEBIDAS
				Tamanho ml1 = new Tamanho("350ml");
				ml1.save();

				Tamanho ml2 = new Tamanho("600ml");
				ml2.save();

				Tamanho l1 = new Tamanho("1L");
				l1.save();

				Tamanho l2 = new Tamanho("2L");
				l2.save();
//					TAMANHO PARA COMIDAS

//				PRODUTOS
					if (Produto.count() == 0) {
						Produto pepsi = new Produto();
						pepsi.nome = "Pepsi";
						pepsi.preco = "12";
						pepsi.tamanho = ml1;
						pepsi.descricao = "Refrigerante";
						pepsi.categoria = bebida;
						pepsi.save();
						
						Produto cocaCola = new Produto();
						cocaCola.nome = "Coca-cola";
						cocaCola.preco = "5";
						cocaCola.tamanho = ml1;
						cocaCola.descricao = "Coca-cola 350ml";
						cocaCola.categoria = bebida;
						cocaCola.save();
						
						Produto cocaCola1l = new Produto();
						cocaCola1l.nome = "Coca-cola";
						cocaCola1l.preco = "12";
						cocaCola1l.tamanho = l1;
						cocaCola1l.descricao = "Coca-cola 1l";
						cocaCola1l.categoria = bebida;
						cocaCola1l.save();
						
						Produto cocaCola2l = new Produto();
						cocaCola1l.nome = "Coca-cola";
						cocaCola1l.preco = "12";
						cocaCola1l.tamanho = l2;
						cocaCola1l.descricao = "Coca-cola 2l";
						cocaCola1l.categoria = bebida;
						cocaCola1l.save();
						
						Produto cocaColaZero = new Produto();
						cocaColaZero.nome = "Coca-cola zero";
						cocaColaZero.preco = "6";
						cocaColaZero.tamanho = ml1;
						cocaColaZero.descricao = "Coca-cola 350ml zero açúcar";
						cocaColaZero.categoria = bebida;
						cocaColaZero.save();
						
						Produto guarana1l = new Produto();
						guarana1l.nome = "Guaraná";
						guarana1l.preco = "12";
						guarana1l.tamanho = l1;
						guarana1l.descricao = "Coca-cola 1l";
						guarana1l.categoria = bebida;
						guarana1l.save();
						
						Produto guarana2l = new Produto();
						guarana2l.nome = "Guaraná";
						guarana2l.preco = "15";
						guarana2l.tamanho = l2;
						guarana2l.descricao = "Coca-cola 2l";
						guarana2l.categoria = bebida;
						guarana2l.save();
						
						Produto sucoDeLaranja = new Produto();
						sucoDeLaranja.nome = "Suco de laranja";
						sucoDeLaranja.preco = "8";
						sucoDeLaranja.tamanho = ml2;
						sucoDeLaranja.descricao = "Copo de suco de laranja";
						sucoDeLaranja.categoria = bebida;
						sucoDeLaranja.save();

						Produto sucoDeMaracujá = new Produto();
						sucoDeMaracujá.nome = "Suco de maracujá";
						sucoDeMaracujá.preco = "8";
						sucoDeMaracujá.tamanho = ml2;
						sucoDeMaracujá.descricao = "Copo de suco de maracujá";
						sucoDeMaracujá.categoria = bebida;
						sucoDeMaracujá.save();
						
						Produto sucoDeAcerola = new Produto();
						sucoDeAcerola.nome = "Suco de acerola";
						sucoDeAcerola.preco = "8";
						sucoDeAcerola.tamanho = ml2;
						sucoDeAcerola.descricao = "Copo de suco de acerola";
						sucoDeAcerola.categoria = bebida;
						sucoDeAcerola.save();

						Produto coxinhaDeFrango = new Produto();
						coxinhaDeFrango.nome = "Coxinha de frango";
						coxinhaDeFrango.preco = "5";
						coxinhaDeFrango.tamanho = x;
						coxinhaDeFrango.descricao = "frango com catupiry e requeijão";
						coxinhaDeFrango.categoria = coxinha;
						coxinhaDeFrango.save();

						Produto coxinhaDeCarne = new Produto();
						coxinhaDeCarne.nome = "Coxinha de carne";
						coxinhaDeCarne.preco = "5";
						coxinhaDeCarne.tamanho = x;
						coxinhaDeCarne.descricao = "carne moída e requeijão";
						coxinhaDeCarne.categoria = coxinha;
						coxinhaDeCarne.save();
						
						
						Produto coxinhaDePizza = new Produto();
						coxinhaDePizza.nome = "Coxinha de pizza";
						coxinhaDePizza.tamanho = x;
						coxinhaDePizza.preco = "5";
						coxinhaDePizza.descricao = "Queijo, presunto e orégano";
						coxinhaDePizza.categoria = coxinha;
						coxinhaDePizza.save();

						Produto pastelDeCarne = new Produto();
						pastelDeCarne.nome = "Pastel de carne";
						pastelDeCarne.tamanho = x;
						pastelDeCarne.preco = "5";
						pastelDeCarne.descricao = "Carne e requeijão";
						pastelDeCarne.categoria = pastel;
						pastelDeCarne.save();
						
						Produto pastelDeQueijo = new Produto();
						pastelDeQueijo.nome = "Pastel de queijo";
						pastelDeQueijo.tamanho = x;
						pastelDeQueijo.preco = "5";
						pastelDeQueijo.descricao = "Queijo e orégano";
						pastelDeQueijo.categoria = pastel;
						pastelDeQueijo.save();
						
						Produto pastelDeFrango = new Produto();
						pastelDeFrango.nome = "Pastel de frango";
						pastelDeFrango.tamanho = x;
						pastelDeFrango.preco = "5";
						pastelDeFrango.descricao = "Frango, requeijão e orégano";
						pastelDeFrango.categoria = pastel;
						pastelDeFrango.save();
						
						Produto pastelDePizza = new Produto();
						pastelDePizza.nome = "Pastel de pizza";
						pastelDePizza.tamanho = x;
						pastelDePizza.preco = "5";
						pastelDePizza.descricao = "Queijo, presunto e orégano";
						pastelDePizza.categoria = pastel;
						pastelDePizza.save();
						
						Produto xtudo = new Produto();
						xtudo.nome = "X-tudo";
						xtudo.tamanho = x;
						xtudo.preco = "14";
						xtudo.descricao = "Pão gergelim, hamburguer 90g, queijo, ovo, salada e orégano";
						xtudo.categoria = hamburguer;
						xtudo.save();
						
						Produto xfrango = new Produto();
						xfrango.nome = "X-frango";
						xfrango.tamanho = x;
						xfrango.preco = "12";
						xfrango.descricao = "Pão gergelim, frango desfiado 90g, queijo, ovo, salada e orégano";
						xfrango.categoria = hamburguer;
						xfrango.save();
						
						Produto xcarne = new Produto();
						xcarne.nome = "X-carne";
						xcarne.tamanho = x;
						xcarne.preco = "16";
						xcarne.descricao = "Pão gergelim, hamburguer 100g, queijo, ovo, salada e orégano";
						xcarne.categoria = hamburguer;
						xcarne.save();
						
						Produto mousseDeMaracujá = new Produto();
						mousseDeMaracujá.nome = "Mousse de maracujá";
						mousseDeMaracujá.tamanho = x;
						mousseDeMaracujá.preco = "16";
						mousseDeMaracujá.descricao = "Mousse de maracujá feito da fruta";
						mousseDeMaracujá.categoria = sobremesa;
						mousseDeMaracujá.save();
						
						Produto acaiP = new Produto();
						acaiP.nome = "Açaí";
						acaiP.tamanho = p;
						acaiP.preco = "10";
						acaiP.descricao = "Açaí, banana e granola";
						acaiP.categoria = sobremesa;
						acaiP.save();
						
						Produto acaiM = new Produto();
						acaiM.nome = "Açaí";
						acaiM.tamanho = m;
						acaiM.preco = "13";
						acaiM.descricao = "Açaí, banana,leite em pó e granola";
						acaiM.categoria = sobremesa;
						acaiM.save();
						
						Produto acaiG = new Produto();
						acaiG.nome = "Açaí";
						acaiG.tamanho = g;
						acaiG.preco = "16";
						acaiG.descricao = "Açaí, banana,leite em pó, farinha láctea e granola";
						acaiG.categoria = sobremesa;
						acaiG.save();
						
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