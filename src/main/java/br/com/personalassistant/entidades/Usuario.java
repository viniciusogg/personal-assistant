package br.com.personalassistant.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.personalassistant.enums.TIPO_USUARIO;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Discriminador", discriminatorType = DiscriminatorType.STRING)
@Table(name = "TB_USUARIO", uniqueConstraints = {@UniqueConstraint(name = "UC_USUARIO", columnNames = {"email"})})
@Entity(name = "Usuario")
public abstract class Usuario implements Serializable{

	private static final long serialVersionUID = 1694937550826272267L;

	@Id
	@EmbeddedId
	private PK pk;
	
	@Column(nullable = false)
	private String nome;
	
	private String email;

	private String senha;
	
	@Column(nullable = false)
	private String numTelefonico;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TIPO_USUARIO tipoUsuario;

	public PK getPk() {
		return pk;
	}

	public void setPk(PK pk) {
		this.pk = pk;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNumTelefonico() {
		return numTelefonico;
	}

	public void setNumTelefonico(String numTelefonico) {
		this.numTelefonico = numTelefonico;
	}

	public TIPO_USUARIO getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TIPO_USUARIO tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numTelefonico == null) ? 0 : numTelefonico.hashCode());
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((tipoUsuario == null) ? 0 : tipoUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numTelefonico == null) {
			if (other.numTelefonico != null)
				return false;
		} else if (!numTelefonico.equals(other.numTelefonico))
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (tipoUsuario != other.tipoUsuario)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [pk=" + pk + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", numTelefonico="
				+ numTelefonico + ", tipoUsuario=" + tipoUsuario + "]";
	}

}
