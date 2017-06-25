package br.com.personalassistant.entidades;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import br.com.personalassistant.enums.ESTADO_OFERTA;

@Entity
public class OfertaServico {

	@Id
	private int id;
	
	private int duracaoOferta;
	private double precoInicial;
	private ESTADO_OFERTA status;
	private Endereco endereco; // unidirecional
	private CategoriaServico categoriaServico; // unidirecional
	private Contratante contratante; // bidirecional
	private ArrayList<Lance> lances; // bidirecional
	private DataRealizacaoServico dataRealizacaoServico; // unidirecional

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
	
	public DataRealizacaoServico getDataRealizacaoServico() {
		return dataRealizacaoServico;
	}

	public void setDataRealizacaoServico(DataRealizacaoServico dataRealizacaoServico) {
		this.dataRealizacaoServico = dataRealizacaoServico;
	}

}
