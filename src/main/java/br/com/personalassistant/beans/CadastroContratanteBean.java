package br.com.personalassistant.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.entidades.Endereco;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.ContratanteService;

@ViewScoped
@Named
public class CadastroContratanteBean implements Serializable{

	@Inject private ContratanteService contratanteService;
	private static final long serialVersionUID = 1L;
	private Contratante contratante;
	private Endereco endereco;
	
	public void preRenderView(){
				
		if(this.contratante == null){
			this.contratante = new Contratante();
		}
		
		if(this.endereco == null){
			this.endereco = new Endereco();
		}
	}
	
	public void salvarContratante(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			this.contratante.setEndereco(this.endereco);
			this.contratanteService.save(this.contratante);
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
