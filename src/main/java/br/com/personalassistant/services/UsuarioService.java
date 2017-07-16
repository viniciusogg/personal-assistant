package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.UsuarioDAO;
import br.com.personalassistant.entidades.Usuario;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Transacional
	public void save(Usuario usuario) throws ServiceException{
		try{
			this.usuarioDAO.save(usuario);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(Usuario usuario) throws ServiceException{
		try{
			this.usuarioDAO.delete(usuario);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public Usuario update(Usuario usuario) throws ServiceException{
		try{
			return this.usuarioDAO.update(usuario);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public Usuario getById(Long id) throws ServiceException{
		try{
			return this.usuarioDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Usuario> getAll() throws ServiceException{
		try{
			return this.usuarioDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}

}
