package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Usuario;
import br.com.personalassistant.excecoes.PersistenciaException;

public class UsuarioDAO extends DAO {

	public void save(Usuario usuario) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.persist(usuario);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao salvar usuario");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(Usuario usuario) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try{
			entityManager.remove(usuario);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao remover usuario");
		}
		finally{
			entityManager.close();
		}
	}
	
	public Usuario update(Usuario usuario) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Usuario administradorAtualizado = usuario; 
		
		try{
			entityManager.find(usuario.getClass(), usuario.getId());
			entityManager.merge(usuario);
			entityTransaction.commit();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			entityTransaction.rollback();
			throw new PersistenciaException("Erro ao atualizar usuario");
		}
		finally{
			entityManager.close();
		}
		
		return administradorAtualizado;
	}
	
	public List<Usuario> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<Usuario> usuarios = null;
		
		try{
			TypedQuery<Usuario> typedQuery = entityManager.createQuery("SELECT usuario FROM Usuario usuario", Usuario.class);
			usuarios = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar usuarios");
		}
		finally{
			entityManager.close();
		}
		
		return usuarios;
	}
	
	public Usuario getById(Integer id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		Usuario usuario = null;
		
		try{
			usuario = entityManager.find(Usuario.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar usuario");
		}
		finally{
			entityManager.close();
		}
		
		return usuario;
	}
	
}
