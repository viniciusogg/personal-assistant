package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Capacidade;
import br.com.personalassistant.excecoes.PersistenciaException;

public class CapacidadeDAO extends DAO {

	private static final long serialVersionUID = 1L;

	public void save(Capacidade capacidade) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(capacidade);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar capacidade");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(Capacidade capacidade) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(capacidade.getClass(), capacidade.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover capacidade");
		}
		finally{
			entityManager.close();
		}
	}
	
	public Capacidade update(Capacidade capacidade) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Capacidade capacidadeAtualizada = capacidade; 
		
		try{
			entityManager.find(capacidade.getClass(), capacidade.getId());
			entityManager.merge(capacidade);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar capacidade");
		}
		finally{
			entityManager.close();
		}
		
		return capacidadeAtualizada;
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
	
	public Capacidade getById(Long id) throws PersistenciaException {
		
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
