package br.com.personalassistant.beans.administrador;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.CategoriaServico;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.RemocaoNaoPermitidaException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AssistenteService;
import br.com.personalassistant.services.CategoriaServicoService;

@ViewScoped
@Named
public class CategoriasServicosAdmBean extends AbstractBean{

	private static final long serialVersionUID = -7752224083271420472L;

	@Inject private CategoriaServicoService categoriaServicoService;
	@Inject private AssistenteService assistenteService;
	private CategoriaServico categoriaServico;
	private List<CategoriaServico> lista;
	private boolean isEdicao;
	private String tituloPanel;
	
	public void preRenderView(){
								
		if(this.categoriaServico == null){
			this.categoriaServico = new CategoriaServico();
			this.isEdicao = false;
			this.tituloPanel = "NOVA CATEGORIA DE SERVIÇO";
		}
		else{
			this.tituloPanel = "EDITAR CATEGORIA DE SERVIÇO";
		}

		try {
			this.lista = categoriaServicoService.getAll();
		} 
		catch (ServiceException | NaoExistemObjetosException e) {
			e.printStackTrace();
		}
	}

	public void salvarCategoriaServico(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		String msg = "";
		Severity severity = null;

		try {
			if(!this.isEdicao){
				this.categoriaServicoService.save(this.categoriaServico);
				msg = "Categoria '" + this.categoriaServico.getNome() + "' cadastrada com sucesso";
			}
			else{
				this.categoriaServicoService.update(this.categoriaServico);
				msg = "Categoria '" + this.categoriaServico.getNome() + "' atualizada com sucesso";;
			}
						
			severity = FacesMessage.SEVERITY_INFO;
		} 
		catch (ServiceException e) {
			severity = FacesMessage.SEVERITY_ERROR;
			msg = e.getMessage();
			e.printStackTrace();
		}
		
		context.addMessage(null, new FacesMessage(severity, msg, ""));				
	}
	
	public void editarCategoriaServico(){
		this.isEdicao = true;
	}
	
	public void deletarCategoriaServico(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		String nomeCategoriaRemovida = this.categoriaServico.getNome();
		String msg = "";
		Severity severity = null;
		
		try {
						
			Long quantidadeAssistentes = this.assistenteService.getQuantidadeByCategoriaServico(nomeCategoriaRemovida);
			
			if(quantidadeAssistentes > 0){
				throw new RemocaoNaoPermitidaException("Não é possível remover a categoria de serviço, pois existem assistentes associados à ela");
			}
			
			this.categoriaServicoService.delete(this.categoriaServico);
			
			severity = FacesMessage.SEVERITY_INFO;
			msg = "Categoria '" + nomeCategoriaRemovida + "' removida com sucesso";
		} 
		catch (ServiceException | ObjetoNaoExisteException e) {
			severity = FacesMessage.SEVERITY_ERROR;
			msg = e.getMessage();
			e.printStackTrace();
		} 
		catch (RemocaoNaoPermitidaException e) {
			msg = e.getMessage();
			severity = FacesMessage.SEVERITY_ERROR;
		}
	
		context.addMessage(null, new FacesMessage(severity, msg, ""));	
	}
	
	public CategoriaServico getCategoriaServico() {
		return categoriaServico;
	}

	public void setCategoriaServico(CategoriaServico categoriaServico) {
		this.categoriaServico = categoriaServico;
	}

	public List<CategoriaServico> getLista() {
		return lista;
	}

	public void setLista(List<CategoriaServico> lista) {
		this.lista = lista;
	}

	public String getTituloPanel() {
		return tituloPanel;
	}

	public void setTituloPanel(String tituloPanel) {
		this.tituloPanel = tituloPanel;
	}
	
}
