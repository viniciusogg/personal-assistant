package br.com.personalassistant.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.personalassistant.enums.TIPO_PAGAMENTO;
import br.com.personalassistant.util.DataAtual;

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
	
	@ManyToOne
	@JoinColumn(name="negociacao_FK", nullable = false)
	private Negociacao negociacao = new Negociacao();
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "usuario_FK", referencedColumnName="id_PK"),
		@JoinColumn(name = "ultimaAtualizacaoUsuario_FK", referencedColumnName="ultimaAtualizacao_PK")
	})
	//@JoinColumn(name="usuario_FK", nullable=false)
	private Usuario autorProposta; //unidirecional
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "endereco_FK", referencedColumnName="id_PK"),
		@JoinColumn(name = "ultimaAtualizacaoEndereco_FK", referencedColumnName="ultimaAtualizacao_PK")
	})
	//@JoinColumn(name="endereco_FK")
	private Endereco endereco; // unidirecional
	
	@Embedded
	@Column(nullable = false)
	private DataRealizacaoServico dataRealizacaoServico = new DataRealizacaoServico();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataProposta;
	
	public Proposta() {
		super();
		this.dataProposta = DataAtual.getDataAtual();
	}
	
	public Proposta(Double preco, String comentario, TIPO_PAGAMENTO tipoPagamento, Negociacao negociacao,
			Usuario autorProposta, Endereco endereco,
			DataRealizacaoServico dataRealizacaoServico) {
		super();
		this.preco = preco;
		this.comentario = comentario;
		this.tipoPagamento = tipoPagamento;
		this.negociacao = negociacao;
		this.autorProposta = autorProposta;
		this.endereco = endereco;
		this.dataRealizacaoServico = dataRealizacaoServico;
		this.dataProposta = DataAtual.getDataAtual();
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

	public TIPO_PAGAMENTO getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TIPO_PAGAMENTO tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Negociacao getNegociacao() {
		return negociacao;
	}

	public void setNegociacao(Negociacao negociacao) {
		this.negociacao = negociacao;
	}

	public Usuario getAutorProposta() {
		return autorProposta;
	}

	public void setAutorProposta(Usuario autorProposta) {
		this.autorProposta = autorProposta;
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

	public Long getId() {
		return id;
	}
	
	public Date getDataProposta() {
		return dataProposta;
	}

	public void setDataProposta(Date dataProposta) {
		this.dataProposta = dataProposta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((dataRealizacaoServico == null) ? 0 : dataRealizacaoServico.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((negociacao == null) ? 0 : negociacao.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
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
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
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
		if (negociacao == null) {
			if (other.negociacao != null)
				return false;
		} else if (!negociacao.equals(other.negociacao))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (tipoPagamento != other.tipoPagamento)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Proposta [id=" + id + ", preco=" + preco + ", comentario=" + comentario + ", tipoPagamento="
				+ tipoPagamento + ", negociacao=" + negociacao + ", autorProposta="
				+ autorProposta + ", endereco=" + endereco + ", dataRealizacaoServico=" + dataRealizacaoServico + "]";
	}

	
}
