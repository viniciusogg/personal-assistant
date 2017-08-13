package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Lance;
//import br.com.personalassistant.entidades.PK;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
//import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public class LanceDAO extends DAO<Lance> {

	private static final long serialVersionUID = -902709003677742503L;

	/*public void save(Lance lance) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(lance);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar lance");
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
		
		return lanceAtualizado;
	}
	
	public List<Lance> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<Lance> lances = null;
		
		try{
			TypedQuery<Lance> typedQuery = entityManager.createQuery("SELECT lance FROM Lance lance", Lance.class);
			lances = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(lances == null){
				throw new NaoExistemObjetosException("Não existem lances");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar lances");
		}
		
		return lances;
	}
	
	public Lance getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		Lance lance = null;
		
		try{
			lance = entityManager.find(Lance.class, id);
		}
		catch(PersistenceException ex){
			
			if(lance == null){
				throw new ObjetoNaoExisteException("Não existe lance com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar lance");
		}
		
		return lance;
	}*/
	
	public List<Lance> getAllByIdAssistente(Long id) throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<Lance> lances = null;
		
		try{
			TypedQuery<Lance> typedQuery = entityManager.createQuery("SELECT l"
					+ "FROM Lance l "
					+ "WHERE l.assistente.pk.id = :id ", Lance.class);
			typedQuery.setParameter("id", id);
			 
			lances = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(lances == null){
				throw new NaoExistemObjetosException("Não existem lances");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar lances");
		}
		
		return lances;
	}
	
	
}
