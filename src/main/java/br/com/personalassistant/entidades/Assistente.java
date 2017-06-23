package br.com.personalassistant.entidades;

import java.util.List;

public class Assistente extends Usuario{

	private double precoCobrado;
	private int experiencia; // quantidade de assistencias prestadas

	private Endereco endereco; // unidirecional
	private List<CategoriaServico> categoriasServicos; // unidirecional
	private List<Lance> lances; // bidirecional
	private List<AvaliacaoAssistente> avaliacoesRecebidas; // unidirecional
	private List<AvaliacaoContratante> avaliacoesFeitas; // unidirecional
	private List<Proposta> propostas; // unidirecional (são as negociações)

	public double getPrecoCobrado() {
		return precoCobrado;
	}

	public void setPrecoCobrado(double precoCobrado) {
		this.precoCobrado = precoCobrado;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public List<CategoriaServico> getCategoriasServicos() {
		return categoriasServicos;
	}

	public void setCategoriasServicos(List<CategoriaServico> categoriasServicos) {
		this.categoriasServicos = categoriasServicos;
	}

	public List<Lance> getLances() {
		return lances;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<AvaliacaoAssistente> getAvaliacoesRecebidas() {
		return avaliacoesRecebidas;
	}

	public void setAvaliacoesRecebidas(List<AvaliacaoAssistente> avaliacoesRecebidas) {
		this.avaliacoesRecebidas = avaliacoesRecebidas;
	}

	public List<AvaliacaoContratante> getAvaliacoesFeitas() {
		return avaliacoesFeitas;
	}

	public void setAvaliacoesFeitas(List<AvaliacaoContratante> avaliacoesFeitas) {
		this.avaliacoesFeitas = avaliacoesFeitas;
	}

	public List<Proposta> getPropostas() {
		return propostas;
	}

	public void setPropostas(List<Proposta> propostas) {
		this.propostas = propostas;
	}
}
