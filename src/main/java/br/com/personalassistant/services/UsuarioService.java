package br.com.personalassistant.services;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;

import br.com.personalassistant.dao.UsuarioDAO;
import br.com.personalassistant.entidades.Usuario;
import br.com.personalassistant.enums.TIPO_USUARIO;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.PersistenciaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.util.Transacional;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = -8991038872014020682L;

	@Inject private UsuarioDAO usuarioDAO;
	
	@Transacional
	public void save(Usuario usuario) throws ServiceException{
		try{
			this.usuarioDAO.save(usuario);
		} 
		catch(PersistenciaException ex) {
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public void delete(Usuario usuario) throws ServiceException{
		try{
			this.usuarioDAO.delete(usuario);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	@Transacional
	public Usuario update(Usuario usuario) throws ServiceException{
		try{
			return this.usuarioDAO.update(usuario);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}		
	}
	
	public Usuario getById(Long id) throws ServiceException, ObjetoNaoExisteException{
		try{
			return this.usuarioDAO.getById(id);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public List<Usuario> getAll() throws ServiceException{
		try{
			return this.usuarioDAO.getAll();
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}

	public TIPO_USUARIO getTipoUsuarioByEmail(String email) throws ServiceException, ObjetoNaoExisteException{
		try{
			return this.usuarioDAO.getTipoUsuarioByEmail(email);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public Usuario getUsuarioByEmail(String email) throws ServiceException, ObjetoNaoExisteException{
		try{
			return this.usuarioDAO.getUsuarioByEmail(email);
		}
		catch(PersistenciaException ex){
			throw new ServiceException(ex.getMessage());
		}
	}
	
	public void criptografarSenha(Usuario usuario) throws ServiceException {
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
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
