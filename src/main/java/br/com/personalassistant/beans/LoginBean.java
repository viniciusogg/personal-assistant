package br.com.personalassistant.beans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.com.personalassistant.entidades.Usuario;
import br.com.personalassistant.enums.TIPO_USUARIO;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.UsuarioService;

@Named
@ViewScoped
public class LoginBean extends AbstractBean{

	/*@Inject private UsuarioService usuarioService;
	private static final long serialVersionUID = 1L;
	private String email;
	private String senha;
	
	public void entrar() throws IOException {
		
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
		try {
			
			request.login(this.email, this.senha);
			
			//System.out.println("email: " + email);
			//System.out.println("senha: " + senha);
			
			Usuario usuario = usuarioService.getByEmail(this.email);
			
			//System.out.println(usuario.toString());
			
			if(usuario != null){	
				
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-vindo(a)", ""));
				
				externalContext.getSessionMap().put("usuario", usuario);
				
				if(usuario.getTipoUsuario().equals(TIPO_USUARIO.ADMINISTRADOR)){
					externalContext.redirect("/administrador/index?faces-redirect=true");				
				}
				else if(usuario.getTipoUsuario().equals(TIPO_USUARIO.ASSISTENTE)){
					externalContext.redirect("/assistente/index?faces-redirect=true");
				}
				else if(usuario.getTipoUsuario().equals(TIPO_USUARIO.CONTRATANTE)){
					externalContext.redirect("/contratante/index?faces-redirect=true");
				}
				else{
					externalContext.redirect("/login_error?faces-redirect=true");
				}
			}								
		}
		catch (ServletException e) {
			e.printStackTrace();
		}
		catch (ServiceException e) {
			e.printStackTrace();
		}		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}*/
	
}
