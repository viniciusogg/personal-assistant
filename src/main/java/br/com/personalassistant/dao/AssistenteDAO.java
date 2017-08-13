package br.com.personalassistant.dao;

import java.util.List;

//import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
//import br.com.personalassistant.entidades.PK;
//import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public class AssistenteDAO extends DAO<Assistente> {

	private static final long serialVersionUID = 3088908627019097676L;

	/*public void save(Assistente assistente) throws PersistenciaException{
		
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
	}*/
	
	public List<Assistente> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<Assistente> assistentes = null;
		
		try{
			TypedQuery<Assistente> typedQuery = entityManager.createQuery("SELECT ast "
					+ "FROM Assistente ast, Usuario usr "
					+ "WHERE ast.pk.id = usr.pk.id "
					+ "AND usr.senha <> 'null'", Assistente.class);
			
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
	
	/*public Assistente getById(PK id) throws PersistenciaException, ObjetoNaoExisteException {
		
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
	}*/
	
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
					+ "FROM Assistente a "
					+ "WHERE a.email = :email", Assistente.class);
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
			TypedQuery<Long> typedQuery = entityManager.createQuery("SELECT COUNT(s) "
					+ "FROM Servico s, Usuario u "
					+ "WHERE s.status = 'CONCLUIDO' "
					+ "AND s.assistente.pk.id = :id "
					+ "AND s.assistente.pk.id = u.pk.id "
					+ "AND u.senha <> 'null'", Long.class);
			
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
	
	/*public List<Assistente> getAllSemPropostasDoContratante(Long idContratante) throws NaoExistemObjetosException, PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		List<Assistente> assistentes = null;
		
		try{
			TypedQuery<Assistente> typedQuery = entityManager.createQuery("SELECT ast "
					+ "FROM Assistente ast, Usuario usr, Contratante c, IN(c.propostas) p "
					+ "WHERE ast.id = usr.id "
					+ "AND ast.id <> p.assistente.id "
					+ "AND p.contratante.id = :idContratante", Assistente.class);
			
			typedQuery.setParameter("idContratante", idContratante);
			
//			/*
//			 * 
//			 * "SELECT ast "
//					+ "FROM Assistente ast, Usuario usr, Contratante c, IN(c.propostas) p "
//					+ "WHERE c.id = :idContratante "
//					+ "AND ast.id = usr.id "
//					+ "AND p.assistente.id <> ast.id"
//			 * 
//			 * 
//			 *	vou na tabela propostas, verifico se o contratante é igual ao que passei, (tem que
//			 *	ser diferente)
//			 * 
//			 * 
//			 * SELECT ast FROM Assistente ast, Usuario usr, Proposta p
//			 * WHERE ast.id = usr.id
//			 * AND p.contratante.id <> :
//			 * 
//			 * 
//			 * SELECT ast From Assistente ast, Contratante c, IN(c.propostas) p
//			 * WHERE ast.id <> p.assistente.id
//			 * AND c.id <> p.contratante
//			 * 
			 
						
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
	}*/
	
	
}
