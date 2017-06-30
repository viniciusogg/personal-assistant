package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.AvaliacaoAssistente;
import br.com.personalassistant.excecoes.PersistenciaException;

public class AvaliacaoAssistenteDAO extends DAO {

	public void save(AvaliacaoAssistente avaliacaoAssistente) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.persist(avaliacaoAssistente);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao salvar avaliacaoAssistente");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(AvaliacaoAssistente avaliacaoAssistente) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.remove(avaliacaoAssistente);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao remover avaliacaoAssistente");
		}
		finally{
			entityManager.close();
		}
	}
	
	public AvaliacaoAssistente update(AvaliacaoAssistente avaliacaoAssistente) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		AvaliacaoAssistente avaliacaoAssistenteAtualizada = avaliacaoAssistente; 
		
		try{
			entityManager.find(avaliacaoAssistente.getClass(), avaliacaoAssistente.getId());
			entityManager.merge(avaliacaoAssistente);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao atualizar avaliacaoAssistente");
		}
		finally{
			entityManager.close();
		}
		
		return avaliacaoAssistenteAtualizada;
	}
	
	public List<AvaliacaoAssistente> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<AvaliacaoAssistente> avaliacoesAssistentes = null;
		
		try{
			TypedQuery<AvaliacaoAssistente> typedQuery = entityManager.createQuery("SELECT avaliacaoAssistente FROM AvaliacaoAssistente avaliacaoAssistente", AvaliacaoAssistente.class);
			avaliacoesAssistentes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar avaliacoesAssistentes");
		}
		finally{
			entityManager.close();
		}
		
		return avaliacoesAssistentes;
	}
	
	public AvaliacaoAssistente getById(Long id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		AvaliacaoAssistente avaliacaoAssistente = null;
		
		try{
			avaliacaoAssistente = entityManager.find(AvaliacaoAssistente.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar avaliacaoAssistente");
		}
		finally{
			entityManager.close();
		}
		
		return avaliacaoAssistente;
	}
	
}