package br.com.personalassistant.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.entidades.Capacidade;
import br.com.personalassistant.entidades.CategoriaServico;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AssistenteService;
import br.com.personalassistant.services.CategoriaServicoService;

@Named
@ViewScoped
public class CadastroAssistenteBean implements Serializable {

	@Inject private AssistenteService assistenteService;
	@Inject private CategoriaServicoService categoriaServicoService;
	private static final long serialVersionUID = 1L;
	private Assistente assistente;
	private String nomeCategoriaServico;
	private List<String> nomesCapacidades;
	private List<String> nomesCategoriasServicos;
	private List<CategoriaServico> categoriasServicos;

	public void preRenderView(){
		
		if(this.assistente == null){
			this.assistente = new Assistente();
		}
		
		nomesCapacidades = new ArrayList<String>();
		nomesCategoriasServicos = new ArrayList<String>();
		
		
		try {
			categoriasServicos = categoriaServicoService.getAll();

			for(CategoriaServico categoriaServico: categoriasServicos){
				nomesCategoriasServicos.add(categoriaServico.getNome());
			}
		} 
		catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	public void salvarAssistente(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			List<Capacidade> capacidades = new ArrayList<Capacidade>();
			CategoriaServico categoriaServico = null;
						
			for(String nomeCapacidade: nomesCapacidades){	
				Capacidade capacidade = new Capacidade();
				capacidade.setNome(nomeCapacidade);
				capacidades.add(capacidade);
			}
			
			for(CategoriaServico cs: categoriasServicos){
				if(cs.getNome().equals(this.nomeCategoriaServico)){
					categoriaServico = cs;
				}
			}
			
			this.assistente.setCapacidades(capacidades);
						
			this.assistente.setCategoriaServico(categoriaServico);
			
			assistenteService.save(this.assistente);
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com sucesso", ""));
		} 
		catch (ServiceException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro", ""));
			e.printStackTrace();
		}
	}

	public Assistente getAssistente() {
		return assistente;
	}
	
	public void setAssistente(Assistente assistente) {
		this.assistente = assistente;
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
