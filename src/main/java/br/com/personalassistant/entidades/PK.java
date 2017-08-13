package br.com.personalassistant.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.personalassistant.util.DataAtual;

@Embeddable
public class PK implements Serializable {

	private static final long serialVersionUID = -5129078363842675616L;

	@Column(name = "id_PK")
	private Long id;
	
	@Column(name = "ultimaAtualizacao_PK")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaAtualizacao;
	
	public PK(Long id){
		this.id = id;	
		this.ultimaAtualizacao = DataAtual.getDataAtual();
	}
	
	public PK(){
		this.ultimaAtualizacao = DataAtual.getDataAtual();
	}

	public Long getId() {
		return id;
	}

	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ultimaAtualizacao == null) ? 0 : ultimaAtualizacao.hashCode());
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
		PK other = (PK) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ultimaAtualizacao == null) {
			if (other.ultimaAtualizacao != null)
				return false;
		} else if (!ultimaAtualizacao.equals(other.ultimaAtualizacao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PK [id=" + id + ", ultimaAtualizacao=" + ultimaAtualizacao + "]";
	}
	
}
