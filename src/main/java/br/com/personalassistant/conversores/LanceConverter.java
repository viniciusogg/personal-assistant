package br.com.personalassistant.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.personalassistant.dao.LanceDAO;
import br.com.personalassistant.entidades.Lance;
import br.com.personalassistant.excecoes.PersistenciaException;

@FacesConverter(forClass = Lance.class)
public class LanceConverter implements Converter{

	private LanceDAO lanceDAO = new LanceDAO();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || (value.trim().isEmpty())) {
			return null;
		}

		try {
			Lance lance = lanceDAO.getById(Long.valueOf(value));
			return lance;
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
		
		if (!(value instanceof Lance)) {
			return null;
		}

		Lance lance = (Lance) value;
		return String.valueOf(lance.getId());
	}
	
}
