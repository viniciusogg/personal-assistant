package br.com.personalassistant.beans.contratante;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Lance;
import br.com.personalassistant.entidades.OfertaServico;
import br.com.personalassistant.entidades.Servico;
import br.com.personalassistant.enums.ESTADO_LANCE;
import br.com.personalassistant.enums.ESTADO_OFERTA;
import br.com.personalassistant.excecoes.ServiceException;
//import br.com.personalassistant.services.AssistenteService;
//import br.com.personalassistant.services.LanceService;
import br.com.personalassistant.services.OfertaServicoService;
import br.com.personalassistant.services.ServicoService;

@Named
@ViewScoped
public class LancesOfertaCteBean extends AbstractBean {

	private static final long serialVersionUID = -4895432624706215868L;

	//@Inject private AssistenteService assistenteService;
	@Inject private OfertaServicoService ofertaServicoService;
	@Inject private ServicoService servicoService;
	private OfertaServico ofertaServico;
	//private Lance lance;
	
	public void preRenderView(){
		
		
	}

	public void aceitarLance(Long id){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		String msg = "";
		Severity severity = null;
		
		try{
			Lance lance = null;

			for(Lance l: ofertaServico.getLances()){
				if(l.getId().equals(id)){
					l.setStatus(ESTADO_LANCE.ACEITO);
					lance = l;
				}
				else{
					l.setStatus(ESTADO_LANCE.RECUSADO);
				}
			}
			this.ofertaServico.setStatus(ESTADO_OFERTA.ARREMATADA);
		
			this.ofertaServicoService.update(this.ofertaServico);
			
			Servico servico = new Servico(lance.getValorLance(), lance.getTipoPagamento(), 
					this.ofertaServico.getCategoriaServico(), lance.getAssistente(), this.ofertaServico.getContratante(), 
					lance.getDataRealizacaoServico(), this.ofertaServico.getEndereco());
		
			this.servicoService.save(servico);
			
			msg = "Aceitou o lance com sucesso";
			severity = FacesMessage.SEVERITY_INFO;
		}		
		catch(ServiceException e){
			msg = "Ocorreu um erro ao tentar aceitar o lance, recarregue a página e tente novamente";
			severity = FacesMessage.SEVERITY_ERROR;
		
			e.printStackTrace();
		}
		
		context.addMessage(null, new FacesMessage(severity, msg, ""));
	
		try {
			context.getExternalContext().redirect((context.getExternalContext().getApplicationContextPath() 
						+ "/contratante/index.xhtml"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public OfertaServico getOfertaServico() {
		return ofertaServico;
	}

	public void setOfertaServico(OfertaServico ofertaServico) {
		this.ofertaServico = ofertaServico;
	}

	/*public Lance getLance() {
		return lance;
	}

	public void setLance(Lance lance) {
		this.lance = lance;
	}*/
	
}
