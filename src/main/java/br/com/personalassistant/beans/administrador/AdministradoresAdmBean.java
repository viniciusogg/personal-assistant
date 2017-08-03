package br.com.personalassistant.beans.administrador;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Administrador;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AdministradorService;

@Named
@ViewScoped
public class AdministradoresAdmBean extends AbstractBean{

	private static final long serialVersionUID = -2172176872792566539L;

	@Inject private AdministradorService administradorService;
	private Administrador administrador;
	private List<Administrador> administradores;
	
	public void preRenderView(){
	
		try {
			this.administradores = administradorService.getAll();
		} 
		catch (ServiceException | NaoExistemObjetosException e) {
			e.printStackTrace();
		}
	}
	
	public void deletarContaAdministrador(){

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		String msg = "";
		Severity severity = null;
		
		String nomeAdministradorRemovido = this.administrador.getNome();
		
		try {
			Long idRemovido = this.administrador.getId();
						
			Long idAtual = administradorService.getIdByEmail(getEmailUsuarioLogado());
			
			boolean idsIguais = idRemovido.equals(idAtual);

			this.administradorService.delete(this.administrador);
			
			if(idsIguais){
				
				HttpSession httpSession = (HttpSession) context.getExternalContext().getSession(false);
				httpSession.invalidate();
				
				context.getExternalContext().redirect(context.getExternalContext().
						getApplicationContextPath() + "/login.xhtml?faces-redirect=true");
			}
			
			severity = FacesMessage.SEVERITY_INFO;
			msg = "Administrador '" + nomeAdministradorRemovido + "' removido com sucesso";
		} 
		catch (ServiceException | IOException | ObjetoNaoExisteException e) {
			severity = FacesMessage.SEVERITY_ERROR;
			msg = e.getMessage();
			
			e.printStackTrace();
		}
		
		context.addMessage(null, new FacesMessage(severity, msg, ""));
	}

	public List<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
}
