package br.com.personalassistant.beans.administrador;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.ContratanteService;

@Named
@ViewScoped
public class ContratantesAdmBean extends AbstractBean{

	private static final long serialVersionUID = 1809885253101330882L;

	@Inject private ContratanteService contratanteService;
	private Contratante contratante;
	private List<Contratante> contratantes;
	
	public void preRenderView(){
	
		try {
			this.contratantes = contratanteService.getAll();
		} 
		catch (ServiceException e) {
			e.printStackTrace();
		} 
		catch (NaoExistemObjetosException e) {}
	}
	
	public void deletarContaContratante(){

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		String nomeContratanteRemovido = contratante.getNome();
		
		try {

			contratanteService.delete(this.contratante);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contratante '" + nomeContratanteRemovido + "' removido com sucesso", ""));
		} 
		catch (ServiceException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
	}

	public List<Contratante> getContratantes() {
		return contratantes;
	}

	public void setContratantes(List<Contratante> contratantes) {
		this.contratantes = contratantes;
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}
}
