package br.com.personalassistant.beans.contratante;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.entidades.Endereco;
import br.com.personalassistant.entidades.PK;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.ContratanteService;
import br.com.personalassistant.services.EnderecoService;
import br.com.personalassistant.services.UsuarioService;

@Named
@ViewScoped
public class PerfilCteBean extends AbstractBean {

	private static final long serialVersionUID = 3570137008332816L;

	@Inject private UsuarioService usuarioService;
	@Inject private ContratanteService contratanteService;
	@Inject private EnderecoService enderecoService;
	private Contratante contratante;
	private boolean isEdicao;
	private boolean renderizarPainelVisualizacao = true;
	
	public void preRenderView(){
		
		this.isEdicao = false;
		
		String email = getEmailUsuarioLogado();
		
		try {
			this.contratante = (Contratante) usuarioService.getUsuarioByEmail(email);
		} 
		catch (ServiceException | ObjetoNaoExisteException e) {
			e.printStackTrace();
		}
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}
	
	public void editarPerfil(){
		this.isEdicao = true;
		this.renderizarPainelVisualizacao = false;
	}
	
	public void concluirEdicao(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		Severity severity = null;
		String msg = "";
				
		try {
			
			//contratanteNoBanco
			Contratante contratanteDesatualizado = (Contratante) this.usuarioService.getById(this.contratante.getPk());
			
			// alterou os dois
			if(!contratanteDesatualizado.equals(this.contratante) && !this.contratante.getEndereco().equals(contratanteDesatualizado.getEndereco())){
								
				/*Endereco novoEndereco = this.contratante.getEndereco();
				
				novoEndereco.setPk(new PK(this.contratante.getEndereco().getPk().getId()));
				
				this.enderecoService.save(novoEndereco);
				
				this.contratante.setEndereco(novoEndereco);*/
				atualizarEndereco(this.contratante);
				
				
				
				/*contratanteDesatualizado.setEmail(null);
				
				this.usuarioService.update(contratanteDesatualizado);
				
				this.contratante.setPk(new PK(this.contratante.getPk().getId()));
				
				this.usuarioService.save(this.contratante);*/
				atualizarContratante(contratanteDesatualizado, this.contratante);
			}
			
			// só alterou endereço			
			else if(!this.contratante.getEndereco().equals(contratanteDesatualizado.getEndereco()) && 
					contratanteDesatualizado.equals(this.contratante)){
								
				atualizarEndereco(this.contratante);
			}
			
			// só alterou usuario
			else if(!contratanteDesatualizado.equals(this.contratante) && 
					this.contratante.getEndereco().equals(contratanteDesatualizado.getEndereco())){
													
				atualizarContratante(contratanteDesatualizado, this.contratante);
			}		
			
			msg = "Perfil atualizado com sucesso";
			severity = FacesMessage.SEVERITY_INFO;
		}
		catch (ServiceException | ObjetoNaoExisteException e) {
			msg = "Ocorreu uma falha ao tentar atualizar o perfil";
			severity = FacesMessage.SEVERITY_ERROR;
			e.printStackTrace();
		}
		
		context.addMessage(null, new FacesMessage(severity, msg, ""));				
	}
	
	public void removerContaContratante(){

		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			
			this.usuarioService.delete(this.contratante);
							
			HttpSession httpSession = (HttpSession) context.getExternalContext().getSession(false);
			httpSession.invalidate();
			
			context.getExternalContext().redirect(context.getExternalContext().
					getApplicationContextPath() + "/login.xhtml?faces-redirect=true");
		} 
		catch (ServiceException | IOException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			e.printStackTrace();
		}
		
	}
	
	public void alterarSenha(){
		
	}

	public boolean isRenderizarPainelVisualizacao() {
		return renderizarPainelVisualizacao;
	}

	public void setRenderizarPainelVisualizacao(boolean renderizarPainelVisualizacao) {
		this.renderizarPainelVisualizacao = renderizarPainelVisualizacao;
	}
	
	private void atualizarContratante(Contratante contratanteDesatualizado, Contratante contratanteAtualizado) 
			throws ServiceException{

		contratanteDesatualizado.setEmail(null);
		contratanteDesatualizado.setSenha(null);
		
		this.usuarioService.update(contratanteDesatualizado);

		contratanteAtualizado.setPk(new PK(contratanteAtualizado.getPk().getId()));
		
		this.usuarioService.save(contratanteAtualizado);
	}
	
	private void atualizarEndereco(Contratante contratanteAtualizado) throws ServiceException{

		Endereco novoEndereco = contratanteAtualizado.getEndereco();
		
		novoEndereco.setPk(new PK(contratanteAtualizado.getEndereco().getPk().getId()));
		
		this.enderecoService.save(novoEndereco);
		
		contratanteAtualizado.setEndereco(novoEndereco);
		
		this.usuarioService.update(contratanteAtualizado);
	}
	
}
