package br.com.personalassistant.entidades;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.personalassistant.enums.ESTADO_NEGOCIACAO;

@Table(name = "TB_PROPOSTA")
@Entity(name = "Proposta")
public class Proposta implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@Basic(optional = false)
	private double preco;
	
	private String comentario;
	
	@Enumerated(EnumType.STRING)
	private ESTADO_NEGOCIACAO status;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "endereco_FK")
	private Endereco endereco; // unidirecional
	
	@OneToOne
	@JoinColumn(name = "data_realizacao_servico_FK", nullable = false)
	private DataRealizacaoServico dataRealizacaoServico; // unidirecional
	
	public Proposta() {
		super();
	}
	
	public Proposta(double preco, String comentario, Endereco endereco, DataRealizacaoServico dataRealizacaoServico) {
		super();
		this.preco = preco;
		this.comentario = comentario;
		this.endereco = endereco;
		this.dataRealizacaoServico = dataRealizacaoServico;
	}

	public long getId() {
		return id;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((dataRealizacaoServico == null) ? 0 : dataRealizacaoServico.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Proposta [id=" + id + ", preco=" + preco + ", comentario=" + comentario + ", status=" + status
				+ ", endereco=" + endereco + ", dataRealizacaoServico=" + dataRealizacaoServico + "]";
	}
	
}
