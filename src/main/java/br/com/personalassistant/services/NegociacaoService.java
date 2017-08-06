package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.NegociacaoDAO;
import br.com.personalassistant.entidades.Negociacao;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class NegociacaoService implements Serializable {

	private static final long serialVersionUID = 1268020450368279924L;
	
	@Inject
	private NegociacaoDAO negociacaoDAO;
	
	@Transacional
	public void save(Negociacao negociacao) throws ServiceException{
		try{
			this.negociacaoDAO.save(negociacao);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(Negociacao negociacao) throws ServiceException{
		try{
			this.negociacaoDAO.delete(negociacao);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public Negociacao update(Negociacao negociacao) throws ServiceException{
		try{
			return this.negociacaoDAO.update(negociacao);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public Negociacao getById(Long id) throws ServiceException, ObjetoNaoExisteException{
		try{
			return this.negociacaoDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Negociacao> getAll() throws ServiceException, NaoExistemObjetosException{
		try{
			return this.negociacaoDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}


}
