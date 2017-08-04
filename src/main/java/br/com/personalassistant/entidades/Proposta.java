package br.com.personalassistant.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.personalassistant.enums.ESTADO_NEGOCIACAO;
import br.com.personalassistant.enums.TIPO_PAGAMENTO;

@Table(name = "TB_PROPOSTA")
@Entity(name = "Proposta")
public class Proposta implements Serializable{

	private static final long serialVersionUID = 8100904415164980186L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(nullable = false)
	private Double preco;
	
	private String comentario;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TIPO_PAGAMENTO tipoPagamento;
	
	@Enumerated(EnumType.STRING)
	private ESTADO_NEGOCIACAO status;
	
	@ManyToOne
	@JoinColumn(name="assistente_FK",nullable = false)
	private Assistente assistente; //bidirecional
	
	@ManyToOne
	@JoinColumn(name="contratante_FK", nullable = false)
	private Contratante contratante; //bidirecional
	
	@ManyToOne
	@JoinColumn(name = "endereco_FK")
	private Endereco endereco; // unidirecional
	
	@Embedded
	@Column(nullable = false)
	private DataRealizacaoServico dataRealizacaoServico;
	
	public Proposta() {
		super();
	}
	
	public Proposta(Double preco, String comentario, Endereco endereco, 
			DataRealizacaoServico dataRealizacaoServico, TIPO_PAGAMENTO tipoPagamento) {
		super();
		this.preco = preco;
		this.comentario = comentario;
		this.endereco = endereco;
		this.dataRealizacaoServico = dataRealizacaoServico;
		this.tipoPagamento = tipoPagamento;
	}

	public Long getId() {
		return id;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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

	public DataRealizacaoServico getDataRealizacaoServico() {
		return dataRealizacaoServico;
	}

	public void setDataRealizacaoServico(DataRealizacaoServico dataRealizacaoServico) {
		this.dataRealizacaoServico = dataRealizacaoServico;
	}

	public TIPO_PAGAMENTO getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TIPO_PAGAMENTO tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
	public Assistente getAssistente() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assistente == null) ? 0 : assistente.hashCode());
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((contratante == null) ? 0 : contratante.hashCode());
		result = prime * result + ((dataRealizacaoServico == null) ? 0 : dataRealizacaoServico.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tipoPagamento == null) ? 0 : tipoPagamento.hashCode());
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
		Proposta other = (Proposta) obj;
		if (assistente == null) {
			if (other.assistente != null)
				return false;
		} else if (!assistente.equals(other.assistente))
			return false;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (contratante == null) {
			if (other.contratante != null)
				return false;
		} else if (!contratante.equals(other.contratante))
			return false;
		if (dataRealizacaoServico == null) {
			if (other.dataRealizacaoServico != null)
				return false;
		} else if (!dataRealizacaoServico.equals(other.dataRealizacaoServico))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (status != other.status)
			return false;
		if (tipoPagamento != other.tipoPagamento)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Proposta [id=" + id + ", preco=" + preco + ", comentario=" + comentario + ", tipoPagamento="
				+ tipoPagamento + ", status=" + status + ", assistente=" + assistente + ", contratante=" + contratante
				+ ", endereco=" + endereco + ", dataRealizacaoServico=" + dataRealizacaoServico + "]";
	}
	
}
