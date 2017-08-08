package br.com.personalassistant.beans.contratante;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Lance;
import br.com.personalassistant.entidades.OfertaServico;
import br.com.personalassistant.services.AssistenteService;
import br.com.personalassistant.services.LanceService;
import br.com.personalassistant.services.ServicoService;

@Named
@ViewScoped
public class LancesOfertaCteBean extends AbstractBean {

	private static final long serialVersionUID = -4895432624706215868L;

	@Inject private AssistenteService assistenteService;
	@Inject private LanceService lanceService;
	@Inject private ServicoService servicoService;
	private OfertaServico ofertaServico;
	private Lance lance;
	
	public void preRenderView(){
		
		
	}

	public void aceitarLance(Long id){
		
	}
	
	public OfertaServico getOfertaServico() {
		return ofertaServico;
	}

	public void setOfertaServico(OfertaServico ofertaServico) {
		this.ofertaServico = ofertaServico;
	}

	public Lance getLance() {
		return lance;
	}

	public void setLance(Lance lance) {
		this.lance = lance;
	}
	
}
