package br.com.personalassistant.beans.contratante;

import br.com.personalassistant.beans.AbstractBean;

public class IndexContratanteBean extends AbstractBean{

	private static final long serialVersionUID = -7936163110122575442L;

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
		 * pontualidade = 5
		 * cordialidade = 4
		 * qualidadeServico = 5
		 * 
		 * 5 + 4 + 5 = 14/3 = 4,6 = Math.round(4,6) = 5
		 * 
		 * PREVALECE A MAIOR PORCENTAGEM, NO CASO DE EMPATE, FAZER A MÉDIA DAS ESTRELAS QUE ESTÃO
		 * EMPATADAS PELAS QUE NÃO ESTÃO.
		 * quantEstrela = ...
		 */			
		return "BOA";
	}
}
