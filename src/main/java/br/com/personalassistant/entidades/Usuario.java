package br.com.personalassistant.entidades;

public abstract class Usuario {

	private int id;
	private String nome;
	private String email;
	private String senha;
	private String numTelefonico;

	public int getId() {
		return id;
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
}
