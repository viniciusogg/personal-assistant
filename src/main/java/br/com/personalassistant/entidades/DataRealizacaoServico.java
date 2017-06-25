package br.com.personalassistant.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DataRealizacaoServico {

	@Id
	private int id;
	
	private Date dataRealizacaoInicial;
	private Date dataRealizacaoLimite;
	private Date horaRealizacaoServico;
	
}
