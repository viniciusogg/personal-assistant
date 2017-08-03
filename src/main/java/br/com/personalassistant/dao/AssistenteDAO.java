package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public class AssistenteDAO extends DAO {

	private static final long serialVersionUID = 3088908627019097676L;

	public void save(Assistente assistente) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(assistente);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar assistente");
		}
	}
	
	public void delete(Assistente assistente) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(assistente.getClass(), assistente.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover assistente");
		}	
	}
	
	public Assistente update(Assistente assistente) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Assistente assistenteAtualizado = assistente; 
		
		try{
			entityManager.find(assistente.getClass(), assistente.getId());
			entityManager.merge(assistente);			
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar assistente");
		}
		
		return assistenteAtualizado;
	}
	
	public List<Assistente> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<Assistente> assistentes = null;
		
		try{
			TypedQuery<Assistente> typedQuery = entityManager.createQuery("SELECT ast "
					+ "FROM Assistente ast, Usuario usr "
					+ "WHERE ast.id = usr.id", Assistente.class);
			
			assistentes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(assistentes == null){
				throw new NaoExistemObjetosException("Não existem assistentes");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar assistentes");
		}
		
		return assistentes;
	}
	
	public Assistente getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		Assistente assistente = null;
		
		try{			
			TypedQuery<Assistente> typedQuery = entityManager.createQuery("SELECT ast "
					+ "FROM Assistente ast, Usuario usr WHERE ast.id = usr.id "
					+ "AND ast.id = :id", Assistente.class);
			
			typedQuery.setParameter("id", id);
			
			assistente = typedQuery.getSingleResult();
		}
		catch(PersistenceException ex){
			
			if(assistente == null){
				throw new ObjetoNaoExisteException("Não existe assistente com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar assistente");
		}
		
		return assistente;
	}
	
	public Long getQuantidadeByCategoriaServico(String nomeCategoriaServico) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		Long quantidade = null;
		
		try{			
			TypedQuery<Long> typedQuery = entityManager.createQuery("SELECT COUNT(ast) "
					+ "FROM Assistente ast "
					+ "WHERE ast.categoriaServico.nome = :nomeCategoriaServico", Long.class);
			
			typedQuery.setParameter("nomeCategoriaServico", nomeCategoriaServico);
			
			quantidade = typedQuery.getSingleResult();
		}
		catch(PersistenceException ex){
			
			if(quantidade == null){
				throw new ObjetoNaoExisteException("Não existe assistente com esta categoria de serviço");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar assistente");
		}
		
		return quantidade;
	}
	
	public Assistente getAssistenteByEmail(String email) throws PersistenciaException, ObjetoNaoExisteException{
		
		EntityManager entityManager = getEntityManager();
		Assistente assistente = null;
	
		if (email == null) {
			email = "";
		}

		try{
			TypedQuery<Assistente> typedQuery = entityManager.createQuery("SELECT a "
					+ "FROM Assistente a, Usuario u "
					+ "WHERE a.id = u.id AND "
					+ "u.email = :email", Assistente.class);
			
			typedQuery.setParameter("email", email);			
			
			assistente = typedQuery.getSingleResult();
		}
		catch(PersistenceException ex){
			
			if(assistente == null){
				throw new ObjetoNaoExisteException("Não existe assistente com este email");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar assistente");
		}
		
		return assistente;
	}
	
	public Long getQuantidadeServicosById(Long id) throws ObjetoNaoExisteException, PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Long quantidade = null;
		
		try{			
			TypedQuery<Long> typedQuery = entityManager.createQuery("SELECT COUNT(servico) "
					+ "FROM Servico servico "
					+ "WHERE servico.assistente.id = :id "
					+ "AND servico.status = 'CONCLUIDO'", Long.class);
			
			typedQuery.setParameter("id", id);
			
			quantidade = typedQuery.getSingleResult();
		}
		catch(PersistenceException ex){
			
			if(quantidade == null){
				throw new ObjetoNaoExisteException("Não existe assistente com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar assistente");
		}
		
		return quantidade;
	}
	
	public List<Assistente> getAllSemPropostasDoContratante(Long idContratante) throws NaoExistemObjetosException, PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		List<Assistente> assistentes = null;
		
		try{
			TypedQuery<Assistente> typedQuery = entityManager.createQuery("SELECT ast "
					+ "FROM Assistente ast, Usuario usr, Contratante c, IN(c.propostas) p"
					+ "WHERE c.id = :idContratante"
					+ "AND ast.id = usr.id "
					+ "AND p.assistente.id <> ast.id", Assistente.class);
			
			//+ "AND c.id = :idContratante "

			
			//SELECIONE TODOS OS OS ASSISTENTES QUE NÃO ESTÃO NAS PROPOSTAS DO CONTRATANTE;
			
			typedQuery.setParameter("idContratante", idContratante);
			
			assistentes = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(assistentes == null){
				throw new NaoExistemObjetosException("Não existem assistentes");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar assistentes");
		}
		
		return assistentes;
	}
}
