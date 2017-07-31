package br.com.personalassistant.beans.contratante;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;

@Named
@ViewScoped
public class AvaliacoesContratanteBean extends AbstractBean {

	private static final long serialVersionUID = 3228361687547946944L;

	private Integer pontualidade;
	private Integer cordialidade;
	private Integer qualidadeServico;
	private List<String> listaServicos = new ArrayList<String>();
	
	public AvaliacoesContratanteBean(){
		listaServicos.add("Serviço 1");
		listaServicos.add("Serviço 2");
		listaServicos.add("Serviço 3");
	}

	public Integer getPontualidade() {
		return pontualidade;
	}
	
	public void setPontualidade(Integer pontualidade) {
		this.pontualidade = pontualidade;
	}
	
	public Integer getCordialidade() {
		return cordialidade;
	}
	
	public void setCordialidade(Integer cordialidade) {
		this.cordialidade = cordialidade;
	}
	
	public Integer getQualidadeServico() {
		return qualidadeServico;
	}
	
	public void setQualidadeServico(Integer qualidadeServico) {
		this.qualidadeServico = qualidadeServico;
	}
	
	public List<String> getListaServicos() {
		return listaServicos;
	}
	
	public void setListaServicos(List<String> listaServicos) {
		this.listaServicos = listaServicos;
	}
	
	public void salvarAvaliacao(){
		
	}
	
	public void addFavorito(){
		
	}
}
