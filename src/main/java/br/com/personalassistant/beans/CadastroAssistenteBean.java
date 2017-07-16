package br.com.personalassistant.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AssistenteService;

public class CadastroAssistenteBean implements Serializable {

	@Inject
	private AssistenteService assistenteService;
	
	private static final long serialVersionUID = 1L;
	private Assistente assistente;
	
	public void preRenderView(){
				
		if(this.assistente == null){
			this.assistente = new Assistente();
		}
	}
	
	public void salvarAssistente(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			assistenteService.save(this.assistente);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com sucesso", ""));
		} 
		catch (ServiceException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro", ""));
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
