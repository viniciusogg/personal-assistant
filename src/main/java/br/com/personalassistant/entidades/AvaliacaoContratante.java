package br.com.personalassistant.entidades;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "AvaliacaoContratante")
@Table(name = "TB_AVALIACAO_CONTRATANTE")
@DiscriminatorValue("AvaliacaoContratante")
public class AvaliacaoContratante extends Avaliacao{

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private int facilidadePagamento;
	
	public AvaliacaoContratante() {
		super();
	}

	public AvaliacaoContratante(int cordialidade, int pontualidade, int facilidadePagamento) {
		super();
		this.setCordialidade(cordialidade);
		this.setPontualidade(pontualidade);
		this.facilidadePagamento = facilidadePagamento;
	}

	public int getFacilidadePagamento() {
		return facilidadePagamento;
	}

	public void setFacilidadePagamento(int facilidadePagamento) {
		this.facilidadePagamento = facilidadePagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + facilidadePagamento;
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
		AvaliacaoContratante other = (AvaliacaoContratante) obj;
		if (facilidadePagamento != other.facilidadePagamento)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AvaliacaoContratante [facilidadePagamento=" + facilidadePagamento + ", getId()=" + getId()
				+ ", getCordialidade()=" + getCordialidade() + ", getPontualidade()=" + getPontualidade()
				+ ", getData()=" + getData() + ", getComentario()=" + getComentario() + "]";
	}
}
