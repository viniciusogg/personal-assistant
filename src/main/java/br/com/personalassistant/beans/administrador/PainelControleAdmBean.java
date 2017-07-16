package br.com.personalassistant.beans.administrador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;


@ViewScoped
@Named
public class PainelControleAdmBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String opcaoFiltro;
	private List<String> opcoesFiltro;
	
	public PainelControleAdmBean(){
		opcoesFiltro = new ArrayList<String>();
		opcoesFiltro.add("Hoje");
		opcoesFiltro.add("Semana");
		opcoesFiltro.add("Mês");
		opcoesFiltro.add("Geral");
	}
	
	public String getOpcaoFiltro() {
		return opcaoFiltro;
	}

	public void setOpcaoFiltro(String opcaoFiltro) {
		this.opcaoFiltro = opcaoFiltro;
	}

	public List<String> getOpcoesFiltro() {
		return opcoesFiltro;
	}

	public void setOpcoesFiltro(List<String> opcoesFiltro) {
		this.opcoesFiltro = opcoesFiltro;
	}
	
	
	
}
