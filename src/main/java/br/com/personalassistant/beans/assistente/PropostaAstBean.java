package br.com.personalassistant.beans.assistente;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Proposta;
import br.com.personalassistant.services.PropostaService;

@Named
@ViewScoped
public class PropostaAstBean extends AbstractBean {

	private static final long serialVersionUID = -2763195489293393140L;

	//@Inject private PropostaService propostaService;
	
	private Proposta proposta;
	
	public void preRenderView(){
		
	}
	
	public Proposta getProposta() {
		return proposta;
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}
	
	
}
