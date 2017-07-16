package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.LanceDAO;
import br.com.personalassistant.entidades.Lance;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class LanceService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LanceDAO lanceDAO;
	
	@Transacional
	public void save(Lance lance) throws ServiceException{
		try{
			this.lanceDAO.save(lance);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(Lance lance) throws ServiceException{
		try{
			this.lanceDAO.delete(lance);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public Lance update(Lance lance) throws ServiceException{
		try{
			return this.lanceDAO.update(lance);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public Lance getById(Long id) throws ServiceException{
		try{
			return this.lanceDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Lance> getAll() throws ServiceException{
		try{
			return this.lanceDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
}
