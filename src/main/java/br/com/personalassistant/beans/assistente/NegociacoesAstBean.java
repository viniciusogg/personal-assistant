package br.com.personalassistant.beans.assistente;

import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.entidades.Proposta;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AssistenteService;
import br.com.personalassistant.services.PropostaService;

@Named
@ViewScoped
public class NegociacoesAstBean extends AbstractBean {

	private static final long serialVersionUID = -6699220932723101796L;

	@Inject private PropostaService propostaService;
	@Inject private AssistenteService assistenteService;
	private List<Proposta> propostas;
	private Assistente assistente;
	
	public void preRenderView(){
		
		try {
			this.assistente = assistenteService.getAssistenteByEmail(getEmailUsuarioLogado());
			
			this.propostas = propostaService.getToAssistenteAllById(assistente.getId());
		} 
		catch (ServiceException | NaoExistemObjetosException | ObjetoNaoExisteException e) {
			e.printStackTrace();
		}
	}

	public List<Proposta> getPropostas() {
		return propostas;
	}

	public void setPropostas(List<Proposta> propostas) {
		this.propostas = propostas;
	}

	public Assistente getAssistente() {
		return assistente;
	}

	public void setAssistente(Assistente assistente) {
		this.assistente = assistente;
	}

	public void verDetalhesProposta(String idProposta){
				
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.getExternalContext().redirect(facesContext.getExternalContext().getApplicationContextPath() + "/assistente/proposta.xhtml?id="+idProposta);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
