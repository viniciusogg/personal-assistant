package br.com.personalassistant.dao;

import java.util.ArrayList;
//import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.AvaliacaoContratante;
import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.entidades.PK;
//import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public class AvaliacaoContratanteDAO extends DAO<AvaliacaoContratante> {

	private static final long serialVersionUID = -902709003677742503L;

	/*public void save(AvaliacaoContratante avaliacaoContratante) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(avaliacaoContratante);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar avaliacaoContratante");
		}
	}
	
	public void delete(AvaliacaoContratante avaliacaoContratante) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(avaliacaoContratante.getClass(), avaliacaoContratante.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover avaliacaoContratante");
		}
	}
	
	public AvaliacaoContratante update(AvaliacaoContratante avaliacaoContratante) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		AvaliacaoContratante avaliacaoContratanteAtualizado = avaliacaoContratante; 
		
		try{
			entityManager.find(avaliacaoContratante.getClass(), avaliacaoContratante.getId());
			entityManager.merge(avaliacaoContratante);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar avaliacaoContratante");
		}
		
		return avaliacaoContratanteAtualizado;
	}
	
	public List<AvaliacaoContratante> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<AvaliacaoContratante> avaliacoesContratantes = null;
		
		try{
			TypedQuery<AvaliacaoContratante> typedQuery = entityManager.createQuery("SELECT avaliacaoContratante FROM AvaliacaoContratante avaliacaoContratante", AvaliacaoContratante.class);
			avaliacoesContratantes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(avaliacoesContratantes == null){
				throw new NaoExistemObjetosException("Não existem avaliacoesContratantes");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar avaliacoesContratantes");
		}
		
		return avaliacoesContratantes;
	}
	
	public AvaliacaoContratante getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		AvaliacaoContratante avaliacaoContratante = null;
		
		try{
			avaliacaoContratante = entityManager.find(AvaliacaoContratante.class, id);
		}
		catch(PersistenceException ex){
			
			if(avaliacaoContratante == null){
				throw new ObjetoNaoExisteException("Não existe avaliacaoContratante com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar avaliacaoContratante");
		}
		
		return avaliacaoContratante;
	}*/
	
	public Long getTotalAvaliacoesById(PK id) throws ObjetoNaoExisteException, PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Long total = new Long(0);
		
		try{
			TypedQuery<Long> consultaTotal = entityManager.createQuery("SELECT COUNT(ac) "
					+ "FROM AvaliacaoContratante ac, Servico s "
					+ "WHERE s.contratante.pk.id = :id AND "
					+ "AND s.avaliacaoContratanteRecebeu.id = ac.id "
					+ "AND s.status = 'CONCLUIDO'", Long.class);
			consultaTotal.setParameter("id", id.getId());
			consultaTotal.setParameter("ultimaAtualizacao", id.getUltimaAtualizacao());
			total = consultaTotal.getSingleResult();
		}
		catch(PersistenceException ex){
			
			if(total == null){
				throw new ObjetoNaoExisteException("Não existe avaliacaoContratante com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar avaliacaoContratante");
		}
		
		return total;
	}
	
	public ArrayList<Double> getAvaliacoesByIdContratante(PK id) throws PersistenciaException, ObjetoNaoExisteException{

		EntityManager entityManager = getEntityManager();
		
		Double umaEstrela = 0.0;
		Double duasEstrelas = 0.0;
		Double tresEstrelas = 0.0;
		Double quatroEstrelas = 0.0;
		Double cincoEstrelas = 0.0;
		
		ArrayList<Double> valores = new ArrayList<Double>();
		
		try{
			Long totalAvaliacoes = getTotalAvaliacoesById(id);
			
			TypedQuery<Long> consultaUm = entityManager.createQuery("SELECT COUNT(ac) "
					+ "FROM AvaliacaoContratante ac, Servico s "
					+ "WHERE ac.mediaAvaliacao = 1 "
					+ "AND s.contratante.id = :id AND s.contratante.ultimaAtualizacao = :ultimaAtualizacao "
					+ "AND s.avaliacaoContratanteRecebeu.id = ac.id "
					+ "AND s.status = 'CONCLUIDO'", Long.class);
			consultaUm.setParameter("id", id.getId());
			consultaUm.setParameter("ultimaAtualizacao", id.getUltimaAtualizacao());

			if(consultaUm.getSingleResult() != null && consultaUm.getSingleResult() != 0.0){
				umaEstrela = (double) ((consultaUm.getSingleResult()/totalAvaliacoes) * 100);
			}
			
			TypedQuery<Long> consultaDois = entityManager.createQuery("SELECT COUNT(a) "
					+ "FROM AvaliacaoContratante ac, Servico s "
					+ "WHERE ac.mediaAvaliacao = 2 "
					+ "AND s.contratante.id = :id AND s.contratante.ultimaAtualizacao = :ultimaAtualizacao "
					+ "AND s.avaliacaoContratanteRecebeu.id = ac.id "
					+ "AND s.status = 'CONCLUIDO'", Long.class);
			consultaDois.setParameter("id", id.getId());
			consultaDois.setParameter("ultimaAtualizacao", id.getUltimaAtualizacao());
			
			if(consultaDois.getSingleResult() != null && consultaDois.getSingleResult() != 0.0){
				duasEstrelas = (double) (consultaDois.getSingleResult()/totalAvaliacoes) * 100;
			}
			
			TypedQuery<Long> consultaTres = entityManager.createQuery("SELECT COUNT(ac) "
					+ "FROM AvaliacaoContratante ac, Servico s "
					+ "WHERE ac.mediaAvaliacao = 3 "
					+ "AND s.contratante.id = :id AND s.contratante.ultimaAtualizacao = :ultimaAtualizacao "
					+ "AND s.avaliacaoContratanteRecebeu.id = ac.id "
					+ "AND s.status = 'CONCLUIDO'", Long.class);
			consultaTres.setParameter("id", id.getId());
			consultaTres.setParameter("ultimaAtualizacao", id.getUltimaAtualizacao());
			
			if(consultaTres.getSingleResult() != null && consultaTres.getSingleResult() != 0.0){
				tresEstrelas = (double) (consultaTres.getSingleResult()/totalAvaliacoes) * 100;
			}
			
			TypedQuery<Long> consultaQuatro = entityManager.createQuery("SELECT COUNT(ac) "
					+ "FROM AvaliacaoContratante ac, Servico s "
					+ "WHERE ac.mediaAvaliacao = 4 "
					+ "AND s.contratante.id = :id AND s.contratante.ultimaAtualizacao = :ultimaAtualizacao "
					+ "AND s.avaliacaoContratanteRecebeu.id = ac.id "
					+ "AND s.status = 'CONCLUIDO'", Long.class);
			consultaQuatro.setParameter("id", id.getId());
			consultaQuatro.setParameter("ultimaAtualizacao", id.getUltimaAtualizacao());
			
			if(consultaQuatro.getSingleResult() != null && consultaQuatro.getSingleResult() != 0.0){
				quatroEstrelas = (double) (consultaQuatro.getSingleResult()/totalAvaliacoes) * 100;
			}
			
			TypedQuery<Long> consultaCinco = entityManager.createQuery("SELECT COUNT(ac) "
					+ "FROM AvaliacaoContratante ac, Servico s "
					+ "WHERE ac.mediaAvaliacao = 5 "
					+ "AND s.contratante.id = :id AND s.contratante.ultimaAtualizacao = :ultimaAtualizacao "
					+ "AND s.avaliacaoContratanteRecebeu.id = ac.id "
					+ "AND s.status = 'CONCLUIDO'", Long.class);
			consultaCinco.setParameter("id", id.getId());
			consultaCinco.setParameter("ultimaAtualizacao", id.getUltimaAtualizacao());
			
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
			throw new PersistenciaException("Erro ao recuperar contratante");
		}

		return valores;
	}
	
	
}
