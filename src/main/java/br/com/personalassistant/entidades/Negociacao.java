package br.com.personalassistant.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.personalassistant.enums.ESTADO_NEGOCIACAO;
import br.com.personalassistant.util.DataAtual;

@Entity
@Table(name="TB_NEGOCIACAO")
public class Negociacao {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(name="negociacao_FK", nullable=false)
	@OneToMany(mappedBy = "negociacao", cascade = CascadeType.ALL)
	private List<Proposta> propostas = new ArrayList<Proposta>();
	
	@OneToOne
	@JoinColumns({
		@JoinColumn(name = "contratante_FK", referencedColumnName="id_PK"),
		@JoinColumn(name = "ultimaAtualizacaoContratante_FK", referencedColumnName="ultimaAtualizacao_PK")
	})
	//@JoinColumn(name="contratante_FK", nullable=false)
	private Contratante contratante;
	
	@OneToOne
	@JoinColumns({
		@JoinColumn(name = "assistente_FK", referencedColumnName="id_PK"),
		@JoinColumn(name = "ultimaAtualizacaoAssistente_FK", referencedColumnName="ultimaAtualizacao_PK")
	})
	//@JoinColumn(name="assistente_FK", nullable=false)
	private Assistente assistente;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataInicioNegociacao = new Date();
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ESTADO_NEGOCIACAO status;
	
	public Negociacao() {
		super();

		this.status = ESTADO_NEGOCIACAO.EM_ANDAMENTO;
		this.dataInicioNegociacao = DataAtual.getDataAtual();
	}

	public Negociacao(List<Proposta> propostas, Contratante contratante, Assistente assistente,
			Date dataInicioNegociacao) {
		super();
		this.propostas = propostas;
		this.contratante = contratante;
		this.assistente = assistente;
		this.dataInicioNegociacao = dataInicioNegociacao;
		this.status = ESTADO_NEGOCIACAO.EM_ANDAMENTO;
		this.dataInicioNegociacao = DataAtual.getDataAtual();
	}

	public Long getId() {
		return id;
	}

	public List<Proposta> getPropostas() {
		return propostas;
	}

	public void setPropostas(List<Proposta> propostas) {
		this.propostas = propostas;
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public Assistente getAssistente() {
		return assistente;
	}

	public void setAssistente(Assistente assistente) {
		this.assistente = assistente;
	}

	public Date getDataInicioNegociacao() {
		return dataInicioNegociacao;
	}

	public void setDataInicioNegociacao(Date dataInicioNegociacao) {
		this.dataInicioNegociacao = dataInicioNegociacao;
	}

	public ESTADO_NEGOCIACAO getStatus() {
		return status;
	}

	public void setStatus(ESTADO_NEGOCIACAO status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assistente == null) ? 0 : assistente.hashCode());
		result = prime * result + ((contratante == null) ? 0 : contratante.hashCode());
		result = prime * result + ((dataInicioNegociacao == null) ? 0 : dataInicioNegociacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((propostas == null) ? 0 : propostas.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Negociacao other = (Negociacao) obj;
		if (assistente == null) {
			if (other.assistente != null)
				return false;
		} else if (!assistente.equals(other.assistente))
			return false;
		if (contratante == null) {
			if (other.contratante != null)
				return false;
		} else if (!contratante.equals(other.contratante))
			return false;
		if (dataInicioNegociacao == null) {
			if (other.dataInicioNegociacao != null)
				return false;
		} else if (!dataInicioNegociacao.equals(other.dataInicioNegociacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (propostas == null) {
			if (other.propostas != null)
				return false;
		} else if (!propostas.equals(other.propostas))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Negociacao [id=" + id + ", propostas=" + propostas + ", contratante=" + contratante + ", assistente="
				+ assistente + ", dataInicioNegociacao=" + dataInicioNegociacao + ", status=" + status + "]";
	}

	
	
}
