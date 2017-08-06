package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.ArrayList;
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

	@Inject private ContratanteDAO contratanteDAO;
	
	@Inject private AvaliacaoContratanteService avaliacaoContratanteService;
	
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
			Contratante contratante = this.contratanteDAO.getById(id);
			
			return contratante;
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Contratante> getAll() throws ServiceException, NaoExistemObjetosException{
		try{
			List<Contratante> contratantes = this.contratanteDAO.getAll();
			
			for(Contratante c: contratantes){
				c.setReputacao(calcularReputacao(c.getId()));
			}
			
			return contratantes;
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public Contratante getContratanteByEmail(String email) throws ServiceException, ObjetoNaoExisteException{
		try{
			Contratante contratante = this.contratanteDAO.getContratanteByEmail(email);
			
			contratante.setReputacao(calcularReputacao(contratante.getId()));;
			
			return contratante;
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	private int calcularReputacao(Long id){
		
		int indiceMaior = 0;
		
		try {
			ArrayList<Double> valores = avaliacaoContratanteService.getAvaliacoesByIdContratante(id);
			
			if(valores == null || valores.isEmpty()){
				return 0;
			}
			
			indiceMaior = -1;
			Double maior = valores.get(0);
			
			for(int i = 1; i < valores.size(); i++){
				if(valores.get(i) > maior){
					indiceMaior = i;
					maior = valores.get(i);
				}
				else if(valores.get(i).equals(maior) && valores.get(i) != 0){
					indiceMaior = Math.round((indiceMaior + i) / 2);
				}
			}
		}
		
		catch (ServiceException e) {
			e.printStackTrace();
		} 
		catch (ObjetoNaoExisteException e) {
			e.printStackTrace();
		}
		
		if(indiceMaior == -1){
			return 0;
		}
		
		return indiceMaior + 1;
	}
	
}
