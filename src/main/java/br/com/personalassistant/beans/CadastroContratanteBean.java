package br.com.personalassistant.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.ContratanteService;

@ViewScoped
@Named
public class CadastroContratanteBean implements Serializable{

	@Inject
	private ContratanteService contratanteService;
	
	private static final long serialVersionUID = 1L;
	private Contratante contratante;
	
	public void preRenderView(){
				
		if(this.contratante == null){
			this.contratante = new Contratante();
		}
	}
	
	public void salvarContratante(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			contratanteService.save(contratante);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com sucesso", ""));
		} 
		catch (ServiceException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro", ""));
			e.printStackTrace();
		}
	}

	public Contratante getContratante() {
		return contratante;
	}
	
	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}
}
