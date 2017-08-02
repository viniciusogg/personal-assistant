package br.com.personalassistant.validadores;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.entidades.Usuario;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.UsuarioService;

@Named
@RequestScoped
@FacesValidator("emailValidator")
public class EmailValidador implements Serializable, Validator{

	private static final long serialVersionUID = 1617028916089967695L;

	@Inject private UsuarioService usuarioService;
	
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String email = value.toString().trim();
		
		String emailValido = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";	
		
		String msgErro = null;
		
		if(!email.matches(emailValido)){
			msgErro = "O e-mail digitado não é válido";
			//((UIInput) component).setValid(false);
		}
		else{
			try {
				Usuario usuario = usuarioService.getUsuarioByEmail(email);
				
				if(usuario != null){
					msgErro = "Este e-mail já está cadastrado";
					//((UIInput) component).setValid(false);
				}
			} 
			catch (ServiceException e) {
				e.printStackTrace();
			}
			catch (ObjetoNaoExisteException e) {}
			
		}
		
		if(msgErro != null){
			
			FacesMessage facesMessage = new FacesMessage(msgErro);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			//context.addMessage(component.getClientId(context), facesMessage);
			throw new ValidatorException(facesMessage);
		}
	}

}
