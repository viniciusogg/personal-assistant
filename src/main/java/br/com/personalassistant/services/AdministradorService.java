package br.com.personalassistant.services;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.AdministradorDAO;
import br.com.personalassistant.entidades.Administrador;
import br.com.personalassistant.entidades.PK;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class AdministradorService implements Serializable {

	private static final long serialVersionUID = 8026683165515507191L;

	@Inject
	private AdministradorDAO administradorDAO;
	
	@Transacional
	public void save(Administrador administrador) throws ServiceException{
		try{
			this.administradorDAO.save(administrador);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(Administrador administrador) throws ServiceException{
		try{
			this.administradorDAO.delete(administrador);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public Administrador update(Administrador administrador) throws ServiceException{
		try{
			return this.administradorDAO.update(administrador);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public Administrador getById(PK pk) throws ServiceException, ObjetoNaoExisteException{
		try{
			return this.administradorDAO.getById(pk);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Administrador> getAll() throws ServiceException, NaoExistemObjetosException{
		try{
			return this.administradorDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public PK getIdByEmail(String email) throws ObjetoNaoExisteException, ServiceException{
		try {
			return this.administradorDAO.getIdByEmail(email);
		} 
		catch (PersistenciaException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void criptografarSenha(Administrador administrador) throws ServiceException {
		administrador.setSenha(criptografarSenha(administrador.getSenha()));
	}

	/**
	 * Método que criptografa uma dada senha usando o método hash SHA-256.
	 * 
	 * @param password
	 *            senha a ser criptografada
	 * @return senha criptografada
	 * @throws ServiceDacaException
	 *             lançada caso ocorra algum erro durante o processo
	 */
	private static String criptografarSenha(String senha) throws ServiceException {
		
		MessageDigest messageDigest;
		
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(senha.getBytes("UTF-8"));
			
			byte[] digest = messageDigest.digest();
			
			BigInteger bigInt = new BigInteger(1, digest);
			
			String output = bigInt.toString(16);
			
			return output;
		}
		catch (NoSuchAlgorithmException e) {
			throw new ServiceException("Não foi possível criptografar a senha!");
		} 
		catch (UnsupportedEncodingException e) {
			throw new ServiceException("Não foi possível criptografar a senha!");
		}
	}

}
