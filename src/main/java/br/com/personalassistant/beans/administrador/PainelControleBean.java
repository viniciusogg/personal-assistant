package br.com.personalassistant.beans.administrador;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class PainelControleBean {

	private String opcaoFiltro;
	private List<String> opcoesFiltro;
	
	/*
	 * @PostConstruct
	public void init(){
		opcoesFiltro = new ArrayList<String>();
		opcoesFiltro.add("Hoje");
		opcoesFiltro.add("Semana");
		opcoesFiltro.add("Mês");
		opcoesFiltro.add("Geral");
	}
	*/
	
	public PainelControleBean(){
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
