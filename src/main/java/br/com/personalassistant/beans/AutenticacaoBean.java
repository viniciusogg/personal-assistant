package br.com.personalassistant.beans;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.personalassistant.enums.TIPO_USUARIO;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.UsuarioService;

@Named
@RequestScoped
public class AutenticacaoBean extends AbstractBean {

	private static final long serialVersionUID = 7809292460597830107L;

	@Inject private UsuarioService usuarioService;
	private TIPO_USUARIO tipoUsuario;
	
	public void redirecionarUsuario() {
		
		try {

			String email = getEmailUsuarioLogado();
			
			if(!email.equals("")){
				this.tipoUsuario = usuarioService.getTipoUsuarioByEmail(email);									
			}
			
			FacesContext facesContext = FacesContext.getCurrentInstance();


			if(this.tipoUsuario != null){
				
				String endereco = null;
				
				if(this.tipoUsuario.equals(TIPO_USUARIO.ADMINISTRADOR)){
					endereco =  facesContext.getExternalContext().getApplicationContextPath() + 
							"/administrador/index.xhtml?faces-redirect=true";
				}
				else if(this.tipoUsuario.equals(TIPO_USUARIO.ASSISTENTE)){			
					endereco =  facesContext.getExternalContext().getApplicationContextPath() + 
							"/assistente/index.xhtml?faces-redirect=true";
				}
				else if(this.tipoUsuario.equals(TIPO_USUARIO.CONTRATANTE)){
					endereco =  facesContext.getExternalContext().getApplicationContextPath() + 
							"/contratante/index.xhtml?faces-redirect=true";			
				}
								
				this.reportarMensagemSucesso();
				
				facesContext.getExternalContext().redirect(endereco);
			}
			else{
				facesContext.getExternalContext().redirect(facesContext.getExternalContext().
						getApplicationContextPath() + "/login.xhtml?faces-redirect=true");
			}	
		}
		catch (ServiceException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void efetuarLogout() throws IOException {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		ExternalContext externalContext = facesContext.getExternalContext();
		
		HttpSession httpSession = (HttpSession) externalContext.getSession(false);
		httpSession.invalidate();
		
		externalContext.redirect(externalContext.getApplicationContextPath() + 
				"/autenticacao.xhtml?faces-redirect=true");
	}
	
	public void reportarMensagemErro(boolean error){
		this.reportarMensagem(error, false);
	}
	
	public void reportarMensagemSucesso(){
		this.reportarMensagem(false, true);
	}
	
	protected void reportarMensagem(boolean error, boolean autenticou){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		Flash flash = context.getExternalContext().getFlash();
		flash.setKeepMessages(true);

		if(error){		
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail ou senha incorreto(a)", ""));					
			flash.clear();
		}
		else if(!error && autenticou){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-vindo(a) !", ""));					
		}
		
	}
}
