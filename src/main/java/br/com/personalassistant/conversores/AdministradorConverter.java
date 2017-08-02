package br.com.personalassistant.conversores;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.services.AdministradorService;
import br.com.personalassistant.entidades.Administrador;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;

@Named
@RequestScoped
@FacesConverter(value="conversorAdministrador", forClass = Administrador.class)
public class AdministradorConverter implements Converter {

	@Inject
	private AdministradorService adminService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || (value.trim().isEmpty())) {
			return null;
		}

		try {
			Administrador admin = adminService.getById(Long.valueOf(value));
			return admin;
		} 
		catch (NumberFormatException | ServiceException | ObjetoNaoExisteException ex) {
			String msgErroStr = String.format("Erro de conversão! Não foi possível " 
					+ "realizar a conversão da string '%s' para o tipo esperado.", value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			
			ex.printStackTrace();
			
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (!(value instanceof Administrador)) {
			return null;
		}

		Administrador admin = (Administrador) value;
		return String.valueOf(admin.getId());
	}

}
