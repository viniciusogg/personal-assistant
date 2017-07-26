package br.com.personalassistant.beans.administrador;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.CategoriaServico;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.CategoriaServicoService;

@ViewScoped
@Named
public class CategoriaServicoAdmBean extends AbstractBean{

	private static final long serialVersionUID = -7752224083271420472L;

	@Inject private CategoriaServicoService categoriaServicoService;
	private CategoriaServico categoriaServico;
	private List<CategoriaServico> lista;
	
	public void preRenderView(){
				
		if(this.categoriaServico == null){
			this.categoriaServico = new CategoriaServico();
		}

		try {
			this.lista = categoriaServicoService.getAll();
		} 
		catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public void salvarCategoriaServico(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			categoriaServicoService.save(categoriaServico);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria '" + categoriaServico.getNome() + "' cadastrada com sucesso", ""));
		} 
		catch (ServiceException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
	}
	
	public void deletarCategoriaServico(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		String nomeCategoriaRemovida = categoriaServico.getNome();
		
		try {
			categoriaServicoService.delete(categoriaServico);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria '" + nomeCategoriaRemovida + "' removida com sucesso", ""));
		} 
		catch (ServiceException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}		
	}
	
	public CategoriaServico getCategoriaServico() {
		return categoriaServico;
	}

	public void setCategoriaServico(CategoriaServico categoriaServico) {
		this.categoriaServico = categoriaServico;
	}

	public List<CategoriaServico> getLista() {
		return lista;
	}

	public void setLista(List<CategoriaServico> lista) {
		this.lista = lista;
	}
}
