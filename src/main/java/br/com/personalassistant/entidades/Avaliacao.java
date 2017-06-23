package br.com.personalassistant.entidades;

import java.util.Date;

public abstract class Avaliacao {

	private int id;
	private int cordialidade;
	private int pontualidade;
	private Date data;
	private String comentario;

	public int getId() {
		return id;
	}

	public int getCordialidade() {
		return cordialidade;
	}

	public void setCordialidade(int cordialidade) {
		this.cordialidade = cordialidade;
	}

	public int getPontualidade() {
		return pontualidade;
	}

	public void setPontualidade(int pontualidade) {
		this.pontualidade = pontualidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
