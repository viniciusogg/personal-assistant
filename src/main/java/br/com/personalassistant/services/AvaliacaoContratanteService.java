package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.AvaliacaoContratanteDAO;
import br.com.personalassistant.entidades.AvaliacaoContratante;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class AvaliacaoContratanteService implements Serializable {

	private static final long serialVersionUID = 2338169367472613909L;
	
	@Inject
	private AvaliacaoContratanteDAO avaliacaoContratanteDAO;
	
	@Transacional
	public void save(AvaliacaoContratante avaliacaoContratante) throws ServiceException{
		try{
			this.avaliacaoContratanteDAO.save(avaliacaoContratante);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(AvaliacaoContratante avaliacaoContratante) throws ServiceException{
		try{
			this.avaliacaoContratanteDAO.delete(avaliacaoContratante);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public AvaliacaoContratante update(AvaliacaoContratante avaliacaoContratante) throws ServiceException{
		try{
			return this.avaliacaoContratanteDAO.update(avaliacaoContratante);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public AvaliacaoContratante getById(Long id) throws ServiceException{
		try{
			return this.avaliacaoContratanteDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<AvaliacaoContratante> getAll() throws ServiceException{
		try{
			return this.avaliacaoContratanteDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
}
