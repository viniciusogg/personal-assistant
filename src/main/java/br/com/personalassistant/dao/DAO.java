package br.com.personalassistant.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAO {

	protected static EntityManagerFactory entityManagerFactory;
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("personal-assistant");
	}
	
	protected EntityManager getEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
	
	protected void closeEntityManager(){
		if(entityManagerFactory.isOpen()){
			entityManagerFactory.close();
		}
	}
}