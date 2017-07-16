package br.com.personalassistant.beans.administrador;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.personalassistant.dao.CategoriaServicoDAO;
import br.com.personalassistant.entidades.CategoriaServico;
import br.com.personalassistant.excecoes.PersistenciaException;

@ViewScoped
@ManagedBean
public class CategoriaServicoAdmBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private CategoriaServico categoriaServico;
	private CategoriaServicoDAO categoriaServicoDAO;
	private List<CategoriaServico> lista;
	
	public void preRenderView(){
		
		categoriaServicoDAO = new CategoriaServicoDAO();
		
		if(this.categoriaServico == null){
			this.categoriaServico = new CategoriaServico();
		}

		try {
			this.lista = categoriaServicoDAO.getAll();
		} 
		catch (PersistenciaException e) {
			e.printStackTrace();
		}
	}

	public void salvarCategoriaServico(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			categoriaServicoDAO.save(categoriaServico);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria '" + categoriaServico.getNome() + "' cadastrada com sucesso", ""));
		} 
		catch (PersistenciaException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
	}
	
	public void deletarCategoriaServico(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		String nomeCategoriaRemovida = categoriaServico.getNome();
		
		try {
			categoriaServicoDAO.delete(categoriaServico);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria '" + nomeCategoriaRemovida + "' removida com sucesso", ""));
		} 
		catch (PersistenciaException e) {
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
