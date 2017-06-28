package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Avaliacao;
import br.com.personalassistant.excecoes.PersistenciaException;

public class AvaliacaoDAO extends DAO {

	public void save(Avaliacao avaliacao) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.persist(avaliacao);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao salvar avaliacao");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(Avaliacao avaliacao) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.remove(avaliacao);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao remover avaliacao");
		}
		finally{
			entityManager.close();
		}
	}
	
	public Avaliacao update(Avaliacao avaliacao) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Avaliacao avaliacaoAtualizada = avaliacao; 
		
		try{
			entityManager.find(avaliacao.getClass(), avaliacao.getId());
			entityManager.merge(avaliacao);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao atualizar avaliacao");
		}
		finally{
			entityManager.close();
		}
		
		return avaliacaoAtualizada;
	}
	
	public List<Avaliacao> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<Avaliacao> avaliacoes = null;
		
		try{
			TypedQuery<Avaliacao> typedQuery = entityManager.createQuery("SELECT avaliacao FROM Avaliacao avaliacao", Avaliacao.class);
			avaliacoes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar avaliacoes");
		}
		finally{
			entityManager.close();
		}
		
		return avaliacoes;
	}
	
	public Avaliacao getById(Long id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		Avaliacao avaliacao = null;
		
		try{
			avaliacao = entityManager.find(Avaliacao.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar avaliacao");
		}
		finally{
			entityManager.close();
		}
		
		return avaliacao;
	}
	
}
