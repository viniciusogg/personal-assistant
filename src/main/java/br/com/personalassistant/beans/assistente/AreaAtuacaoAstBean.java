package br.com.personalassistant.beans.assistente;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AssistenteService;

@Named
@ViewScoped
public class AreaAtuacaoAstBean extends AbstractBean {

	private static final long serialVersionUID = -79280093168767924L;

	private Assistente assistente;
	@Inject private AssistenteService assistenteService;
	
	public void preRenderView(){
		try {
			this.assistente = this.assistenteService.getAssistenteByEmail(getEmailUsuarioLogado());
		}
		catch (ServiceException | ObjetoNaoExisteException e) {
			e.printStackTrace();
		}
	}

	public Assistente getAssistente() {
		return assistente;
	}

	public void setAssistente(Assistente assistente) {
		this.assistente = assistente;
	}
	
	
	
}
