package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.OfertaServicoDAO;
import br.com.personalassistant.entidades.OfertaServico;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class OfertaServicoService implements Serializable {

	private static final long serialVersionUID = 1378054134834893952L;

	@Inject
	private OfertaServicoDAO ofertaServicoDAO;
	
	@Transacional
	public void save(OfertaServico ofertaServico) throws ServiceException{
		try{
			this.ofertaServicoDAO.save(ofertaServico);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(OfertaServico ofertaServico) throws ServiceException{
		try{
			this.ofertaServicoDAO.delete(ofertaServico);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public OfertaServico update(OfertaServico ofertaServico) throws ServiceException{
		try{
			return this.ofertaServicoDAO.update(ofertaServico);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public OfertaServico getById(Long id) throws ServiceException{
		try{
			return this.ofertaServicoDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<OfertaServico> getAll() throws ServiceException{
		try{
			return this.ofertaServicoDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
}
