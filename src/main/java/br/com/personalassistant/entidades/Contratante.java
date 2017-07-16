package br.com.personalassistant.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "TB_CONTRATANTE")
@Entity(name = "Contratante")
@DiscriminatorValue("Contratante")
public class Contratante extends Usuario{

	private static final long serialVersionUID = 1L;
	
	@OneToMany
	@JoinColumn(name = "favorito_do_contratante_FK")
	private List<Assistente> assistentesFavoritos; // unidirecional
	
	//@JoinColumn(name = "contratante_FK")
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Endereco> enderecos; // unidirecional
	
	@OneToMany
	@JoinColumn(name = "contratante_FK")
	private List<Proposta> propostas; // unidirecional
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "contratante")
	private List<OfertaServico> ofertasServicos; // bidirecional
	
	@OneToMany
	@JoinColumn(name = "contratante_avaliador_FK")
	private List<AvaliacaoAssistente> avaliacoesFeitas; // unidirecional
	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "contratante_avaliado_FK")
	private List<AvaliacaoContratante> avaliacoesRecebidas; // unidirecional
	
	public Contratante() {
		super();
	}

	public Contratante(String nome, String email, String senha, String numTelefonico,
			List<Endereco> enderecos) {
		super();
		this.setNome(nome);
		this.setEmail(email);
		this.setSenha(senha);
		this.setNumTelefonico(numTelefonico);
		this.enderecos = enderecos;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((assistentesFavoritos == null) ? 0 : assistentesFavoritos.hashCode());
		result = prime * result + ((avaliacoesFeitas == null) ? 0 : avaliacoesFeitas.hashCode());
		result = prime * result + ((avaliacoesRecebidas == null) ? 0 : avaliacoesRecebidas.hashCode());
		result = prime * result + ((enderecos == null) ? 0 : enderecos.hashCode());
		result = prime * result + ((ofertasServicos == null) ? 0 : ofertasServicos.hashCode());
		result = prime * result + ((propostas == null) ? 0 : propostas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contratante other = (Contratante) obj;
		if (assistentesFavoritos == null) {
			if (other.assistentesFavoritos != null)
				return false;
		} else if (!assistentesFavoritos.equals(other.assistentesFavoritos))
			return false;
		if (avaliacoesFeitas == null) {
			if (other.avaliacoesFeitas != null)
				return false;
		} else if (!avaliacoesFeitas.equals(other.avaliacoesFeitas))
			return false;
		if (avaliacoesRecebidas == null) {
			if (other.avaliacoesRecebidas != null)
				return false;
		} else if (!avaliacoesRecebidas.equals(other.avaliacoesRecebidas))
			return false;
		if (enderecos == null) {
			if (other.enderecos != null)
				return false;
		} else if (!enderecos.equals(other.enderecos))
			return false;
		if (ofertasServicos == null) {
			if (other.ofertasServicos != null)
				return false;
		} else if (!ofertasServicos.equals(other.ofertasServicos))
			return false;
		if (propostas == null) {
			if (other.propostas != null)
				return false;
		} else if (!propostas.equals(other.propostas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contratante [assistentesFavoritos=" + assistentesFavoritos + ", enderecos=" + enderecos + ", propostas="
				+ propostas + ", ofertasServicos=" + ofertasServicos + ", avaliacoesFeitas=" + avaliacoesFeitas
				+ ", avaliacoesRecebidas=" + avaliacoesRecebidas + ", getId()=" + getId() + ", getNome()=" + getNome()
				+ ", getEmail()=" + getEmail() + ", getSenha()=" + getSenha() + ", getNumTelefonico()="
				+ getNumTelefonico() + "]";
	}
	
}
