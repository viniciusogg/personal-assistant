package br.com.personalassistant.beans.administrador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.personalassistant.dao.CategoriaServicoDAO;
import br.com.personalassistant.entidades.CategoriaServico;
import br.com.personalassistant.excecoes.PersistenciaException;

@ViewScoped
@ManagedBean
public class CategoriaServicoAdmBean {

	private CategoriaServico categoriaServico;
	private CategoriaServicoDAO categoriaServicoDAO;
	private List<CategoriaServico> lista;
	
	public void init(){
		if(this.categoriaServico == null){
			this.categoriaServico = new CategoriaServico();
		}
	}
	
	public CategoriaServicoAdmBean(){

		this.categoriaServicoDAO = new CategoriaServicoDAO();	
		
		try {
			this.lista = categoriaServicoDAO.getAll();
		} 
		catch (PersistenciaException e) {
			e.printStackTrace();
		}
	}
	
	public void salvarCategoriaServico(){
		
		try {
			categoriaServicoDAO.save(categoriaServico);
		} 
		catch (PersistenciaException e) {
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
