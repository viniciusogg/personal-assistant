package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public class ContratanteDAO extends DAO {

	private static final long serialVersionUID = -902709003677742503L;

	public void save(Contratante contratante) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(contratante);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar contratante");
		}
	}
	
	public void delete(Contratante contratante) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(contratante.getClass(), contratante.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover contratante");
		}
	}
	
	public Contratante update(Contratante contratante) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Contratante contratanteAtualizado = contratante; 
		
		try{
			entityManager.find(contratante.getClass(), contratante.getId());
			entityManager.merge(contratante);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar contratante");
		}
		
		return contratanteAtualizado;
	}
	
	public List<Contratante> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<Contratante> contratantes = null;
		
		try{
			TypedQuery<Contratante> typedQuery = entityManager.createQuery("SELECT cte "
					+ "FROM Contratante cte, Usuario usr "
					+ "WHERE cte.id = usr.id", Contratante.class);
			
			contratantes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(contratantes == null){
				throw new NaoExistemObjetosException("Não existem contratantes");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar contratantes");
		}
		
		return contratantes;
	}
	
	public Contratante getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		Contratante contratante = null;
		
		try{
			TypedQuery<Contratante> typedQuery = entityManager.createQuery("SELECT cte "
					+ "FROM Contratante cte, Usuario usr WHERE ast.id = usr.id "
					+ "AND cte.id = :id", Contratante.class);
			
			typedQuery.setParameter("id", id);
			
			contratante = typedQuery.getSingleResult();
		}
		catch(PersistenceException ex){
			
			if(contratante == null){
				throw new ObjetoNaoExisteException("Não existe contratante com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar contratante");
		}
		
		return contratante;
	}
	
	public Contratante getContratanteByEmail(String email) throws PersistenciaException, ObjetoNaoExisteException{
		
		EntityManager entityManager = getEntityManager();
		Contratante contratante = null;
	
		if (email == null) {
			email = "";
		}

		try{
			TypedQuery<Contratante> typedQuery = entityManager.createQuery("SELECT c "
					+ "FROM Contratante c, Usuario u "
					+ "WHERE c.id = u.id AND "
					+ "u.email = :email", Contratante.class);
			
			typedQuery.setParameter("email", email);			
			
			contratante = typedQuery.getSingleResult();
		}
		catch(PersistenceException ex){
			
			if(contratante == null){
				throw new ObjetoNaoExisteException("Não existe assistente com este email");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar assistente");
		}
		
		return contratante;
	}
	
	
	
}
