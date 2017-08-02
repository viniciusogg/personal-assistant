package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.AvaliacaoContratante;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public class AvaliacaoContratanteDAO extends DAO {

	private static final long serialVersionUID = -902709003677742503L;

	public void save(AvaliacaoContratante avaliacaoContratante) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(avaliacaoContratante);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar avaliacaoContratante");
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
	}
	
	public AvaliacaoContratante update(AvaliacaoContratante avaliacaoContratante) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		AvaliacaoContratante avaliacaoContratanteAtualizado = avaliacaoContratante; 
		
		try{
			entityManager.find(avaliacaoContratante.getClass(), avaliacaoContratante.getId());
			entityManager.merge(avaliacaoContratante);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar avaliacaoContratante");
		}
		
		return avaliacaoContratanteAtualizado;
	}
	
	public List<AvaliacaoContratante> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<AvaliacaoContratante> avaliacoesContratantes = null;
		
		try{
			TypedQuery<AvaliacaoContratante> typedQuery = entityManager.createQuery("SELECT avaliacaoContratante FROM AvaliacaoContratante avaliacaoContratante", AvaliacaoContratante.class);
			avaliacoesContratantes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(avaliacoesContratantes == null){
				throw new NaoExistemObjetosException("Não existem avaliacoesContratantes");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar avaliacoesContratantes");
		}
		
		return avaliacoesContratantes;
	}
	
	public AvaliacaoContratante getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		AvaliacaoContratante avaliacaoContratante = null;
		
		try{
			avaliacaoContratante = entityManager.find(AvaliacaoContratante.class, id);
		}
		catch(PersistenceException ex){
			
			if(avaliacaoContratante == null){
				throw new ObjetoNaoExisteException("Não existe avaliacaoContratante com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar avaliacaoContratante");
		}
		
		return avaliacaoContratante;
	}
	
	
}
