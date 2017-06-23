package br.com.personalassistant.entidades;

import java.util.ArrayList;
import java.util.Date;

import br.com.personalassistant.enums.ESTADO_OFERTA;

public class OfertaServico {

	private int id;
	private int duracaoOferta;
	private double precoInicial;
	private Date dataRealizacaoServico;
	private ESTADO_OFERTA status;

	private Endereco endereco; // unidirecional
	private CategoriaServico categoriaServico; // unidirecional
	private Contratante contratante; // bidirecional
	private ArrayList<Lance> lances; // bidirecional

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuracaoOferta() {
		return duracaoOferta;
	}

	public void setDuracaoOferta(int duracaoOferta) {
		this.duracaoOferta = duracaoOferta;
	}

	public double getPrecoInicial() {
		return precoInicial;
	}

	public void setPrecoInicial(double precoInicial) {
		this.precoInicial = precoInicial;
	}

	public Date getDataRealizacaoServico() {
		return dataRealizacaoServico;
	}

	public void setDataRealizacaoServico(Date dataRealizacaoServico) {
		this.dataRealizacaoServico = dataRealizacaoServico;
	}

	public ESTADO_OFERTA getStatus() {
		return status;
	}

	public void setStatus(ESTADO_OFERTA status) {
		this.status = status;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public CategoriaServico getCategoriaServico() {
		return categoriaServico;
	}

	public void setCategoriaServico(CategoriaServico categoriaServico) {
		this.categoriaServico = categoriaServico;
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public ArrayList<Lance> getLances() {
		return lances;
	}

	public void setLances(ArrayList<Lance> lances) {
		this.lances = lances;
	}

}
