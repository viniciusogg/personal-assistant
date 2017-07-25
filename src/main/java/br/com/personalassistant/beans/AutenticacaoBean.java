package br.com.personalassistant.beans;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.personalassistant.services.UsuarioService;
import br.com.personalassistant.enums.TIPO_USUARIO;
import br.com.personalassistant.excecoes.ServiceException;

@Named
@RequestScoped
public class AutenticacaoBean extends AbstractBean{

	private static final long serialVersionUID = 2981381517732112808L;
	private TIPO_USUARIO tipoUsuario;
	private String email;
	@Inject private UsuarioService usuarioService;
	
	public String redirecionarUsuario() {
		
		try {

			this.email = getEmailUsuarioLogado();
			
			if(!this.email.equals("")){
				this.tipoUsuario = usuarioService.getTipoUsuarioByEmail(this.email);									
			}
		}
		catch (ServiceException e) {
			e.printStackTrace();
		}
		
		if(this.tipoUsuario != null){
			
			if(this.tipoUsuario.equals(TIPO_USUARIO.ADMINISTRADOR)){
				return "/administrador/index?faces-redirect=true";
			}
			else if(this.tipoUsuario.equals(TIPO_USUARIO.ASSISTENTE)){			
				return "/assistente/index?faces-redirect=true";
			}
			else if(this.tipoUsuario.equals(TIPO_USUARIO.CONTRATANTE)){
				return "/contratante/index?faces-redirect=true";			
			}
		}
		
		return "/login?faces-redirect=true";
	}
	
	public void efetuarLogout() throws IOException {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpSession httpSession = (HttpSession) externalContext.getSession(false);
		httpSession.invalidate();
		externalContext.redirect(externalContext.getApplicationContextPath() + 
				"/index.xhtml?faces-redirect=true");
	}
	
}
