package br.com.personalassistant.entidades;

import java.util.List;

public class CategoriaServico {

	private int id;
	private String nome;
	private List<PalavraChave> palavrasChave; // unidirecional

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<PalavraChave> getPalavrasChave(){
		return palavrasChave;
	}

	public void setPalavrasChave(List<PalavraChave> palavrasChave){
		this.palavrasChave = palavrasChave;
	}
}
