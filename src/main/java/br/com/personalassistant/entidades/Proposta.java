package br.com.personalassistant.entidades;

import java.util.Date;

import br.com.personalassistant.enums.ESTADO_NEGOCIACAO;

public class Proposta {

	private int id;
	private double preco;
	private Date dataRealizacao;
	private String comentario;
	private ESTADO_NEGOCIACAO status;

	private Endereco endereco; // unidirecional
//	private Assistente assistente;
//	private Contratante contratante;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public ESTADO_NEGOCIACAO getStatus() {
		return status;
	}

	public void setStatus(ESTADO_NEGOCIACAO status) {
		this.status = status;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

/*	public Assistente getAssistente() {
		return assistente;
	}

	public void setAssistente(Assistente assistente) {
		this.assistente = assistente;
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}
*/
}
