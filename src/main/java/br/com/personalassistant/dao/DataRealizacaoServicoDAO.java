package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.DataRealizacaoServico;
import br.com.personalassistant.excecoes.PersistenciaException;

public class DataRealizacaoServicoDAO extends DAO {

	public void save(DataRealizacaoServico dataRealizacaoServico) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.persist(dataRealizacaoServico);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao salvar dataRealizacaoServico");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(DataRealizacaoServico dataRealizacaoServico) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.remove(dataRealizacaoServico);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao remover dataRealizacaoServico");
		}
		finally{
			entityManager.close();
		}
	}
	
	public DataRealizacaoServico update(DataRealizacaoServico dataRealizacaoServico) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		DataRealizacaoServico administradorAtualizado = dataRealizacaoServico; 
		
		try{
			entityManager.find(dataRealizacaoServico.getClass(), dataRealizacaoServico.getId());
			entityManager.merge(dataRealizacaoServico);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao atualizar dataRealizacaoServico");
		}
		finally{
			entityManager.close();
		}
		
		return administradorAtualizado;
	}
	
	public List<DataRealizacaoServico> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<DataRealizacaoServico> datasRealizacoesServicos = null;
		
		try{
			TypedQuery<DataRealizacaoServico> typedQuery = entityManager.createQuery("SELECT dataRealizacaoServico FROM DataRealizacaoServico dataRealizacaoServico", DataRealizacaoServico.class);
			datasRealizacoesServicos = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar datasRealizacoesServicos");
		}
		finally{
			entityManager.close();
		}
		
		return datasRealizacoesServicos;
	}
	
	public DataRealizacaoServico getById(Integer id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		DataRealizacaoServico dataRealizacaoServico = null;
		
		try{
			dataRealizacaoServico = entityManager.find(DataRealizacaoServico.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar dataRealizacaoServico");
		}
		finally{
			entityManager.close();
		}
		
		return dataRealizacaoServico;
	}
	
}
