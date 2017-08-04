package br.com.personalassistant.beans.contratante;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.ContratanteService;

@Named
@ViewScoped
public class PerfilCteBean extends AbstractBean {

	private static final long serialVersionUID = 3570137008332816L;

	@Inject private ContratanteService contratanteService;
	private Contratante contratante;
	private boolean isEdicao;
	private boolean renderizarPainelVisualizacao = true;
	
	public void preRenderView(){
		
		this.isEdicao = false;
		
		String email = getEmailUsuarioLogado();
		
		try {
			this.contratante = contratanteService.getContratanteByEmail(email);
		} 
		catch (ServiceException | ObjetoNaoExisteException e) {
			e.printStackTrace();
		}
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
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
			this.contratanteService.update(this.contratante);
			
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
	
	public void removerContaContratante(){

		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			
			this.contratanteService.delete(this.contratante);
							
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
