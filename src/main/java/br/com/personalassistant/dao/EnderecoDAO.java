package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Endereco;
import br.com.personalassistant.excecoes.PersistenciaException;

public class EnderecoDAO extends DAO {

	private static final long serialVersionUID = -1426464964947275048L;

	public void save(Endereco endereco) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(endereco);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar endereco");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(Endereco endereco) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(endereco.getClass(), endereco.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover endereco");
		}
		finally{
			entityManager.close();
		}
	}
	
	public Endereco update(Endereco endereco) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Endereco enderecoAtualizado = endereco; 
		
		try{
			entityManager.find(endereco.getClass(), endereco.getId());
			entityManager.merge(endereco);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar endereco");
		}
		finally{
			entityManager.close();
		}
		
		return enderecoAtualizado;
	}
	
	public List<Endereco> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<Endereco> enderecos = null;
		
		try{
			TypedQuery<Endereco> typedQuery = entityManager.createQuery("SELECT endereco FROM Endereco endereco", Endereco.class);
			enderecos = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar enderecos");
		}
		finally{
			entityManager.close();
		}
		
		return enderecos;
	}
	
	public Endereco getById(Long id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		Endereco endereco = null;
		
		try{
			endereco = entityManager.find(Endereco.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar endereco");
		}
		finally{
			entityManager.close();
		}
		
		return endereco;
	}
	
}
