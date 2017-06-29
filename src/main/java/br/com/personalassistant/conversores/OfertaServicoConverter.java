package br.com.personalassistant.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.personalassistant.dao.OfertaServicoDAO;
import br.com.personalassistant.entidades.OfertaServico;
import br.com.personalassistant.excecoes.PersistenciaException;

@FacesConverter(forClass = OfertaServico.class)
public class OfertaServicoConverter implements Converter {

	private OfertaServicoDAO ofertaServicoDAO = new OfertaServicoDAO();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || (value.trim().isEmpty())) {
			return null;
		}

		try {
			OfertaServico ofertaServico = ofertaServicoDAO.getById(Long.valueOf(value));
			return ofertaServico;
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
		
		if (!(value instanceof OfertaServico)) {
			return null;
		}

		OfertaServico ofertaServico = (OfertaServico) value;
		return String.valueOf(ofertaServico.getId());
	}

}
