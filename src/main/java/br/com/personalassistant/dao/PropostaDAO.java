package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Proposta;
import br.com.personalassistant.excecoes.PersistenciaException;

public class PropostaDAO extends DAO {

	public void save(Proposta proposta) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.persist(proposta);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao salvar proposta");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(Proposta proposta) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.remove(entityManager.getReference(proposta.getClass(), proposta.getId()));
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao remover proposta");
		}
		finally{
			entityManager.close();
		}
	}
	
	public Proposta update(Proposta proposta) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Proposta propostaAtualizada = proposta; 
		
		try{
			entityManager.find(proposta.getClass(), proposta.getId());
			entityManager.merge(proposta);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao atualizar proposta");
		}
		finally{
			entityManager.close();
		}
		
		return propostaAtualizada;
	}
	
	public List<Proposta> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<Proposta> propostas = null;
		
		try{
			TypedQuery<Proposta> typedQuery = entityManager.createQuery("SELECT proposta FROM Proposta proposta", Proposta.class);
			propostas = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar propostas");
		}
		finally{
			entityManager.close();
		}
		
		return propostas;
	}
	
	public Proposta getById(Long id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		Proposta proposta = null;
		
		try{
			proposta = entityManager.find(Proposta.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar proposta");
		}
		finally{
			entityManager.close();
		}
		
		return proposta;
	}
	
}
