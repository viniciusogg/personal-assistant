package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Negociacao;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public class NegociacaoDAO extends DAO {

	private static final long serialVersionUID = -505353346213062846L;

	public void save(Negociacao negociacao) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(negociacao);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar negociacao");
		}
	}
	
	public void delete(Negociacao negociacao) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(negociacao.getClass(), negociacao.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover negociacao");
		}
	}
	
	public Negociacao update(Negociacao negociacao) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Negociacao negociacaoAtualizado = negociacao; 
		
		try{
			entityManager.find(negociacao.getClass(), negociacao.getId());
			entityManager.merge(negociacao);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar negociacao");
		}
		
		return negociacaoAtualizado;
	}
	
	public List<Negociacao> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<Negociacao> negociacoes = null;
		
		try{
			TypedQuery<Negociacao> typedQuery = entityManager.createQuery("SELECT negociacao FROM Negociacao negociacao", Negociacao.class);
			negociacoes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(negociacoes == null){
				throw new NaoExistemObjetosException("Não existem negociacoes");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar negociacoes");
		}
		
		return negociacoes;
	}
	
	public Negociacao getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		Negociacao negociacao = null;
		
		try{
			negociacao = entityManager.find(Negociacao.class, id);
		}
		catch(PersistenceException ex){
			
			if(negociacao == null){
				throw new ObjetoNaoExisteException("Não existe negociacao com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar negociacao");
		}
		
		return negociacao;
	}
	
	public List<Negociacao> getToAssistenteAllById(Long id) throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<Negociacao> negociacoes = null;
		
		try{
			TypedQuery<Negociacao> typedQuery = entityManager.createQuery("SELECT negociacao FROM "
					+ "Negociacao negociacao "
					+ "WHERE negociacao.assistente.id = :id", Negociacao.class);
			typedQuery.setParameter("id", id);
			
			negociacoes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(negociacoes == null){
				throw new NaoExistemObjetosException("Não existem negociacoes");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar negociacoes");
		}
		
		return negociacoes;
	}
	
	public List<Negociacao> getToContratanteAllById(Long id) throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<Negociacao> negociacoes = null;
		
		try{
			TypedQuery<Negociacao> typedQuery = entityManager.createQuery("SELECT negociacao FROM "
					+ "Negociacao negociacao "
					+ "WHERE negociacao.contratante.id = :id", Negociacao.class);
			typedQuery.setParameter("id", id);
			
			negociacoes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(negociacoes == null){
				throw new NaoExistemObjetosException("Não existem negociacoes");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar negociacoes");
		}
		
		return negociacoes;
	}
	
	public Negociacao refresh(Negociacao negociacao) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		
		try{
			negociacao = entityManager.find(Negociacao.class, negociacao.getId());
			entityManager.refresh(negociacao);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Refresh falhou");
		}
		
		return negociacao;
	}
}
