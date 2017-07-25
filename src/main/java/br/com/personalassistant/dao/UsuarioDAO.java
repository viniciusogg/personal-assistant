package br.com.personalassistant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.personalassistant.entidades.Usuario;
import br.com.personalassistant.enums.TIPO_USUARIO;
import br.com.personalassistant.excecoes.PersistenciaException;

public class UsuarioDAO extends DAO {

	private static final long serialVersionUID = 1L;

	public void save(Usuario usuario) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.persist(usuario);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao salvar usuario");
		}
		finally{
			entityManager.close();
		}
		
	}
	
	public void delete(Usuario usuario) throws PersistenciaException{	
		
		EntityManager entityManager = getEntityManager();
		
		try{
			entityManager.remove(entityManager.getReference(usuario.getClass(), usuario.getId()));
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao remover usuario");
		}
		finally{
			entityManager.close();
		}
	}
	
	public Usuario update(Usuario usuario) throws PersistenciaException{
		
		EntityManager entityManager = getEntityManager();
		Usuario usuarioAtualizado = usuario; 
		
		try{
			entityManager.find(usuario.getClass(), usuario.getId());
			entityManager.merge(usuario);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao atualizar usuario");
		}
		finally{
			entityManager.close();
		}
		
		return usuarioAtualizado;
	}
	
	public List<Usuario> getAll() throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		List<Usuario> usuarios = null;
		
		try{
			TypedQuery<Usuario> typedQuery = entityManager.createQuery("SELECT usuario FROM Usuario usuario", Usuario.class);
			usuarios = typedQuery.getResultList();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenceException("Erro ao recuperar usuarios");
		}
		finally{
			entityManager.close();
		}
		
		return usuarios;
	}
	
	public Usuario getById(Long id) throws PersistenciaException {
		
		EntityManager entityManager = getEntityManager();
		Usuario usuario = null;
		
		try{
			usuario = entityManager.find(Usuario.class, id);
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar usuario");
		}
		finally{
			entityManager.close();
		}
		
		return usuario;
	}
	
	public TIPO_USUARIO getTipoUsuarioByEmail(String email) throws PersistenciaException{
				
		EntityManager entityManager = getEntityManager();
		TIPO_USUARIO tipoUsuario = null;
	
		if (email == null) {
			email = "";
		}

		try{
			TypedQuery<TIPO_USUARIO> typedQuery = entityManager.createQuery("SELECT u.tipoUsuario FROM Usuario u WHERE u.email = :email", 
					TIPO_USUARIO.class);
			typedQuery.setParameter("email", email);			
			tipoUsuario = typedQuery.getSingleResult();
		}
		catch(PersistenceException ex){
			ex.printStackTrace();
			throw new PersistenciaException("Erro ao recuperar tipo usuario");
		}
		finally{
			entityManager.close();
		}
		
		return tipoUsuario;
	}
	
}
