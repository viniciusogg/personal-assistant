package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.OfertaServico;
import br.com.personalassistant.excecoes.PersistenciaException;

public class OfertaServicoDAO extends DAO {

	private static final long serialVersionUID = 1L;

	public void save(OfertaServico ofertaServico) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(ofertaServico);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar ofertaServico");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(OfertaServico ofertaServico) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(ofertaServico.getClass(), ofertaServico.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover ofertaServico");
		}
		finally{
			entityManager.close();
		}
	}
	
	public OfertaServico update(OfertaServico ofertaServico) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		OfertaServico ofertaServicoAtualizada = ofertaServico; 
		
		try{
			entityManager.find(ofertaServico.getClass(), ofertaServico.getId());
			entityManager.merge(ofertaServico);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar ofertaServico");
		}
		finally{
			entityManager.close();
		}
		
		return ofertaServicoAtualizada;
	}
	
	public List<OfertaServico> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<OfertaServico> ofertasServicos = null;
		
		try{
			TypedQuery<OfertaServico> typedQuery = entityManager.createQuery("SELECT ofertaServico FROM OfertaServico ofertaServico", OfertaServico.class);
			ofertasServicos = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar ofertasServicos");
		}
		finally{
			entityManager.close();
		}
		
		return ofertasServicos;
	}
	
	public OfertaServico getById(Long id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		OfertaServico ofertaServico = null;
		
		try{
			ofertaServico = entityManager.find(OfertaServico.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar ofertaServico");
		}
		finally{
			entityManager.close();
		}
		
		return ofertaServico;
	}
	
}
