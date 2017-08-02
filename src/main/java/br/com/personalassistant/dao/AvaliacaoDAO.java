package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Avaliacao;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public class AvaliacaoDAO extends DAO {

	private static final long serialVersionUID = -902709003677742503L;

	public void save(Avaliacao avaliacao) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(avaliacao);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar avaliacao");
		}
	}
	
	public void delete(Avaliacao avaliacao) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(avaliacao.getClass(), avaliacao.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover avaliacao");
		}
	}
	
	public Avaliacao update(Avaliacao avaliacao) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Avaliacao avaliacaoAtualizado = avaliacao; 
		
		try{
			entityManager.find(avaliacao.getClass(), avaliacao.getId());
			entityManager.merge(avaliacao);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar avaliacao");
		}
		
		return avaliacaoAtualizado;
	}
	
	public List<Avaliacao> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<Avaliacao> avaliacoes = null;
		
		try{
			TypedQuery<Avaliacao> typedQuery = entityManager.createQuery("SELECT avaliacao FROM Avaliacao avaliacao", Avaliacao.class);
			avaliacoes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(avaliacoes == null){
				throw new NaoExistemObjetosException("Não existem avaliacoes");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar avaliacoes");
		}
		
		return avaliacoes;
	}
	
	public Avaliacao getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		Avaliacao avaliacao = null;
		
		try{
			avaliacao = entityManager.find(Avaliacao.class, id);
		}
		catch(PersistenceException ex){
			
			if(avaliacao == null){
				throw new ObjetoNaoExisteException("Não existe avaliacao com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar avaliacao");
		}
		
		return avaliacao;
	}
	
	
}
