package br.com.personalassistant.beans.contratante;

import java.io.Serializable;

import br.com.personalassistant.beans.AbstractBean;

public class IndexContratanteBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long totalAvaliacoes = new Long(800);
	private Integer quantEstrelas = 4;
	
	public Long getTotalAvaliacoes() {
		return totalAvaliacoes;
	}
	
	public void setTotalAvaliacoes(Long totalAvaliacoes) {
		this.totalAvaliacoes = totalAvaliacoes;
	}
	
	public Integer getQuantEstrelas() {
		return quantEstrelas;
	}
	
	public void setQuantEstrelas(Integer quantEstrelas) {
		this.quantEstrelas = quantEstrelas;
	}
	
	public String getReputacao(){
		/*
		 * TOTAL DE AVALIAÇÕES DE 1 ESTRELA: 4 = 4/800 = 0,005x100 = 0,5%
		 * TOTAL DE AVALIAÇÕES DE 2 ESTRELAS: 2 = 2/800 = 0,0025x100 = 0,25%
		 * TOTAL DE AVALIAÇÕES DE 3 ESTRELAS: 10 = 10/800 = 0,0125x100 = 1,25%
		 * TOTAL DE AVALIAÇÕES DE 4 ESTRELAS: 200 = 200/800 = 0,25x100 = 25%
		 * TOTAL DE AVALIAÇÕES DE 5 ESTRELAS: 500 = 584/800 = 0,73x100 = 73%
		 * 
		 * PREVALECE A MAIOR PORCENTAGEM, NO CASO DE EMPATE, FAZER A MÉDIA DAS ESTRELAS QUE ESTÃO
		 * EMPATADAS PELAS QUE NÃO ESTÃO.
		 * quantEstrela = ...
		 */			
		return "BOA";
	}
}
