package br.com.personalassistant.beans.assistente;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.entidades.Capacidade;
import br.com.personalassistant.entidades.CategoriaServico;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AssistenteService;
import br.com.personalassistant.services.CategoriaServicoService;

@Named
@ViewScoped
public class EdicaoAreaAtuacaoAstBean extends AbstractBean {

	private static final long serialVersionUID = 7212775031772715640L;

	@Inject private AssistenteService assistenteService;
	@Inject private CategoriaServicoService categoriaServicoService;
	private Assistente assistente;
	private List<CategoriaServico> categoriasServicos;
	private List<String> nomesCapacidades;
	
	public void preRenderView(){
		try {
			this.categoriasServicos = this.categoriaServicoService.getAll();
			this.assistente = this.assistenteService.getAssistenteByEmail(getEmailUsuarioLogado());
			
			this.nomesCapacidades = new ArrayList<String>();
			
			for(Capacidade c: assistente.getCapacidades()){
				nomesCapacidades.add(c.getNome());
			}
		} 
		catch (ServiceException | ObjetoNaoExisteException | NaoExistemObjetosException e) {
			e.printStackTrace();
		}
	}

	public void concluirEdicao(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		
		String msg = "";
		Severity severity = null;
		
		List<Capacidade> capacidades = new ArrayList<Capacidade>();
		
		try {
			for(String nomeCapacidade: nomesCapacidades){
				capacidades.add(new Capacidade(nomeCapacidade));
			}
			
			this.assistente.setCapacidades(capacidades);
			
			this.assistenteService.update(this.assistente);
			
			msg = "Área de atuação atualizada com sucesso";
			severity = FacesMessage.SEVERITY_INFO;
		} 
		catch (ServiceException e) {
			e.printStackTrace();
			msg = e.getMessage();
			severity = FacesMessage.SEVERITY_ERROR;
		}
		
		facesContext.addMessage(null, new FacesMessage(severity, msg, ""));
	}
	
	public Assistente getAssistente() {
		return assistente;
	}

	public void setAssistente(Assistente assistente) {
		this.assistente = assistente;
	}

	public List<CategoriaServico> getCategoriasServicos() {
		return categoriasServicos;
	}

	public void setCategoriasServicos(List<CategoriaServico> categoriasServicos) {
		this.categoriasServicos = categoriasServicos;
	}

	public List<String> getNomesCapacidades() {
		return nomesCapacidades;
	}

	public void setNomesCapacidades(List<String> nomesCapacidades) {
		this.nomesCapacidades = nomesCapacidades;
	}
	
	
}
