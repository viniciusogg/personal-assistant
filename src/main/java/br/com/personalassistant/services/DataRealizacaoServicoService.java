package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.DataRealizacaoServicoDAO;
import br.com.personalassistant.entidades.DataRealizacaoServico;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class DataRealizacaoServicoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DataRealizacaoServicoDAO dataRealizacaoServicoDAO;
	
	@Transacional
	public void save(DataRealizacaoServico dataRealizacaoServico) throws ServiceException{
		try{
			this.dataRealizacaoServicoDAO.save(dataRealizacaoServico);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(DataRealizacaoServico dataRealizacaoServico) throws ServiceException{
		try{
			this.dataRealizacaoServicoDAO.delete(dataRealizacaoServico);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public DataRealizacaoServico update(DataRealizacaoServico dataRealizacaoServico) throws ServiceException{
		try{
			return this.dataRealizacaoServicoDAO.update(dataRealizacaoServico);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public DataRealizacaoServico getById(Long id) throws ServiceException{
		try{
			return this.dataRealizacaoServicoDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<DataRealizacaoServico> getAll() throws ServiceException{
		try{
			return this.dataRealizacaoServicoDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
}
