package br.com.personalassistant.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProdutor {

	@Produces
	@ApplicationScoped
	public EntityManagerFactory getEntityManagerFactory() {
		EntityManagerFactory entityManagerFactory = null;
		
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("personalassistant");
		} 
		catch (Throwable t) {
			throw t;
		}
		return entityManagerFactory;
	}
	
	@Produces 
	@RequestScoped
	public EntityManager criarEntityManager(EntityManagerFactory entityManagerFactory){
		return entityManagerFactory.createEntityManager();
	}
	
	public void fecharEntityManager(@Disposes EntityManager entityManager){
		entityManager.close();
	}
	
	public void fecharEntityManagerFactory(@Disposes EntityManagerFactory entityManagerFactory){
		entityManagerFactory.close();
	}
}
