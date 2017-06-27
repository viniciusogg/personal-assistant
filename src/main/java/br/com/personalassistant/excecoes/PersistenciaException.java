package br.com.personalassistant.excecoes;

public class PersistenciaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public PersistenciaException(String mensagem){
		super(mensagem);
	}

}
