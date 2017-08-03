package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.AssistenteDAO;
import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class AssistenteService implements Serializable {

	private static final long serialVersionUID = 3541164974033649233L;

	@Inject
	private AssistenteDAO assistenteDAO;
	
	@Transacional
	public void save(Assistente assistente) throws ServiceException{
		try{
			this.assistenteDAO.save(assistente);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(Assistente assistente) throws ServiceException{
		try{
			this.assistenteDAO.delete(assistente);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public Assistente update(Assistente assistente) throws ServiceException{
		try{
			return this.assistenteDAO.update(assistente);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public Assistente getById(Long id) throws ServiceException, ObjetoNaoExisteException{
		try{
			return this.assistenteDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Assistente> getAll() throws ServiceException, NaoExistemObjetosException{
		try{
			return this.assistenteDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public Long getQuantidadeByCategoriaServico(String nomeCategoriaServico) throws ServiceException, ObjetoNaoExisteException{
		try{
			return this.assistenteDAO.getQuantidadeByCategoriaServico(nomeCategoriaServico);
		}
		catch (PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public Assistente getAssistenteByEmail(String email) throws ServiceException, ObjetoNaoExisteException{
		try {
			return this.assistenteDAO.getAssistenteByEmail(email);
		} 
		catch (PersistenciaException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Long getQuantidadeServicosById(Long id) throws ServiceException, ObjetoNaoExisteException{
		try {
			return this.assistenteDAO.getQuantidadeServicosById(id);
		} 
		catch (PersistenciaException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<Assistente> getAllSemPropostasDoContratante(Long idContratante) throws ServiceException{
		try {
			return this.assistenteDAO.getAllSemPropostasDoContratante(idContratante);
		} 
		catch (PersistenciaException | NaoExistemObjetosException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
