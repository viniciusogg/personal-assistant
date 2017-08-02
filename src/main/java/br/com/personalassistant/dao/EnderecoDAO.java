package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Endereco;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public class EnderecoDAO extends DAO {

	private static final long serialVersionUID = -902709003677742503L;

	public void save(Endereco endereco) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(endereco);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar endereco");
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
		
		return enderecoAtualizado;
	}
	
	public List<Endereco> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<Endereco> enderecos = null;
		
		try{
			TypedQuery<Endereco> typedQuery = entityManager.createQuery("SELECT endereco FROM Endereco endereco", Endereco.class);
			enderecos = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(enderecos == null){
				throw new NaoExistemObjetosException("Não existem enderecos");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar enderecos");
		}
		
		return enderecos;
	}
	
	public Endereco getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		Endereco endereco = null;
		
		try{
			endereco = entityManager.find(Endereco.class, id);
		}
		catch(PersistenceException ex){
			
			if(endereco == null){
				throw new ObjetoNaoExisteException("Não existe endereco com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar endereco");
		}
		
		return endereco;
	}
	
	
}
