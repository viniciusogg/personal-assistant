package br.com.personalassistant.entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.personalassistant.enums.TIPO_USUARIO;

@DiscriminatorValue("admin")
@Table(name = "TB_ADMINISTRADOR")
@Entity(name = "Administrador")
public class Administrador extends Usuario{

	private static final long serialVersionUID = 7799842984118080272L;

	public Administrador(){
		super();
	}
	
	public Administrador(String nome, String numTelefonico, 
			String email, String senha) {
		this.setNome(nome);
		this.setNumTelefonico(numTelefonico);
		this.setEmail(email);
		this.setSenha(senha);
		this.setTipoUsuario(TIPO_USUARIO.ADMINISTRADOR);
	}

	@Override
	public String toString() {
		return "Administrador [getPk()=" + getPk() + ", getNome()=" + getNome() + ", getEmail()=" + getEmail()
				+ ", getSenha()=" + getSenha() + ", getNumTelefonico()=" + getNumTelefonico() + ", getTipoUsuario()="
				+ getTipoUsuario() + "]";
	}
	
}
