package br.com.personalassistant.beans.assistente;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class PainelControleAstBean {
	
	private long quantAssistencias = 120;
	private long totalAvaliacoes = 800;
	private int quantEstrelas = 4;
	
	public String getNivelExperiencia(){
		
		if(quantAssistencias < 500){
			return "SEM EXPERIÊNCIA";
		}
		else if(quantAssistencias > 500 && quantAssistencias < 1500){
			return "POUCO EXPERIENTE";
		}
		else if(quantAssistencias > 1500 && quantAssistencias < 3000){
			return "EXPERIENTE";
		}
		
		return "MUITO EXPERIENTE";
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

	public long getQuantAssistencias() {
		return quantAssistencias;
	}

	public long getTotalAvaliacoes() {
		return totalAvaliacoes;
	}
	
	public int getQuantEstrelas() {
		return quantEstrelas;
	}
}
