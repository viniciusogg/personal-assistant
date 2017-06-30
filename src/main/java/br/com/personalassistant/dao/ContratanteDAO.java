package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.excecoes.PersistenciaException;

public class ContratanteDAO extends DAO {

	public void save(Contratante contratante) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.persist(contratante);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao salvar contratante");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(Contratante contratante) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.remove(contratante);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao remover contratante");
		}
		finally{
			entityManager.close();
		}
	}
	
	public Contratante update(Contratante contratante) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Contratante contratanteAtualizado = contratante; 
		
		try{
			entityManager.find(contratante.getClass(), contratante.getId());
			entityManager.merge(contratante);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao atualizar contratante");
		}
		finally{
			entityManager.close();
		}
		
		return contratanteAtualizado;
	}
	
	public List<Contratante> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<Contratante> contratantes = null;
		
		try{
			TypedQuery<Contratante> typedQuery = entityManager.createQuery("SELECT contratante FROM Contratante contratante", Contratante.class);
			contratantes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar contratantes");
		}
		finally{
			entityManager.close();
		}
		
		return contratantes;
	}
	
	public Contratante getById(Long id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		Contratante contratante = null;
		
		try{
			contratante = entityManager.find(Contratante.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar contratante");
		}
		finally{
			entityManager.close();
		}
		
		return contratante;
	}
	
}