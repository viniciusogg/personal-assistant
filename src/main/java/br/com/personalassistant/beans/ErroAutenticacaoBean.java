package br.com.personalassistant.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

@Named
@RequestScoped
public class ErroAutenticacaoBean extends AbstractBean {

	private static final long serialVersionUID = 7809292460597830107L;

	public void reportarMensagemErro(boolean error){
		
		if(error){
			
			FacesContext context = FacesContext.getCurrentInstance();
			Flash flash = context.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail ou senha incorreto(a)", ""));			
			flash.clear();
		}
	}
}
