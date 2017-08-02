package br.com.personalassistant.excecoes;

public class NaoExistemObjetosException extends Exception {
	
	private static final long serialVersionUID = -568455582674113679L;

	public NaoExistemObjetosException(String message) {
		super(message);
	}

}
