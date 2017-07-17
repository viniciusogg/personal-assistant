package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.CategoriaServicoDAO;
import br.com.personalassistant.entidades.CategoriaServico;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class CategoriaServicoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaServicoDAO categoriaServicoDAO;
	
	@Transacional
	public void save(CategoriaServico categoriaServico) throws ServiceException{
		try{
			this.categoriaServicoDAO.save(categoriaServico);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(CategoriaServico categoriaServico) throws ServiceException{
		try{
			this.categoriaServicoDAO.delete(categoriaServico);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public CategoriaServico update(CategoriaServico categoriaServico) throws ServiceException{
		try{
			return this.categoriaServicoDAO.update(categoriaServico);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public CategoriaServico getById(Long id) throws ServiceException{
		try{
			return this.categoriaServicoDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<CategoriaServico> getAll() throws ServiceException{
		try{
			return this.categoriaServicoDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public CategoriaServico getByName(String nome) throws ServiceException{
		try{
			return this.categoriaServicoDAO.getByName(nome);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
}
