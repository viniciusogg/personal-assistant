package br.com.personalassistant.beans.assistente;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;

@Named
@RequestScoped
public class OfertasAssistenteBean extends AbstractBean {

	private static final long serialVersionUID = -3648717368496249778L;
	private List<String> opcoesPagamento = new ArrayList<String>();
	private String tipoPagamento;
	
	public OfertasAssistenteBean(){
		opcoesPagamento.add("Preço fixo");
		opcoesPagamento.add("Hora trabalhada");
	}

	public List<String> getOpcoesPagamento() {
		return opcoesPagamento;
	}

	public void setOpcoesPagamento(List<String> opcoesPagamento) {
		this.opcoesPagamento = opcoesPagamento;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
}
