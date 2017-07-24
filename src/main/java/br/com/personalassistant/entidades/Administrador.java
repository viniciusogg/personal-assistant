package br.com.personalassistant.entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.personalassistant.enums.TIPO_USUARIO;

@DiscriminatorValue("Admin")
@Table(name = "TB_ADMINISTRADOR")
@Entity(name = "Administrador")
public class Administrador extends Usuario{

	private static final long serialVersionUID = 1L;

	public Administrador(){
		super();
	}
	
	public Administrador(String nome, String numTelefonico, 
			String email, String senha) {
		setNome(nome);
		setNumTelefonico(numTelefonico);
		setEmail(email);
		setSenha(senha);
		setTipoUsuario(TIPO_USUARIO.ADMINISTRADOR);
	}
}
