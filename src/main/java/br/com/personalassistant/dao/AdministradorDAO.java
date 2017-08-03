package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Administrador;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public class AdministradorDAO extends DAO {

	private static final long serialVersionUID = -902709003677742503L;

	public void save(Administrador administrador) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(administrador);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar administrador");
		}
	}
	
	public void delete(Administrador administrador) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(administrador.getClass(), administrador.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover administrador");
		}
	}
	
	public Administrador update(Administrador administrador) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Administrador administradorAtualizado = administrador; 
		
		try{
			entityManager.find(administrador.getClass(), administrador.getId());
			entityManager.merge(administrador);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar administrador");
		}
		
		return administradorAtualizado;
	}
	
	public List<Administrador> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<Administrador> administradores = null;
		
		try{
			TypedQuery<Administrador> typedQuery = entityManager.createQuery("SELECT administrador FROM Administrador administrador", Administrador.class);
			administradores = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(administradores == null){
				throw new NaoExistemObjetosException("Não existem administradores");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar administradores");
		}
		
		return administradores;
	}
	
	public Administrador getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		Administrador administrador = null;
		
		try{
			administrador = entityManager.find(Administrador.class, id);
		}
		catch(PersistenceException ex){
			
			if(administrador == null){
				throw new ObjetoNaoExisteException("Não existe administrador com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar administrador");
		}
		
		return administrador;
	}
	
	public Long getIdByEmail(String email) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		Long id = null;
		
		try{
			TypedQuery<Long> typedQuery = entityManager.createQuery("SELECT a.id "
					+ "FROM Administrador a "
					+ "WHERE a.email = :email", Long.class);
			
			typedQuery.setParameter("email", email);
			
			id = typedQuery.getSingleResult();
		}
		catch(PersistenceException ex){
			
			if(id == null){
				throw new ObjetoNaoExisteException("Não existe administrador com este email");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar administradores");
		}
		
		return id;
	}
	
	
}
