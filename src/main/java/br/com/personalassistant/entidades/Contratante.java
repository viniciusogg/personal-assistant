package br.com.personalassistant.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.com.personalassistant.enums.TIPO_USUARIO;

@Table(name = "TB_CONTRATANTE")
@Entity(name = "Contratante")
@DiscriminatorValue("contr")
public class Contratante extends Usuario{

	private static final long serialVersionUID = -1566617799006322877L;
	
	@Transient
	private int reputacao;
	
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumns({
		@JoinColumn(name = "contratante_FK", referencedColumnName="id_PK"),
		@JoinColumn(name = "ultimaAtualizacaoContratante_FK", referencedColumnName="ultimaAtualizacao_PK")
	})
	private List<Assistente> assistentesFavoritos; // unidirecional
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumns({
		@JoinColumn(name = "endereco_FK", referencedColumnName="id_PK"),
		@JoinColumn(name = "ultimaAtualizacaoEndereco_FK", referencedColumnName="ultimaAtualizacao_PK")
	})
	private Endereco endereco; // unidirecional
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "contratante")
	@Column(name = "contratante_FK")
	private List<OfertaServico> ofertasServicos; // bidirecional
	
	public Contratante() {
		super();
		this.setTipoUsuario(TIPO_USUARIO.CONTRATANTE);
	}

	public Contratante(String nome, String email, String senha, String numTelefonico,
			Endereco endereco) {
		super();
		this.setNome(nome);
		this.setEmail(email);
		this.setSenha(senha);
		this.setNumTelefonico(numTelefonico);
		this.endereco = endereco;
		this.setTipoUsuario(TIPO_USUARIO.CONTRATANTE);
	}

	public List<Assistente> getAssistentesFavoritos() {
		return assistentesFavoritos;
	}

	public void setAssistenteFavoritos(List<Assistente> assistentesFavoritos) {
		this.assistentesFavoritos = assistentesFavoritos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<OfertaServico> getOfertasServicos() {
		return ofertasServicos;
	}

	public void setOfertasServicos(List<OfertaServico> ofertasServicos) {
		this.ofertasServicos = ofertasServicos;
	}
	
	public int getReputacao() {
		return reputacao;
	}

	public void setReputacao(int reputacao) {
		this.reputacao = reputacao;
	}

	@Override
	public String toString() {
		return "Contratante [reputacao=" + reputacao + ", assistentesFavoritos=" + assistentesFavoritos + ", endereco="
				+ endereco + ", ofertasServicos=" + ofertasServicos + ", getPk()=" + getPk() + ", getNome()="
				+ getNome() + ", getEmail()=" + getEmail() + ", getSenha()=" + getSenha() + ", getNumTelefonico()="
				+ getNumTelefonico() + ", getTipoUsuario()=" + getTipoUsuario() + "]";
	}
	
}
