package br.com.personalassistant.services;

import java.io.Serializable;
import java.util.ArrayList;
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

	@Inject private AssistenteDAO assistenteDAO;
	@Inject private AvaliacaoAssistenteService avaliacaoAssistenteService;
	
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
			Assistente assistente = this.assistenteDAO.getById(id);
			
			assistente.setQuantServicosPrestados(calcularQuantServicosPrestados(assistente.getId()));
			assistente.setNivelExperiencia(calcularNivelExperiencia(assistente.getId()));
			assistente.setReputacao(calcularReputacao(assistente.getId()));
			
			return assistente;
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Assistente> getAll() throws ServiceException, NaoExistemObjetosException{
		try{
			List<Assistente> assistentes = this.assistenteDAO.getAll();
			
			for(Assistente a: assistentes){
				a.setQuantServicosPrestados(calcularQuantServicosPrestados(a.getId()));
				a.setNivelExperiencia(calcularNivelExperiencia(a.getId()));
				a.setReputacao(calcularReputacao(a.getId()));
			}
			
			return assistentes;
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
			Assistente assistente =  this.assistenteDAO.getAssistenteByEmail(email);
			
			assistente.setQuantServicosPrestados(calcularQuantServicosPrestados(assistente.getId()));
			assistente.setNivelExperiencia(calcularNivelExperiencia(assistente.getId()));
			assistente.setReputacao(calcularReputacao(assistente.getId()));
			
			return assistente;
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

	
	/*public List<Assistente> getAllSemPropostasDoContratante(Long idContratante) throws ServiceException{
		try {
			return this.assistenteDAO.getAllSemPropostasDoContratante(idContratante);
		} 
		catch (PersistenciaException | NaoExistemObjetosException e) {
			throw new ServiceException(e.getMessage());
		}
	}*/
	
	private int calcularReputacao(Long id){
		
		int indiceMaior = 0;
		
		try {
			ArrayList<Double> valores = avaliacaoAssistenteService.getAvaliacoesByIdAssistente(id);
			
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
	
	private String calcularNivelExperiencia(Long id){
	
		Long experiencia = calcularQuantServicosPrestados(id);
		
		if(experiencia <= 50){
			return "INEXPERIENTE";
		}
		else if(experiencia > 50 && experiencia <= 100){
			return "POUCA EXPERIENCIA";
		}
		else if(experiencia > 100 && experiencia <= 200){
			return "EXPERIENTE";
		}
		else{
			return "MUITO EXPERIENTE";
		}
	}

	private Long calcularQuantServicosPrestados(Long id){
		
		Long experiencia = null;
		
		try {
			experiencia = getQuantidadeServicosById(id);
		} 
		catch (ServiceException e) {
			e.printStackTrace();
		}
		catch (ObjetoNaoExisteException e) {
			e.printStackTrace();
		}
		
		return experiencia;
	}
}
