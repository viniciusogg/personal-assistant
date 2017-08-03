package br.com.personalassistant.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.AvaliacaoAssistente;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public class AvaliacaoAssistenteDAO extends DAO {

	private static final long serialVersionUID = -902709003677742503L;

	public void save(AvaliacaoAssistente avaliacaoAssistente) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(avaliacaoAssistente);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar avaliacaoAssistente");
		}
	}
	
	public void delete(AvaliacaoAssistente avaliacaoAssistente) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(avaliacaoAssistente.getClass(), avaliacaoAssistente.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover avaliacaoAssistente");
		}
	}
	
	public AvaliacaoAssistente update(AvaliacaoAssistente avaliacaoAssistente) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		AvaliacaoAssistente avaliacaoAssistenteAtualizado = avaliacaoAssistente; 
		
		try{
			entityManager.find(avaliacaoAssistente.getClass(), avaliacaoAssistente.getId());
			entityManager.merge(avaliacaoAssistente);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar avaliacaoAssistente");
		}
		
		return avaliacaoAssistenteAtualizado;
	}
	
	public List<AvaliacaoAssistente> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<AvaliacaoAssistente> avaliacoesAssistentes = null;
		
		try{
			TypedQuery<AvaliacaoAssistente> typedQuery = entityManager.createQuery("SELECT avaliacaoAssistente FROM AvaliacaoAssistente avaliacaoAssistente", AvaliacaoAssistente.class);
			avaliacoesAssistentes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(avaliacoesAssistentes == null){
				throw new NaoExistemObjetosException("Não existem avaliacoesAssistentes");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar avaliacoesAssistentes");
		}
		
		return avaliacoesAssistentes;
	}
	
	public AvaliacaoAssistente getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		AvaliacaoAssistente avaliacaoAssistente = null;
		
		try{
			avaliacaoAssistente = entityManager.find(AvaliacaoAssistente.class, id);
		}
		catch(PersistenceException ex){
			
			if(avaliacaoAssistente == null){
				throw new ObjetoNaoExisteException("Não existe avaliacaoAssistente com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar avaliacaoAssistente");
		}
		
		return avaliacaoAssistente;
	}
	
	public Long getTotalAvaliacoesById(Long id) throws ObjetoNaoExisteException, PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Long total = new Long(0);
		
		try{
			TypedQuery<Long> consultaTotal = entityManager.createQuery("SELECT COUNT(a) "
					+ "FROM AvaliacaoAssistente a, Servico s "
					+ "WHERE s.assistente.id = :id "
					+ "AND s.avaliacaoAssistenteRecebeu.id = a.id "
					+ "AND s.status = 'CONCLUIDO'", Long.class);
			consultaTotal.setParameter("id", id);
			total = consultaTotal.getSingleResult();
		}
		catch(PersistenceException ex){
			
			if(total == null){
				throw new ObjetoNaoExisteException("Não existe avaliacaoAssistente com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar avaliacaoAssistente");
		}
		
		return total;
	}
	
	public ArrayList<Double> getAvaliacoesByIdAssistente(Long id) throws PersistenciaException, ObjetoNaoExisteException{

		EntityManager entityManager = getEntityManager();
		
		Double umaEstrela = 0.0;
		Double duasEstrelas = 0.0;
		Double tresEstrelas = 0.0;
		Double quatroEstrelas = 0.0;
		Double cincoEstrelas = 0.0;
		
		ArrayList<Double> valores = new ArrayList<Double>();
		
		try{
			Long totalAvaliacoes = getTotalAvaliacoesById(id);
			
			TypedQuery<Long> consultaUm = entityManager.createQuery("SELECT COUNT(a) "
					+ "FROM AvaliacaoAssistente a, Servico s "
					+ "WHERE a.mediaAvaliacao = 1 "
					+ "AND s.assistente.id = :id "
					+ "AND s.avaliacaoAssistenteRecebeu.id = a.id "
					+ "AND s.status = 'CONCLUIDO'", Long.class);
			consultaUm.setParameter("id", id);
			
			if(consultaUm.getSingleResult() != null && consultaUm.getSingleResult() != 0.0){
				umaEstrela = (double) ((consultaUm.getSingleResult()/totalAvaliacoes) * 100);
			}
			
			TypedQuery<Long> consultaDois = entityManager.createQuery("SELECT COUNT(a) "
					+ "FROM AvaliacaoAssistente a, Servico s "
					+ "WHERE a.mediaAvaliacao = 2 "
					+ "AND s.assistente.id = :id "
					+ "AND s.avaliacaoAssistenteRecebeu.id = a.id "
					+ "AND s.status = 'CONCLUIDO'", Long.class);
			consultaDois.setParameter("id", id);
			
			if(consultaDois.getSingleResult() != null && consultaDois.getSingleResult() != 0.0){
				duasEstrelas = (double) (consultaDois.getSingleResult()/totalAvaliacoes) * 100;				
			}
			
			TypedQuery<Long> consultaTres = entityManager.createQuery("SELECT COUNT(a) "
					+ "FROM AvaliacaoAssistente a, Servico s "
					+ "WHERE a.mediaAvaliacao = 3 "
					+ "AND s.assistente.id = :id "
					+ "AND s.avaliacaoAssistenteRecebeu.id = a.id "
					+ "AND s.status = 'CONCLUIDO'", Long.class);
			consultaTres.setParameter("id", id);

			if(consultaTres.getSingleResult() != null && consultaTres.getSingleResult() != 0.0){
				tresEstrelas = (double) (consultaTres.getSingleResult()/totalAvaliacoes) * 100;
			}
			
			TypedQuery<Long> consultaQuatro = entityManager.createQuery("SELECT COUNT(a) "
					+ "FROM AvaliacaoAssistente a, Servico s "
					+ "WHERE a.mediaAvaliacao = 4 "
					+ "AND s.assistente.id = :id "
					+ "AND s.avaliacaoAssistenteRecebeu.id = a.id "
					+ "AND s.status = 'CONCLUIDO'", Long.class);
			consultaQuatro.setParameter("id", id);
			
			if(consultaQuatro.getSingleResult() != null && consultaQuatro.getSingleResult() != 0.0){
				quatroEstrelas = (double) (consultaQuatro.getSingleResult()/totalAvaliacoes) * 100;
			}
			
			TypedQuery<Long> consultaCinco = entityManager.createQuery("SELECT COUNT(a) "
					+ "FROM AvaliacaoAssistente a, Servico s "
					+ "WHERE a.mediaAvaliacao = 5 "
					+ "AND s.assistente.id = :id "
					+ "AND s.avaliacaoAssistenteRecebeu.id = a.id "
					+ "AND s.status = 'CONCLUIDO'", Long.class);
			consultaCinco.setParameter("id", id);
			
			if(consultaCinco.getSingleResult() != null && consultaCinco.getSingleResult() != 0.0){
				cincoEstrelas = (double) (consultaCinco.getSingleResult()/totalAvaliacoes) * 100;
			}
			
			valores.add(umaEstrela);
			valores.add(duasEstrelas);
			valores.add(tresEstrelas);
			valores.add(quatroEstrelas);
			valores.add(cincoEstrelas);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar assistente");
		}
		
		return valores;
	}
	
}

