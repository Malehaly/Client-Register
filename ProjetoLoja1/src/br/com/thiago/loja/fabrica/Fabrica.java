package br.com.thiago.loja.fabrica;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Fabrica {
	
	private static EntityManagerFactory emf;

	public static EntityManager getEntityManager() {
		 if (emf == null){
			emf = Persistence.createEntityManagerFactory("loja_um");
		 }
		 return emf.createEntityManager();
	}

}
