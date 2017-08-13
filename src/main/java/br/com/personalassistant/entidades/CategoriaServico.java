package br.com.personalassistant.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name = "TB_CATEGORIA_SERVICO", uniqueConstraints = {
		@UniqueConstraint(name = "UC_CATEGORIA_SERVICO",columnNames = {"nome"})})
@Entity(name = "CategoriaServico")
public class CategoriaServico implements Serializable{

	private static final long serialVersionUID = 5931600135186301826L;

	@EmbeddedId
	private PK pk;
	
	@Column(nullable = false)
	private String nome;

	public CategoriaServico(){
		super();
	}
	
	public CategoriaServico(String nome) {
		super();
		this.nome = nome;
	}

	public PK getPk() {
		return pk;
	}
	
	public void setPk(PK pk){
		this.pk = pk;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		CategoriaServico other = (CategoriaServico) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoriaServico [pk=" + pk + ", nome=" + nome + "]";
	}
	
}
