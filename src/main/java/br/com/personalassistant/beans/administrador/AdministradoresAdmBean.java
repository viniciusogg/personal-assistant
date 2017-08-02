package br.com.personalassistant.beans.administrador;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Administrador;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
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
		catch (ServiceException e) {
			e.printStackTrace();
		} 
		catch (NaoExistemObjetosException e) {}
	}
	
	public void deletarContaAdministrador(){

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		String nomeAdministradorRemovido = administrador.getNome();
		
		try {

			administradorService.delete(this.administrador);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador '" + nomeAdministradorRemovido + "' removido com sucesso", ""));
		} 
		catch (ServiceException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
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
