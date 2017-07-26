package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.AvaliacaoAssistenteDAO;
import br.com.personalassistant.entidades.AvaliacaoAssistente;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class AvaliacaoAssisteneService implements Serializable {

	private static final long serialVersionUID = -7337585144022967065L;

	@Inject
	private AvaliacaoAssistenteDAO avaliacaoAssistenteDAO;
	
	@Transacional
	public void save(AvaliacaoAssistente avaliacaoAssistente) throws ServiceException{
		try{
			this.avaliacaoAssistenteDAO.save(avaliacaoAssistente);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(AvaliacaoAssistente avaliacaoAssistente) throws ServiceException{
		try{
			this.avaliacaoAssistenteDAO.delete(avaliacaoAssistente);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public AvaliacaoAssistente update(AvaliacaoAssistente avaliacaoAssistente) throws ServiceException{
		try{
			return this.avaliacaoAssistenteDAO.update(avaliacaoAssistente);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public AvaliacaoAssistente getById(Long id) throws ServiceException{
		try{
			return this.avaliacaoAssistenteDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<AvaliacaoAssistente> getAll() throws ServiceException{
		try{
			return this.avaliacaoAssistenteDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
}
