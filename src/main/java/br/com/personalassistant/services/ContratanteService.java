package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.ContratanteDAO;
import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class ContratanteService implements Serializable {

	private static final long serialVersionUID = 890576624707587650L;

	@Inject
	private ContratanteDAO contratanteDAO;
	
	@Transacional
	public void save(Contratante contratante) throws ServiceException{
		try{
			this.contratanteDAO.save(contratante);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(Contratante contratante) throws ServiceException{
		try{
			this.contratanteDAO.delete(contratante);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public Contratante update(Contratante contratante) throws ServiceException{
		try{
			return this.contratanteDAO.update(contratante);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public Contratante getById(Long id) throws ServiceException, ObjetoNaoExisteException{
		try{
			return this.contratanteDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Contratante> getAll() throws ServiceException, NaoExistemObjetosException{
		try{
			return this.contratanteDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
}
