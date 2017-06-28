package br.com.personalassistant.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.personalassistant.dao.AdministradorDAO;
import br.com.personalassistant.entidades.Administrador;
import br.com.personalassistant.excecoes.PersistenciaException;

public class AdministradorConverter implements Converter {

	private AdministradorDAO adminDAO = new AdministradorDAO();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || (value.trim().isEmpty())) {
			return null;
		}

		try {
			Administrador admin = adminDAO.getById(Long.valueOf(value));
			return admin;
		} 
		catch (NumberFormatException | PersistenciaException ex) {
			String msgErroStr = String.format("Erro de conversão! Não foi possível " 
					+ "realizar a conversão da string '%s' para o tipo esperado.", value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
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
