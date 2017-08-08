package br.com.personalassistant.services;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.OfertaServicoDAO;
import br.com.personalassistant.entidades.OfertaServico;
import br.com.personalassistant.enums.ESTADO_OFERTA;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class OfertaServicoService implements Serializable {

	private static final long serialVersionUID = 1378054134834893952L;

	@Inject
	private OfertaServicoDAO ofertaServicoDAO;
	
	@Transacional
	public void save(OfertaServico ofertaServico) throws ServiceException{
		try{
			this.ofertaServicoDAO.save(ofertaServico);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(OfertaServico ofertaServico) throws ServiceException{
		try{
			this.ofertaServicoDAO.delete(ofertaServico);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public OfertaServico update(OfertaServico ofertaServico) throws ServiceException{
		try{
			return this.ofertaServicoDAO.update(ofertaServico);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public OfertaServico getById(Long id) throws ServiceException, ObjetoNaoExisteException{
		try{
			return this.ofertaServicoDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<OfertaServico> getAll() throws ServiceException, NaoExistemObjetosException{
		try{
			return this.ofertaServicoDAO.getAll();			
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<OfertaServico> getAllByIdCategoriaServico(Long id) throws ServiceException, NaoExistemObjetosException{
		try{
			List<OfertaServico> ofertas = this.ofertaServicoDAO.getAllByIdCategoriaServico(id);
			
			List<OfertaServico> ofertasValidas = new ArrayList<OfertaServico>();
			
			for(OfertaServico o: ofertas){
				if(!this.isDataOfertaValida(o.getDataFinalOferta())){
					o.setStatus(ESTADO_OFERTA.EXPIRADA);
					this.update(o);
				}
				else{					
					ofertasValidas.add(o);
				}
			}
			
			return ofertasValidas;
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<OfertaServico> getAllByIdContratante(Long id) throws ServiceException, NaoExistemObjetosException{
		try{
			List<OfertaServico> ofertas = this.ofertaServicoDAO.getAllByIdContratante(id);
			
			List<OfertaServico> ofertasValidas = new ArrayList<OfertaServico>();
			
			for(OfertaServico o: ofertas){
				if(!this.isDataOfertaValida(o.getDataFinalOferta())){
					o.setStatus(ESTADO_OFERTA.EXPIRADA);
					this.update(o);
				}
				else{					
					ofertasValidas.add(o);
				}
			}
			
			return ofertasValidas;
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	private boolean isDataOfertaValida(Date dataFinalProposta){
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		Date dataAtual =  calendar.getTime();
		
		SimpleDateFormat formatoDefault = new SimpleDateFormat("yyyy-MM-dd");
		String dataAtualFormatada = formatoDefault.format(dataAtual);
		
		try {
			dataAtual = formatoDefault.parse(dataAtualFormatada);

			if(dataAtual.equals(dataFinalProposta) || dataAtual.after(dataFinalProposta)){
				return false;
			}
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
