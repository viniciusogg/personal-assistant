package br.com.personalassistant.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.personalassistant.dao.AvaliacaoAssistenteDAO;
import br.com.personalassistant.entidades.AvaliacaoAssistente;
import br.com.personalassistant.excecoes.PersistenciaException;

@FacesConverter(forClass = AvaliacaoAssistente.class)
public class AvaliacaoAssistenteConverter implements Converter {

	private AvaliacaoAssistenteDAO avaliacaoAssistenteDAO = new AvaliacaoAssistenteDAO();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || (value.trim().isEmpty())) {
			return null;
		}

		try {
			AvaliacaoAssistente avaliacaoAssistente = avaliacaoAssistenteDAO.getById(Long.valueOf(value));
			return avaliacaoAssistente;
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
		
		if (!(value instanceof AvaliacaoAssistente)) {
			return null;
		}

		AvaliacaoAssistente avaliacaoAssistente = (AvaliacaoAssistente) value;
		return String.valueOf(avaliacaoAssistente.getId());
	}

}
