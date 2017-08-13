package br.com.personalassistant.beans.contratante;

import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.entidades.Negociacao;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.ContratanteService;
import br.com.personalassistant.services.NegociacaoService;
import br.com.personalassistant.services.UsuarioService;

@Named
@ViewScoped
public class NegociacoesCteBean extends AbstractBean {

	private static final long serialVersionUID = -8447393094330218025L;

	@Inject private NegociacaoService negociacaoService;
	@Inject private ContratanteService contratanteService;
	@Inject private UsuarioService usuarioService;
	private List<Negociacao> negociacoes;
	private Contratante contratante;
	
	public void preRenderView(){
		
		try {
			this.contratante = (Contratante) usuarioService.getUsuarioByEmail(getEmailUsuarioLogado());
			
			this.negociacoes = negociacaoService.getToContratanteAllById(contratante.getPk().getId());
		} 
		catch (ServiceException | NaoExistemObjetosException | ObjetoNaoExisteException e) {
			e.printStackTrace();
		}
	}

	public void verDetalhesNegociacao(String idNegociacao){
		
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.getExternalContext().redirect(facesContext.getExternalContext().getApplicationContextPath() + "/contratante/negociacao.xhtml?id="+idNegociacao);
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

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}
	
}
