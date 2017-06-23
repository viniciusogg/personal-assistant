package br.com.personalassistant.entidades;

import java.util.Date;

import br.com.personalassistant.enums.ESTADO_LANCE;

public class Lance {

	private int id;
	private Date dataRealizacaoServico;
	private double valor;
	private ESTADO_LANCE status;
	private Assistente assistente; // bidirecional
	private OfertaServico oferstaServico; // bidirecional

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataRealizacaoServico() {
		return dataRealizacaoServico;
	}

	public void setDataRealizacaoServico(Date dataRealizacaoServico) {
		this.dataRealizacaoServico = dataRealizacaoServico;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public ESTADO_LANCE getStatus() {
		return status;
	}

	public void setStatus(ESTADO_LANCE status) {
		this.status = status;
	}

	public Assistente getAssistente() {
		return assistente;
	}

	public void setAssistente(Assistente assistente) {
		this.assistente = assistente;
	}

	public OfertaServico getOferstaServico() {
		return oferstaServico;
	}

	public void setOferstaServico(OfertaServico oferstaServico) {
		this.oferstaServico = oferstaServico;
	}
}
