package br.com.personalassistant.entidades;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "AvaliacaoAssistente")
@Table(name = "TB_AVALIACAO_ASSISTENTE")
@DiscriminatorValue("AvaliacaoAssistente")
public class AvaliacaoAssistente extends Avaliacao{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private Integer qualidadeServico;
	
	public AvaliacaoAssistente(){
		super();
	}
	
	public AvaliacaoAssistente(Integer cordialidade, Integer pontualidade, Integer qualidadeServico, String comentario) {
		super();
		this.setCordialidade(cordialidade);
		this.setPontualidade(pontualidade);
		this.qualidadeServico = qualidadeServico;
		this.setComentario(comentario);
	}

	public Integer getQualidadeServico() {
		return qualidadeServico;
	}

	public void setQualidadeServico(Integer qualidadeServico) {
		this.qualidadeServico = qualidadeServico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((qualidadeServico == null) ? 0 : qualidadeServico.hashCode());
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
		AvaliacaoAssistente other = (AvaliacaoAssistente) obj;
		if (qualidadeServico == null) {
			if (other.qualidadeServico != null)
				return false;
		} else if (!qualidadeServico.equals(other.qualidadeServico))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AvaliacaoAssistente [qualidadeServico=" + qualidadeServico + ", getId()=" + getId()
				+ ", getCordialidade()=" + getCordialidade() + ", getPontualidade()=" + getPontualidade()
				+ ", getData()=" + getData() + ", getComentario()=" + getComentario() + "]";
	}

}
