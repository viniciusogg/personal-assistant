package br.com.personalassistant.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.entidades.Endereco;
import br.com.personalassistant.entidades.PK;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.EnderecoService;
import br.com.personalassistant.services.UsuarioService;

@ViewScoped
@Named
public class CadastroContratanteBean extends AbstractBean{

	private static final long serialVersionUID = 6467407687238883757L;

	@Inject private UsuarioService usuarioService;
	@Inject private EnderecoService enderecoService;
	private Contratante contratante;
	private Endereco endereco;
	
	public void preRenderView(){
				
		if(this.contratante == null){
			this.contratante = new Contratante();
		}
		
		if(this.endereco == null){
			this.endereco = new Endereco();
		}
	}
	
	public void salvarContratante(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			
			this.endereco.setPk(new PK(this.usuarioService.generateId()));
			
			this.contratante.setPk(new PK(this.usuarioService.generateId()));
			this.contratante.setEndereco(this.endereco);
			
			this.usuarioService.criptografarSenha(this.contratante);
			
			this.enderecoService.save(this.endereco);
			this.usuarioService.save(this.contratante);

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com sucesso, faça login na sua conta", ""));
		} 
		catch (ServiceException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro, tente novamente", ""));
			e.printStackTrace();
		}
	}

	public Contratante getContratante() {
		return contratante;
	}
	
	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
