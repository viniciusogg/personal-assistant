package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Lance;
import br.com.personalassistant.excecoes.PersistenciaException;

public class LanceDAO extends DAO {

	private static final long serialVersionUID = 8408307571553851621L;

	public void save(Lance lance) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(lance);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar lance");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(Lance lance) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(lance.getClass(), lance.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover lance");
		}
		finally{
			entityManager.close();
		}
	}
	
	public Lance update(Lance lance) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Lance lanceAtualizado = lance; 
		
		try{
			entityManager.find(lance.getClass(), lance.getId());
			entityManager.merge(lance);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar lance");
		}
		finally{
			entityManager.close();
		}
		
		return lanceAtualizado;
	}
	
	public List<Lance> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<Lance> lances = null;
		
		try{
			TypedQuery<Lance> typedQuery = entityManager.createQuery("SELECT lance FROM Lance lance", Lance.class);
			lances = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar lances");
		}
		finally{
			entityManager.close();
		}
		
		return lances;
	}
	
	public Lance getById(Long id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		Lance lance = null;
		
		try{
			lance = entityManager.find(Lance.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar lance");
		}
		finally{
			entityManager.close();
		}
		
		return lance;
	}
	
}
