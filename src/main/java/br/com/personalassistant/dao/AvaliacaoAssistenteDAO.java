package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.AvaliacaoAssistente;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public class AvaliacaoAssistenteDAO extends DAO {

	private static final long serialVersionUID = -902709003677742503L;

	public void save(AvaliacaoAssistente avaliacaoAssistente) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(avaliacaoAssistente);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar avaliacaoAssistente");
		}
	}
	
	public void delete(AvaliacaoAssistente avaliacaoAssistente) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(avaliacaoAssistente.getClass(), avaliacaoAssistente.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover avaliacaoAssistente");
		}
	}
	
	public AvaliacaoAssistente update(AvaliacaoAssistente avaliacaoAssistente) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		AvaliacaoAssistente avaliacaoAssistenteAtualizado = avaliacaoAssistente; 
		
		try{
			entityManager.find(avaliacaoAssistente.getClass(), avaliacaoAssistente.getId());
			entityManager.merge(avaliacaoAssistente);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar avaliacaoAssistente");
		}
		
		return avaliacaoAssistenteAtualizado;
	}
	
	public List<AvaliacaoAssistente> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<AvaliacaoAssistente> avaliacoesAssistentes = null;
		
		try{
			TypedQuery<AvaliacaoAssistente> typedQuery = entityManager.createQuery("SELECT avaliacaoAssistente FROM AvaliacaoAssistente avaliacaoAssistente", AvaliacaoAssistente.class);
			avaliacoesAssistentes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(avaliacoesAssistentes == null){
				throw new NaoExistemObjetosException("Não existem avaliacoesAssistentes");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar avaliacoesAssistentes");
		}
		
		return avaliacoesAssistentes;
	}
	
	public AvaliacaoAssistente getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		AvaliacaoAssistente avaliacaoAssistente = null;
		
		try{
			avaliacaoAssistente = entityManager.find(AvaliacaoAssistente.class, id);
		}
		catch(PersistenceException ex){
			
			if(avaliacaoAssistente == null){
				throw new ObjetoNaoExisteException("Não existe avaliacaoAssistente com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar avaliacaoAssistente");
		}
		
		return avaliacaoAssistente;
	}
	
	
}
