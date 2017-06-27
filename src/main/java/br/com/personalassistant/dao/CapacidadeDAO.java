package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Capacidade;
import br.com.personalassistant.excecoes.PersistenciaException;

public class CapacidadeDAO extends DAO {

	public void save(Capacidade capacidade) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.persist(capacidade);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao salvar capacidade");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(Capacidade capacidade) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.remove(capacidade);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao remover capacidade");
		}
		finally{
			entityManager.close();
		}
	}
	
	public Capacidade update(Capacidade capacidade) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Capacidade administradorAtualizado = capacidade; 
		
		try{
			entityManager.find(capacidade.getClass(), capacidade.getId());
			entityManager.merge(capacidade);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao atualizar capacidade");
		}
		finally{
			entityManager.close();
		}
		
		return administradorAtualizado;
	}
	
	public List<Capacidade> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<Capacidade> capacidades = null;
		
		try{
			TypedQuery<Capacidade> typedQuery = entityManager.createQuery("SELECT capacidade FROM Capacidade capacidade", Capacidade.class);
			capacidades = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar capacidades");
		}
		finally{
			entityManager.close();
		}
		
		return capacidades;
	}
	
	public Capacidade getById(Integer id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		Capacidade capacidade = null;
		
		try{
			capacidade = entityManager.find(Capacidade.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar capacidade");
		}
		finally{
			entityManager.close();
		}
		
		return capacidade;
	}
	
}
