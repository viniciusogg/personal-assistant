package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Servico;
import br.com.personalassistant.excecoes.PersistenciaException;

public class ServicoDAO extends DAO {

	private static final long serialVersionUID = -1564085064355391443L;

	public void save(Servico servico) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(servico);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar servico");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(Servico servico) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(servico.getClass(), servico.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover servico");
		}
		finally{
			entityManager.close();
		}
	}
	
	public Servico update(Servico servico) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Servico avaliacaoAtualizada = servico; 
		
		try{
			entityManager.find(servico.getClass(), servico.getId());
			entityManager.merge(servico);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar servico");
		}
		finally{
			entityManager.close();
		}
		
		return avaliacaoAtualizada;
	}
	
	public List<Servico> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<Servico> servicos = null;
		
		try{
			TypedQuery<Servico> typedQuery = entityManager.createQuery("SELECT servico FROM Servico servico", Servico.class);
			servicos = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar servicos");
		}
		finally{
			entityManager.close();
		}
		
		return servicos;
	}
	
	public Servico getById(Long id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		Servico servico = null;
		
		try{
			servico = entityManager.find(Servico.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar servico");
		}
		finally{
			entityManager.close();
		}
		
		return servico;
	}
	
}
