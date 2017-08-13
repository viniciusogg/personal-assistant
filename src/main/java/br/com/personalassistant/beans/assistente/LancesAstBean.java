package br.com.personalassistant.beans.assistente;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.entidades.Lance;
import br.com.personalassistant.entidades.OfertaServico;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AssistenteService;
import br.com.personalassistant.services.LanceService;
import br.com.personalassistant.services.OfertaServicoService;

@ViewScoped
@Named
public class LancesAstBean extends AbstractBean {

	private static final long serialVersionUID = -1758490246931547633L;

	@Inject private LanceService lanceService;
	@Inject private AssistenteService assistenteService;
	@Inject private OfertaServicoService ofertaServicoService;
	private List<Lance> lances;

	public void preRenderView(){
				
		try {
			Assistente assistente = this.assistenteService.getAssistenteByEmail(getEmailUsuarioLogado());
			this.lances = this.lanceService.getAllByIdAssistente(assistente.getPk().getId());
		} 
		catch (ServiceException | ObjetoNaoExisteException | NaoExistemObjetosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void removerLance(Lance lance){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		String msg = "";
		Severity severity = null;
		
		try {
			//Lance lance = this.lanceService.getById(id);
			
			OfertaServico oferta = this.ofertaServicoService.getById(lance.getOfertaServico().getId());
			
			int indice = 0;
			
			for(Lance l: oferta.getLances()){
				if(l.getId().equals(lance.getId())){
					indice = oferta.getLances().indexOf(l);
				}
			}
			
			oferta.getLances().remove(indice);
			
			this.ofertaServicoService.update(oferta);
			
			this.lanceService.delete(lance);
			
			msg = "Lance removido com sucesso";
			severity = FacesMessage.SEVERITY_INFO;
		} 
		catch (ServiceException | ObjetoNaoExisteException e) {
			msg = "Falha ao tentar remover o lance, recarregue a página e tente novamente";
			severity = FacesMessage.SEVERITY_ERROR;
		
			e.printStackTrace();
		}
	
		context.addMessage(null, new FacesMessage(severity, msg, ""));
	}
	
	public List<Lance> getLances() {
		return lances;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}

	
}
