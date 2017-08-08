package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Proposta;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public class PropostaDAO extends DAO {

	private static final long serialVersionUID = -902709003677742503L;

	public void save(Proposta proposta) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(proposta);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar proposta");
		}
	}
	
	public void delete(Proposta proposta) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(proposta.getClass(), proposta.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover proposta");
		}
	}
	
	public Proposta update(Proposta proposta) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Proposta propostaAtualizado = proposta; 
		
		try{
			entityManager.find(proposta.getClass(), proposta.getId());
			entityManager.merge(proposta);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar proposta");
		}
		
		return propostaAtualizado;
	}
	
	public List<Proposta> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<Proposta> propostas = null;
		
		try{
			TypedQuery<Proposta> typedQuery = entityManager.createQuery("SELECT proposta FROM Proposta proposta", Proposta.class);
			propostas = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(propostas == null){
				throw new NaoExistemObjetosException("Não existem propostas");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar propostas");
		}
		
		return propostas;
	}
	
	public Proposta getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		Proposta proposta = null;
		
		try{
			proposta = entityManager.find(Proposta.class, id);
		}
		catch(PersistenceException ex){
			
			if(proposta == null){
				throw new ObjetoNaoExisteException("Não existe proposta com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar proposta");
		}
		
		return proposta;
	}
	
	public Proposta refresh(Proposta proposta) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			proposta = entityManager.find(Proposta.class, proposta.getId());
			entityManager.refresh(proposta);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Refresh falhou");
		}
		
		return proposta;
	}
	
}
