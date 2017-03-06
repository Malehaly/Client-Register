package br.com.thiago.loja.aplicacao;

import javax.persistence.EntityManager;

import br.com.thiago.loja.fabrica.Fabrica;
import br.com.thiago.loja.view.MenuFrame;

public class Aplicacao {
	
	public static void main(String[] args) {
		
		//initialize the factory - reduce latency
		EntityManager em = Fabrica.getEntityManager();
		em.close();
		
		//main frame
		MenuFrame.main(null);
	}
}
