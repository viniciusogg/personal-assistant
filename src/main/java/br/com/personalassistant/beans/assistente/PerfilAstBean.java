package br.com.personalassistant.beans.assistente;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AssistenteService;

@Named
@ViewScoped
public class PerfilAstBean extends AbstractBean {

	private static final long serialVersionUID = 6168998604562800790L;

	@Inject private AssistenteService assistenteService;
	private Assistente assistente;
	private boolean isEdicao;
	private boolean renderizarPainelVisualizacao = true;
	
	public void preRenderView(){
		
		this.isEdicao = false;
		
		String email = getEmailUsuarioLogado();
		
		try {
			this.assistente = assistenteService.getAssistenteByEmail(email);
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
	
	public void editarPerfil(){
		this.isEdicao = true;
		this.renderizarPainelVisualizacao = false;
	}
	
	public void concluirEdicao(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		Severity severity = null;
		String msg = "";
		
		try {
			this.assistenteService.update(this.assistente);
			
			msg = "Perfil atualizado com sucesso";
			severity = FacesMessage.SEVERITY_INFO;
		}
		catch (ServiceException e) {
			msg = "Ocorreu uma falha ao tentar atualizar o perfil";
			severity = FacesMessage.SEVERITY_ERROR;
			e.printStackTrace();
		}
		
		context.addMessage(null, new FacesMessage(severity, msg, ""));				
	}
	
	public void removerContaAssistente(){

		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			
			this.assistenteService.delete(this.assistente);
							
			HttpSession httpSession = (HttpSession) context.getExternalContext().getSession(false);
			httpSession.invalidate();
			
			context.getExternalContext().redirect(context.getExternalContext().
					getApplicationContextPath() + "/login.xhtml?faces-redirect=true");
		} 
		catch (ServiceException | IOException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		
	}
	
	public void alterarSenha(){
		
	}

	public boolean isRenderizarPainelVisualizacao() {
		return renderizarPainelVisualizacao;
	}

	public void setRenderizarPainelVisualizacao(boolean renderizarPainelVisualizacao) {
		this.renderizarPainelVisualizacao = renderizarPainelVisualizacao;
	}
		
}
