package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Servico;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public class ServicoDAO extends DAO {

	private static final long serialVersionUID = -902709003677742503L;

	public void save(Servico servico) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(servico);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar servico");
		}
	}
	
	public void delete(Servico servico) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(servico.getClass(), servico.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover servico");
		}
	}
	
	public Servico update(Servico servico) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Servico servicoAtualizado = servico; 
		
		try{
			entityManager.find(servico.getClass(), servico.getId());
			entityManager.merge(servico);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar servico");
		}
		
		return servicoAtualizado;
	}
	
	public List<Servico> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<Servico> servicos = null;
		
		try{
			TypedQuery<Servico> typedQuery = entityManager.createQuery("SELECT servico FROM Servico servico", Servico.class);
			servicos = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(servicos == null){
				throw new NaoExistemObjetosException("Não existem servicos");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar servicos");
		}
		
		return servicos;
	}
	
	public Servico getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		Servico servico = null;
		
		try{
			servico = entityManager.find(Servico.class, id);
		}
		catch(PersistenceException ex){
			
			if(servico == null){
				throw new ObjetoNaoExisteException("Não existe servico com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar servico");
		}
		
		return servico;
	}
	
	public List<Servico> getAllByIdAssistente(Long id) throws NaoExistemObjetosException, PersistenciaException{
		EntityManager entityManager = getEntityManager();
		List<Servico> servicos = null;
		
		try{
			TypedQuery<Servico> typedQuery = entityManager.createQuery("SELECT servico "
					+ "FROM Servico servico "
					+ "WHERE servico.assistente.id = :id", Servico.class);
			typedQuery.setParameter("id", id);
			
			servicos = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(servicos == null){
				throw new NaoExistemObjetosException("Não existem servicos");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar servicos");
		}
		
		return servicos;
	}
	
	public List<Servico> getAllByIdContratante(Long id) throws NaoExistemObjetosException, PersistenciaException{
		EntityManager entityManager = getEntityManager();
		List<Servico> servicos = null;
		
		try{
			TypedQuery<Servico> typedQuery = entityManager.createQuery("SELECT servico "
					+ "FROM Servico servico "
					+ "WHERE servico.contratante.id = :id", Servico.class);
			typedQuery.setParameter("id", id);
			
			servicos = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(servicos == null){
				throw new NaoExistemObjetosException("Não existem servicos");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar servicos");
		}
		
		return servicos;
	}
}
