package br.com.personalassistant.beans.administrador;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AssistenteService;

@Named
@ViewScoped
public class AssistentesAdmBean extends AbstractBean{

	private static final long serialVersionUID = -2787290505709243196L;

	@Inject private AssistenteService assistenteService;
	private Assistente assistente;
	private List<Assistente> assistentes;
	
	public void preRenderView(){
	
		try {
			this.assistentes = assistenteService.getAll();
		} 
		catch (ServiceException e) {
			e.printStackTrace();
		} 
		catch (NaoExistemObjetosException e) {}
	}
	
	public void deletarContaAssistente(){
		System.out.println("Chegou no deletar conta");
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		String nomeAssistenteRemovido = assistente.getNome();
		
		try {
			System.out.println(this.assistente.toString());
			assistenteService.delete(this.assistente);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Assistente '" + nomeAssistenteRemovido + "' removido com sucesso", ""));
		} 
		catch (ServiceException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
	}

	public List<Assistente> getAssistentes() {
		return assistentes;
	}

	public void setAssistentes(List<Assistente> assistentes) {
		this.assistentes = assistentes;
	}

	public Assistente getAssistente() {
		return assistente;
	}

	public void setAssistente(Assistente assistente) {
		this.assistente = assistente;
	}
	
}
