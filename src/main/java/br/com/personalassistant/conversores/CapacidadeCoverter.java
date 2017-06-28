package br.com.personalassistant.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.personalassistant.dao.CapacidadeDAO;
import br.com.personalassistant.entidades.Capacidade;
import br.com.personalassistant.excecoes.PersistenciaException;

public class CapacidadeCoverter implements Converter {

	private CapacidadeDAO capacidadeDAO = new CapacidadeDAO();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || (value.trim().isEmpty())) {
			return null;
		}

		try {
			Capacidade capacidade = capacidadeDAO.getById(Long.valueOf(value));
			return capacidade;
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
		
		if (!(value instanceof Capacidade)) {
			return null;
		}

		Capacidade capacidade = (Capacidade) value;
		return String.valueOf(capacidade.getId());
	}

}
