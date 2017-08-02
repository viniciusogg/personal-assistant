package br.com.personalassistant.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
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
	private String nomeCategoriaServico;
	private List<String> nomesCapacidades;
	private List<String> nomesCategoriasServicos;
	private List<CategoriaServico> categoriasServicos;

	public void preRenderView(){
		
		if(this.assistente == null){
			this.assistente = new Assistente();			
		}
		
		if(this.endereco == null){
			this.endereco = new Endereco();
		}
		
		this.nomesCapacidades = new ArrayList<String>();
		this.nomesCategoriasServicos = new ArrayList<String>();
		
		try {
			this.categoriasServicos = this.categoriaServicoService.getAll();

			for(CategoriaServico categoriaServico: this.categoriasServicos){
				this.nomesCategoriasServicos.add(categoriaServico.getNome());
			}
		} 
		catch (ServiceException | NaoExistemObjetosException e) {
			e.printStackTrace();
		}
	}
	
	public void salvarAssistente(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			
			List<Capacidade> capacidades = new ArrayList<Capacidade>();
						
			for(String nomeCapacidade: this.nomesCapacidades){	
				Capacidade capacidade = new Capacidade();
				capacidade.setNome(nomeCapacidade);
				capacidades.add(capacidade);
			}
			
			CategoriaServico categoriaServico = null;
						
			for(CategoriaServico cs: categoriasServicos){
				if(cs.getNome().equals(this.nomeCategoriaServico)){
					categoriaServico = cs;
				}
			}
			
			this.assistente.setCapacidades(capacidades);						
			this.assistente.setCategoriaServico(categoriaServico);
			this.assistente.setEndereco(this.endereco);
			this.assistente.setTipoUsuario(TIPO_USUARIO.ASSISTENTE);
			
			this.usuarioService.criptografarSenha(assistente);
			this.usuarioService.save(this.assistente);
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com sucesso, faça login na sua conta", ""));
		} 
		catch (ServiceException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro, tente novamente", ""));
			e.printStackTrace();
		}
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

	public String getNomeCategoriaServico() {
		return nomeCategoriaServico;
	}

	public void setNomeCategoriaServico(String nomeCategoriaServico) {
		this.nomeCategoriaServico = nomeCategoriaServico;
	}

	public List<String> getNomesCapacidades() {
		return nomesCapacidades;
	}

	public void setNomesCapacidades(List<String> nomesCapacidades) {
		this.nomesCapacidades = nomesCapacidades;
	}

	public List<String> getNomesCategoriasServicos() {
		return nomesCategoriasServicos;
	}

	public void setNomesCategoriasServicos(List<String> nomesCategoriasServicos) {
		this.nomesCategoriasServicos = nomesCategoriasServicos;
	}
}
