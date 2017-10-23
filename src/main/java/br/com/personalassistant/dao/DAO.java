package br.com.personalassistant.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.personalassistant.entidades.PK;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public abstract class DAO<T> implements Serializable{

	private static final long serialVersionUID = -5275558894052492460L;

	@Inject
	private EntityManager entityManager;
	
	private Class<T> type;
	
	protected EntityManager getEntityManager(){
		return this.entityManager;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DAO(){
		Type t = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) t;
		type = (Class) parameterizedType.getActualTypeArguments()[0];
	}
	
	public void save(T t) throws PersistenciaException{
				
		try{
			this.entityManager.persist(t);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar");
		}
	}
	
	public void delete(T t) throws PersistenciaException{	
				
		try{
			t = this.update(t);
			this.entityManager.remove(t);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover");
		}	
	}
	
	public T update(T t) throws PersistenciaException{
		
		T resultado = null; 
		
		try{
			resultado = this.entityManager.merge(t);
			this.entityManager.flush();
			this.entityManager.detach(t);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar assistente");
		}
		
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		List<T> t = null;
		
		try{
			Query query = this.entityManager.createQuery("SELECT t FROM " + type.getName() + " t");
			
			t = query.getResultList();
		}
		catch(PersistenceException ex){
			
			if(t == null){
				throw new NaoExistemObjetosException("Não existem entidades");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar entidades");
		}
		
		return t;
	}
	
	@SuppressWarnings({ "unchecked", "null" })
	public T getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		T t = null;
		
		try{			
			t = (T) this.entityManager.find(t.getClass(), id);
		}
		catch(PersistenceException ex){
			
			if(t == null){
				throw new ObjetoNaoExisteException("Não existe entidade com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar entidade");
		}
		
		return t;
	}
	
	public T getById(PK id) throws PersistenciaException, ObjetoNaoExisteException {
		
		T t = null;
		
		try{
			t = (T) this.entityManager.find(type, id);
		}
		catch(PersistenceException ex){
			
			if(t == null){
				throw new ObjetoNaoExisteException("Não existe entidade com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar entidade");
		}
		
		return t;
	}
	
	public Long generateId() throws NoResultException{

		Long id = null;
				
		try{			
			Query query = this.entityManager.createNativeQuery("SELECT sequence.next_val "
					+ "FROM hibernate_sequences sequence");
			
			String valor = String.valueOf(query.getSingleResult());
			
			id = Long.parseLong(valor);	
		}
		catch(NoResultException e){
			insertId();
			id = new Long(1);
		}
		
		return id;
	}
	
	public void updateSequenceId(){
		
		Long id = generateId();
		
		String novoId = Long.toString(id+1);
		
		Query query = this.entityManager.createNativeQuery("UPDATE hibernate_sequences "
				+ "SET next_val='"+novoId
				+ "' WHERE sequence_name='default'");
		
		query.executeUpdate();	
	}
	
	private void insertId(){
		
		Query query = this.entityManager.createNativeQuery("INSERT INTO hibernate_sequences "
				+ "(sequence_name, next_val) VALUES ('default', 1)");
		
		query.executeUpdate();
	}

}
