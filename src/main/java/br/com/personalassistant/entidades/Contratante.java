package br.com.personalassistant.entidades;

import java.util.List;

public class Contratante extends Usuario{

	private List<Assistente> assistentesFavoritos; // unidirecional
	private List<Endereco> enderecos; // unidirecional
	private List<Proposta> propostas; // unidirecional
	private List<OfertaServico> ofertasServicos; // bidirecional
	private List<AvaliacaoAssistente> avaliacoesFeitas; // unidirecional
	private List<AvaliacaoContratante> avaliacoesRecebidas; // unidirecional

	public List<Assistente> getAssistentesFavoritos() {
		return assistentesFavoritos;
	}

	public void setAssistentesFavoritos(List<Assistente> assistentesFavoritos) {
		this.assistentesFavoritos = assistentesFavoritos;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Proposta> getPropostas() {
		return propostas;
	}

	public void setPropostas(List<Proposta> propostas) {
		this.propostas = propostas;
	}

	public List<OfertaServico> getOfertasServicos() {
		return ofertasServicos;
	}

	public void setOfertasServicos(List<OfertaServico> ofertasServicos) {
		this.ofertasServicos = ofertasServicos;
	}

	public List<AvaliacaoAssistente> getAvaliacoesFeitas() {
		return avaliacoesFeitas;
	}

	public void setAvaliacoesFeitas(List<AvaliacaoAssistente> avaliacoesFeitas) {
		this.avaliacoesFeitas = avaliacoesFeitas;
	}

	public List<AvaliacaoContratante> getAvaliacoesRecebidas() {
		return avaliacoesRecebidas;
	}

	public void setAvaliacoesRecebidas(List<AvaliacaoContratante> avaliacoesRecebidas) {
		this.avaliacoesRecebidas = avaliacoesRecebidas;
	}
}
