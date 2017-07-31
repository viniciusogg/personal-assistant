package br.com.personalassistant.beans.assistente;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;

@Named
@ViewScoped
public class AvaliacoesAssistenteBean extends AbstractBean {

	private static final long serialVersionUID = -2541138618832675752L;

	private Integer pontualidade;
	private Integer cordialidade;
	private Integer facilidadePagamento;
	private List<String> listaServicos = new ArrayList<String>();
	
	public AvaliacoesAssistenteBean(){
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
	
	public Integer getFacilidadePagamento() {
		return facilidadePagamento;
	}
	
	public void setFacilidadePagamento(Integer facilidadePagamento) {
		this.facilidadePagamento = facilidadePagamento;
	}
	
	public List<String> getListaServicos() {
		return listaServicos;
	}
	
	public void setListaServicos(List<String> listaServicos) {
		this.listaServicos = listaServicos;
	}
	
	public void salvarAvaliacao(){
		
	}

}
