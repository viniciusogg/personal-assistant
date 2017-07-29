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

import br.com.personalassistant.services.AvaliacaoContratanteService;
import br.com.personalassistant.entidades.AvaliacaoContratante;
import br.com.personalassistant.excecoes.ServiceException;

@Named
@RequestScoped
@FacesConverter(forClass = AvaliacaoContratante.class)
public class AvaliacaoContratanteConverter implements Converter {

	@Inject
	private AvaliacaoContratanteService avaliacaoContratanteService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || (value.trim().isEmpty())) {
			return null;
		}

		try {
			AvaliacaoContratante assistente = avaliacaoContratanteService.getById(Long.valueOf(value));
			return assistente;
		} 
		catch (NumberFormatException | ServiceException ex) {
			String msgErroStr = String.format("Erro de conversão! Não foi possível " 
					+ "realizar a conversão da string '%s' para o tipo esperado.", value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (!(value instanceof AvaliacaoContratante)) {
			return null;
		}

		AvaliacaoContratante assistente = (AvaliacaoContratante) value;
		return String.valueOf(assistente.getId());
	}

}
