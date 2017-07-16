package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.AvaliacaoContratante;
import br.com.personalassistant.excecoes.PersistenciaException;

public class AvaliacaoContratanteDAO extends DAO{

	private static final long serialVersionUID = 1L;

	public void save(AvaliacaoContratante avaliacaoContratante) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(avaliacaoContratante);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar avaliacaoContratante");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(AvaliacaoContratante avaliacaoContratante) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(avaliacaoContratante.getClass(), avaliacaoContratante.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover avaliacaoContratante");
		}
		finally{
			entityManager.close();
		}
	}
	
	public AvaliacaoContratante update(AvaliacaoContratante avaliacaoContratante) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		AvaliacaoContratante avaliacaoContratanteAtualizada = avaliacaoContratante; 
		
		try{
			entityManager.find(avaliacaoContratante.getClass(), avaliacaoContratante.getId());
			entityManager.merge(avaliacaoContratante);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar avaliacaoContratante");
		}
		finally{
			entityManager.close();
		}
		
		return avaliacaoContratanteAtualizada;
	}
	
	public List<AvaliacaoContratante> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<AvaliacaoContratante> avaliacoesContratantes = null;
		
		try{
			TypedQuery<AvaliacaoContratante> typedQuery = entityManager.createQuery("SELECT avaliacaoContratante FROM AvaliacaoContratante avaliacaoContratante", AvaliacaoContratante.class);
			avaliacoesContratantes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar avaliacoesContratantes");
		}
		finally{
			entityManager.close();
		}
		
		return avaliacoesContratantes;
	}
	
	public AvaliacaoContratante getById(Long id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		AvaliacaoContratante avaliacaoContratante = null;
		
		try{
			avaliacaoContratante = entityManager.find(AvaliacaoContratante.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar avaliacaoContratante");
		}
		finally{
			entityManager.close();
		}
		
		return avaliacaoContratante;
	}
	
}
