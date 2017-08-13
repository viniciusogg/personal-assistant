package br.com.personalassistant.beans.assistente;

import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.entidades.Negociacao;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AssistenteService;
import br.com.personalassistant.services.NegociacaoService;

@Named
@ViewScoped
public class NegociacoesAstBean extends AbstractBean {

	private static final long serialVersionUID = -6699220932723101796L;

	@Inject private NegociacaoService negociacaoService;
	@Inject private AssistenteService assistenteService;
	private List<Negociacao> negociacoes;
	private Assistente assistente;
	
	public void preRenderView(){
		
		try {
			this.assistente = assistenteService.getAssistenteByEmail(getEmailUsuarioLogado());
			
			this.negociacoes = negociacaoService.getToAssistenteAllById(assistente.getPk().getId());
		} 
		catch (ServiceException | NaoExistemObjetosException | ObjetoNaoExisteException e) {
			e.printStackTrace();
		}
	}

	public void verDetalhesNegociacao(String idNegociacao){
		
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.getExternalContext().redirect(facesContext.getExternalContext().getApplicationContextPath() + "/assistente/negociacao.xhtml?id="+idNegociacao);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}

	public void setNegociacoes(List<Negociacao> negociacoes) {
		this.negociacoes = negociacoes;
	}

	public Assistente getAssistente() {
		return assistente;
	}

	public void setAssistente(Assistente assistente) {
		this.assistente = assistente;
	}
	
}
