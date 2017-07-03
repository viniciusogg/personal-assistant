package br.com.personalassistant.beans.administrador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import br.com.personalassistant.dao.CategoriaServicoDAO;
import br.com.personalassistant.entidades.CategoriaServico;
import br.com.personalassistant.excecoes.PersistenciaException;

@ViewScoped
@ManagedBean
public class CategoriaServicoAdmBean {

	private CategoriaServico categoriaServico;
	//private CategoriaServicoDAO categoriaServicoDAO = new CategoriaServicoDAO();
	private List<String> lista;
	
	public CategoriaServicoAdmBean(){
		
		lista = new ArrayList<String>();
		lista.add("Categoria 1");
		lista.add("Categoria 2");
		lista.add("Categoria 3");
		lista.add("Categoria 4");
		lista.add("Categoria 5");
		lista.add("Categoria 6");
		lista.add("Categoria 7");
		lista.add("Categoria 8");
		lista.add("Categoria 9");
		lista.add("Categoria 10");

	}
	
	public void salvarCategoriaServico(){
		
		/*try {
			categoriaServicoDAO.save(categoriaServico);
		} 
		catch (PersistenciaException e) {
			e.printStackTrace();
		}*/
	}
	
	public CategoriaServico getCategoriaServico() {
		return categoriaServico;
	}

	public void setCategoriaServico(CategoriaServico categoriaServico) {
		this.categoriaServico = categoriaServico;
	}
	
	public List<String> getLista() {
		return lista;
	}

	public void setLista(List<String> lista) {
		this.lista = lista;
	}
		
}
