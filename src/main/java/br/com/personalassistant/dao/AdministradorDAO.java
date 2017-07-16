package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Administrador;
import br.com.personalassistant.excecoes.PersistenciaException;

public class AdministradorDAO extends DAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void save(Administrador administrador) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.persist(administrador);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao salvar administrador");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(Administrador administrador) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.remove(entityManager.getReference(administrador.getClass(), administrador.getId()));
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao remover administrador");
		}
		finally{
			entityManager.close();
		}
	}
	
	public Administrador update(Administrador administrador) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Administrador administradorAtualizado = administrador; 
		
		try{
			entityManager.find(administrador.getClass(), administrador.getId());
			entityManager.merge(administrador);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao atualizar administrador");
		}
		finally{
			entityManager.close();
		}
		
		return administradorAtualizado;
	}
	
	public List<Administrador> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<Administrador> administradores = null;
		
		try{
			TypedQuery<Administrador> typedQuery = entityManager.createQuery("SELECT administrador FROM Administrador administrador", Administrador.class);
			administradores = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar administradores");
		}
		finally{
			entityManager.close();
		}
		
		return administradores;
	}
	
	public Administrador getById(Long id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		Administrador administrador = null;
		
		try{
			administrador = entityManager.find(Administrador.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar administrador");
		}
		finally{
			entityManager.close();
		}
		
		return administrador;
	}
	
}
