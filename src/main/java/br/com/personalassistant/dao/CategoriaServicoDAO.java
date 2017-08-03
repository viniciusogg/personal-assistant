package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.CategoriaServico;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;

public class CategoriaServicoDAO extends DAO {

	private static final long serialVersionUID = -902709003677742503L;

	public void save(CategoriaServico categoriaServico) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(categoriaServico);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar categoriaServico");
		}
	}
	
	public void delete(CategoriaServico categoriaServico) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(categoriaServico.getClass(), categoriaServico.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover categoriaServico");
		}
	}
	
	public CategoriaServico update(CategoriaServico categoriaServico) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		CategoriaServico categoriaServicoAtualizada = categoriaServico; 
		
		try{
			entityManager.find(categoriaServico.getClass(), categoriaServico.getId());
			entityManager.merge(categoriaServico);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar categoriaServico");
		}
		
		return categoriaServicoAtualizada;
	}
	
	public List<CategoriaServico> getAll() throws PersistenciaException, NaoExistemObjetosException {
		
		EntityManager entityManager = getEntityManager();
		List<CategoriaServico> categoriasServicos = null;
		
		try{
			TypedQuery<CategoriaServico> typedQuery = entityManager.createQuery("SELECT categoriaServico FROM CategoriaServico categoriaServico", CategoriaServico.class);
			categoriasServicos = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			
			if(categoriasServicos == null){
				throw new NaoExistemObjetosException("Não existem categoriasServicos");
			}
			
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar categoriasServicos");
		}
		
		return categoriasServicos;
	}
	
	public CategoriaServico getById(Long id) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		CategoriaServico categoriaServico = null;
		
		try{
			categoriaServico = entityManager.find(CategoriaServico.class, id);
		}
		catch(PersistenceException ex){
			
			if(categoriaServico == null){
				throw new ObjetoNaoExisteException("Não existe categoriaServico com este id");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar categoriaServico");
		}
		
		return categoriaServico;
	}
	
	public CategoriaServico getByName(String nome) throws PersistenciaException, ObjetoNaoExisteException {
		
		EntityManager entityManager = getEntityManager();
		CategoriaServico categoriaServico = null;
		
		try{
			TypedQuery<CategoriaServico> typedQuery = entityManager.createQuery("SELECT cs FROM CategoriaServico cs WHERE cs.nome = :nome", 
					CategoriaServico.class);
			typedQuery.setParameter("nome", nome);			
			categoriaServico = typedQuery.getSingleResult();
		}
		catch(PersistenceException ex){
			
			if(categoriaServico == null){
				throw new ObjetoNaoExisteException("Não existe categoriaServico com este nome");
			}
			
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar categoriaServico");
		}
		
		return categoriaServico;
	}
	
}
