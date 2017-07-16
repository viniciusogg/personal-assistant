package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.AdministradorDAO;
import br.com.personalassistant.entidades.Administrador;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class AdministradorService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AdministradorDAO administradorDAO;
	
	@Transacional
	public void save(Administrador administrador) throws ServiceException{
		try{
			this.administradorDAO.save(administrador);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(Administrador administrador) throws ServiceException{
		try{
			this.administradorDAO.delete(administrador);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public Administrador update(Administrador administrador) throws ServiceException{
		try{
			return this.administradorDAO.update(administrador);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public Administrador getById(Long id) throws ServiceException{
		try{
			return this.administradorDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Administrador> getAll() throws ServiceException{
		try{
			return this.administradorDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}

}
