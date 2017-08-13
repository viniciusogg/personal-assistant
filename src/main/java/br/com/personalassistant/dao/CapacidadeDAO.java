package br.com.personalassistant.dao;

/*import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;*/

import br.com.personalassistant.entidades.Capacidade;
/*import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;*/

public class CapacidadeDAO extends DAO<Capacidade> {

	private static final long serialVersionUID = -902709003677742503L;

	/*public void save(Capacidade capacidade) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(capacidade);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar capacidade");
		}
	}
	
	public void delete(Capacidade capacidade) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(capacidade.getClass(), capacidade.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover capacidade");
		}
	}
	
	public Capacidade update(Capacidade capacidade) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Capacidade capacidadeAtualizado = capacidade; 
		
		try{
			entityManager.find(capacidade.getClass(), capacidade.getId());
			entityManager.merge(capacidade);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar capacidade");
		}
		
		return capacidadeAtualizado;
	}
	
	public List<Capacidade> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<Capacidade> capacidades = null;
		
		try{
			TypedQuery<Capacidade> typedQuery = entityManager.createQuery("SELECT capacidade FROM Capacidade capacidade", Capacidade.class);
			capacidades = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(capacidades == null){
				throw new NaoExistemObjetosException("Não existem capacidades");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar capacidades");
		}
		
		return capacidades;
	}
	
	public Capacidade getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		Capacidade capacidade = null;
		
		try{
			capacidade = entityManager.find(Capacidade.class, id);
		}
		catch(PersistenceException ex){
			
			if(capacidade == null){
				throw new ObjetoNaoExisteException("Não existe capacidade com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar capacidade");
		}
		
		return capacidade;
	}*/
	
	
}
