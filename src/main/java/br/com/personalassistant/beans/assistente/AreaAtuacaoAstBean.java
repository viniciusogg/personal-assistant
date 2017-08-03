package br.com.personalassistant.beans.assistente;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;

@Named
@ViewScoped
public class AreaAtuacaoAstBean extends AbstractBean {

	private static final long serialVersionUID = -79280093168767924L;

	private Double valorHora = 54.9;
	private Double valorFixo = 2000.9;
	private String nomeCategoriaServico = "Nome da categoria de servico";
	private List<String> capacidades = new ArrayList<String>();
	private List<String> categoriasServicos = new ArrayList<String>();
	
	public AreaAtuacaoAstBean(){
		capacidades.add("ca55 145432f");
		capacidades.add("capacidrteade 2");
		capacidades.add("capacidade 35345");
		capacidades.add("cap 4");
		capacidades.add("capacie 53");
		
		categoriasServicos.add("Categoria servico 1");
		categoriasServicos.add("Categoria servico 2");
		categoriasServicos.add("Categoria servico 3");
		categoriasServicos.add("Categoria servico 4");
	}
	
	public Double getValorHora() {
		return valorHora;
	}
	
	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
	}
	
	public Double getValorFixo() {
		return valorFixo;
	}
	
	public void setValorFixo(Double valorFixo) {
		this.valorFixo = valorFixo;
	}
	
	public String getNomeCategoriaServico() {
		return nomeCategoriaServico;
	}
	
	public void setNomeCategoriaServico(String nomeCategoriaServico) {
		this.nomeCategoriaServico = nomeCategoriaServico;
	}
	
	public List<String> getCapacidades() {
		return capacidades;
	}
	
	public void setCapacidades(List<String> capacidades) {
		this.capacidades = capacidades;
	}

	public List<String> getCategoriasServicos() {
		return categoriasServicos;
	}

	public void setCategoriasServicos(List<String> categoriasServicos) {
		this.categoriasServicos = categoriasServicos;
	}
	
}
