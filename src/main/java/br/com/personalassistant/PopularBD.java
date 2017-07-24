package br.com.personalassistant;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import br.com.personalassistant.entidades.Administrador;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AdministradorService;

public final class PopularBD {

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;
		AdministradorService administradorService = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("personal-assistant");
			entityManager = entityManagerFactory.createEntityManager();
			administradorService = new AdministradorService();

			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			Administrador admin = new Administrador("Vinicius","87 99158-7626", 
					"vinicius.gouveia.gomes@gmail.com", "vinicius");
			
			administradorService.criptografarSenha(admin);
			entityManager.persist(admin);

			entityTransaction.commit();
		} 
		catch (PersistenceException pe) {
			
			pe.printStackTrace();
			
			if (entityTransaction != null) {
				entityTransaction.rollback();
			}
		} 
		catch (ServiceException e) {
			
			e.printStackTrace();
			
			if (entityTransaction != null) {
				entityTransaction.rollback();
			}
		} 
		finally {
			
			if (entityManager != null) {
				entityManager.close();
			}
			
			if (entityManagerFactory != null) {
				entityManagerFactory.close();
			}
		}
	}

}
