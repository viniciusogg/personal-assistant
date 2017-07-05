package br.com.personalassistant.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.personalassistant.dao.CategoriaServicoDAO;
import br.com.personalassistant.entidades.CategoriaServico;
import br.com.personalassistant.excecoes.PersistenciaException;

@FacesConverter(value = "categoriaServicoConverter")
public class CategoriaServicoConverter implements Converter {

	private CategoriaServicoDAO categoriaServicoDAO = new CategoriaServicoDAO();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null || (value.trim().isEmpty())) {
			return null;
		}

		try {
			CategoriaServico categoriaServico = categoriaServicoDAO.getById(Long.valueOf(value));
			return categoriaServico;
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
		
		if (!(value instanceof CategoriaServico)) {
			return null;
		}

		CategoriaServico categoriaServico = (CategoriaServico) value;
		return String.valueOf(categoriaServico.getId());
	}

}
