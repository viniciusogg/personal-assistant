package br.com.personalassistant.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Avaliacao")
@Table(name = "TB_AVALIACAO")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TIPO", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Avaliacao")
public class Avaliacao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(nullable = false)
	private Integer cordialidade;
	
	@Column(nullable = false)
	private Integer pontualidade;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;
	
	private String comentario;

	public Long getId() {
		return id;
	}

	public Integer getCordialidade() {
		return cordialidade;
	}

	public void setCordialidade(Integer cordialidade) {
		this.cordialidade = cordialidade;
	}

	public Integer getPontualidade() {
		return pontualidade;
	}

	public void setPontualidade(Integer pontualidade) {
		this.pontualidade = pontualidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((cordialidade == null) ? 0 : cordialidade.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pontualidade == null) ? 0 : pontualidade.hashCode());
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
		Avaliacao other = (Avaliacao) obj;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (cordialidade == null) {
			if (other.cordialidade != null)
				return false;
		} else if (!cordialidade.equals(other.cordialidade))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pontualidade == null) {
			if (other.pontualidade != null)
				return false;
		} else if (!pontualidade.equals(other.pontualidade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Avaliacao [id=" + id + ", cordialidade=" + cordialidade + ", pontualidade=" + pontualidade + ", data="
				+ data + ", comentario=" + comentario + "]";
	}

}
