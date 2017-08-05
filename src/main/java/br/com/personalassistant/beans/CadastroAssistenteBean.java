package br.com.personalassistant.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.entidades.Capacidade;
import br.com.personalassistant.entidades.CategoriaServico;
import br.com.personalassistant.entidades.Endereco;
import br.com.personalassistant.enums.TIPO_USUARIO;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.CategoriaServicoService;
import br.com.personalassistant.services.UsuarioService;

@Named
@ViewScoped
public class CadastroAssistenteBean extends AbstractBean {

	private static final long serialVersionUID = -6649800179400957138L;

	@Inject private UsuarioService usuarioService;
	@Inject private CategoriaServicoService categoriaServicoService;
	private Assistente assistente;
	private Endereco endereco;
	private List<String> nomesCapacidades;
	private List<CategoriaServico> categoriasServicos;
	private String nomeCategoriaServico;

	public void preRenderView(){
		
		if(this.assistente == null){
			this.assistente = new Assistente();
			this.assistente.setCapacidades(new ArrayList<Capacidade>());
		}
		
		if(this.endereco == null){
			this.endereco = new Endereco();
		}
		
		this.nomesCapacidades = new ArrayList<String>();
		
		try {
			this.categoriasServicos = this.categoriaServicoService.getAll();
		} 
		catch (ServiceException | NaoExistemObjetosException e) {
			e.printStackTrace();
		}
	}
	
	public void salvarAssistente(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		Severity severity = null;
		String msg = "";
		
		try {
						
			for(String nomeCapacidade: this.nomesCapacidades){	
				this.assistente.getCapacidades().add(new Capacidade(nomeCapacidade));
			}
						
			this.assistente.setEndereco(this.endereco);
			this.assistente.setTipoUsuario(TIPO_USUARIO.ASSISTENTE);
			
			this.usuarioService.criptografarSenha(assistente);
			
			this.usuarioService.save(this.assistente);
			
			severity = FacesMessage.SEVERITY_INFO;
			msg = "Cadastrado com sucesso, faça login na sua conta";
		} 
		catch (ServiceException e) {
			severity = FacesMessage.SEVERITY_ERROR;
			msg = "Ocorreu um erro, tente novamente";
			
			e.printStackTrace();
		}
		
		context.addMessage(null, new FacesMessage(severity, msg, ""));
	}

	public Assistente getAssistente() {
		return assistente;
	}
	
	public void setAssistente(Assistente assistente) {
		this.assistente = assistente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<String> getNomesCapacidades() {
		return nomesCapacidades;
	}

	public void setNomesCapacidades(List<String> nomesCapacidades) {
		this.nomesCapacidades = nomesCapacidades;
	}

	public List<CategoriaServico> getCategoriasServicos() {
		return categoriasServicos;
	}

	public void setCategoriasServicos(List<CategoriaServico> categoriasServicos) {
		this.categoriasServicos = categoriasServicos;
	}

	public String getNomeCategoriaServico() {
		return nomeCategoriaServico;
	}

	public void setNomeCategoriaServico(String nomeCategoriaServico) {
		this.nomeCategoriaServico = nomeCategoriaServico;
	}

}
