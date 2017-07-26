package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.excecoes.PersistenciaException;

public class AssistenteDAO extends DAO {

	private static final long serialVersionUID = 3088908627019097676L;

	public void save(Assistente assistente) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(assistente);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar assistente");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(Assistente assistente) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(assistente.getClass(), assistente.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover assistente");
		}
		finally{
			entityManager.close();
		}
	}
	
	public Assistente update(Assistente assistente) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Assistente assistenteAtualizado = assistente; 
		
		try{
			entityManager.find(assistente.getClass(), assistente.getId());
			entityManager.merge(assistente);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar assistente");
		}
		finally{
			entityManager.close();
		}
		
		return assistenteAtualizado;
	}
	
	public List<Assistente> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<Assistente> assistentes = null;
		
		try{
			TypedQuery<Assistente> typedQuery = entityManager.createQuery("SELECT assistente FROM Assistente assistente", Assistente.class);
			assistentes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar assistentes");
		}
		finally{
			entityManager.close();
		}
		
		return assistentes;
	}
	
	public Assistente getById(Long id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		Assistente assistente = null;
		
		try{
			assistente = entityManager.find(Assistente.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar assistente");
		}
		finally{
			entityManager.close();
		}
		
		return assistente;
	}
	
}
