package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.ServicoDAO;
import br.com.personalassistant.entidades.PK;
import br.com.personalassistant.entidades.Servico;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class ServicoService implements Serializable {

	private static final long serialVersionUID = -4434549428409118202L;

	@Inject
	private ServicoDAO servicoDAO;
	
	@Transacional
	public void save(Servico servico) throws ServiceException{
		try{
			this.servicoDAO.save(servico);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(Servico servico) throws ServiceException{
		try{
			this.servicoDAO.delete(servico);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public Servico update(Servico servico) throws ServiceException{
		try{
			return this.servicoDAO.update(servico);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public Servico getById(Long id) throws ServiceException, ObjetoNaoExisteException{
		try{
			return this.servicoDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Servico> getAll() throws ServiceException, NaoExistemObjetosException{
		try{
			return this.servicoDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Servico> getAllByIdAssistente(PK id) throws NaoExistemObjetosException, ServiceException{
		try{
			return this.servicoDAO.getAllByIdAssistente(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}

	public List<Servico> getAllByIdContratante(Long id) throws NaoExistemObjetosException, ServiceException{
		try{
			return this.servicoDAO.getAllByIdContratante(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
}
