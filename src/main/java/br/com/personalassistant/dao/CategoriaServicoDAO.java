package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.CategoriaServico;
import br.com.personalassistant.excecoes.PersistenciaException;

public class CategoriaServicoDAO extends DAO {

	public void save(CategoriaServico categoriaServico) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.persist(categoriaServico);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao salvar categoriaServico");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(CategoriaServico categoriaServico) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.remove(entityManager.getReference(categoriaServico.getClass(), categoriaServico.getId()));
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao remover categoriaServico");
		}
		finally{
			entityManager.close();
		}
	}
	
	public CategoriaServico update(CategoriaServico categoriaServico) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		CategoriaServico categoriaServicoAtualizada = categoriaServico; 
		
		try{
			entityManager.find(categoriaServico.getClass(), categoriaServico.getId());
			entityManager.merge(categoriaServico);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao atualizar categoriaServico");
		}
		finally{
			entityManager.close();
		}
		
		return categoriaServicoAtualizada;
	}
	
	public List<CategoriaServico> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<CategoriaServico> categoriasServicos = null;
		
		try{
			TypedQuery<CategoriaServico> typedQuery = entityManager.createQuery("SELECT categoriaServico FROM CategoriaServico categoriaServico", CategoriaServico.class);
			categoriasServicos = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar categoriasServicos");
		}
		finally{
			entityManager.close();
		}
		
		return categoriasServicos;
	}
	
	public CategoriaServico getById(Long id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		CategoriaServico categoriaServico = null;
		
		try{
			categoriaServico = entityManager.find(CategoriaServico.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar categoriaServico");
		}
		finally{
			entityManager.close();
		}
		
		return categoriaServico;
	}
	
}
