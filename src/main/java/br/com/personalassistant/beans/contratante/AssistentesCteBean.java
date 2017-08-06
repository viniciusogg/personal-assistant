package br.com.personalassistant.beans.contratante;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.entidades.Negociacao;
import br.com.personalassistant.entidades.Proposta;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AssistenteService;
import br.com.personalassistant.services.ContratanteService;
import br.com.personalassistant.services.NegociacaoService;

@ViewScoped
@Named
public class AssistentesCteBean extends AbstractBean {

	private static final long serialVersionUID = 3584566994926099104L;

	@Inject private AssistenteService assistenteService;
	@Inject private ContratanteService contratanteService;
	@Inject private NegociacaoService negociacaoService;
	private Assistente assistente; 
	private Contratante contratante;
	private List<Assistente> assistentes;
	private Proposta proposta;
	private Negociacao negociacao;
	private boolean precisaEndereco;
	
	public void preRenderView() {
				
		try {
			this.contratante = contratanteService.getContratanteByEmail(getEmailUsuarioLogado());
		
			this.negociacao = new Negociacao();			
			this.negociacao.setContratante(this.contratante);

			this.proposta = new Proposta();
			this.proposta.setContratante(this.contratante);
			this.proposta.setNegociacao(this.negociacao);
			
			this.assistentes = assistenteService.getAll();
		}
		catch (ServiceException | ObjetoNaoExisteException | NaoExistemObjetosException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarProposta(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		String msg = "";
		Severity severity = null;
		
		try {			
			this.proposta.setAssistente(this.assistente);
			
			if(this.precisaEndereco){
				this.proposta.setEndereco(this.contratante.getEndereco());
			}
			
			this.negociacao.setAssistente(this.assistente);
			this.negociacao.getPropostas().add(this.proposta);
			
			this.negociacaoService.save(this.negociacao);
			
			severity = FacesMessage.SEVERITY_INFO;
			msg = "Proposta enviada com sucesso";
		} 
		catch (ServiceException e) {
			severity = FacesMessage.SEVERITY_INFO;
			msg = "Falha ao enviar proposta";
			
			e.printStackTrace();
		}
		
		context.addMessage(null, new FacesMessage(severity, msg, ""));				
	}

	public List<Assistente> getAssistentes() {
		return assistentes;
	}

	public void setAssistentes(List<Assistente> assistentes) {
		this.assistentes = assistentes;
	}
	
	public Proposta getProposta() {
		return proposta;
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}
	
	public boolean isPrecisaEndereco() {
		return precisaEndereco;
	}

	public void setPrecisaEndereco(boolean precisaEndereco) {
		this.precisaEndereco = precisaEndereco;
	}
	
	public Assistente getAssistente() {
		return assistente;
	}

	public void setAssistente(Assistente assistente) {
		this.assistente = assistente;
	}

}
