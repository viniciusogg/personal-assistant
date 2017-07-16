package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.CapacidadeDAO;
import br.com.personalassistant.entidades.Capacidade;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class CapacidadeService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CapacidadeDAO capacidadeDAO;
	
	@Transacional
	public void save(Capacidade capacidade) throws ServiceException{
		try{
			this.capacidadeDAO.save(capacidade);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(Capacidade capacidade) throws ServiceException{
		try{
			this.capacidadeDAO.delete(capacidade);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public Capacidade update(Capacidade capacidade) throws ServiceException{
		try{
			return this.capacidadeDAO.update(capacidade);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public Capacidade getById(Long id) throws ServiceException{
		try{
			return this.capacidadeDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Capacidade> getAll() throws ServiceException{
		try{
			return this.capacidadeDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
}
