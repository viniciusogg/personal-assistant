package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.AvaliacaoDAO;
import br.com.personalassistant.entidades.Avaliacao;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class AvaliacaoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AvaliacaoDAO avaliacaoDAO;
	
	@Transacional
	public void save(Avaliacao avaliacao) throws ServiceException{
		try{
			this.avaliacaoDAO.save(avaliacao);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(Avaliacao avaliacao) throws ServiceException{
		try{
			this.avaliacaoDAO.delete(avaliacao);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public Avaliacao update(Avaliacao avaliacao) throws ServiceException{
		try{
			return this.avaliacaoDAO.update(avaliacao);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public Avaliacao getById(Long id) throws ServiceException{
		try{
			return this.avaliacaoDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Avaliacao> getAll() throws ServiceException{
		try{
			return this.avaliacaoDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
}
