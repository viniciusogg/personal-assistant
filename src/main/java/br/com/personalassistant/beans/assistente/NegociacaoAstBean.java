package br.com.personalassistant.beans.assistente;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Negociacao;
import br.com.personalassistant.entidades.Proposta;
import br.com.personalassistant.entidades.Servico;
import br.com.personalassistant.enums.ESTADO_NEGOCIACAO;
import br.com.personalassistant.enums.TIPO_USUARIO;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.NegociacaoService;
import br.com.personalassistant.services.PropostaService;
import br.com.personalassistant.services.ServicoService;

@Named
@ViewScoped
public class NegociacaoAstBean extends AbstractBean {

	private static final long serialVersionUID = -2763195489293393140L;

	@Inject private NegociacaoService negociacaoService;
	@Inject private PropostaService propostaService;
	@Inject private ServicoService servicoService;
	private Negociacao negociacao;
	private Proposta propostaEditada;
	private boolean renderizarBotoes;
	
	public void preRenderView(){
		
		Collections.sort(this.negociacao.getPropostas(), new Comparator<Proposta>(){
			public int compare(Proposta p1, Proposta p2) {	
				return p1.getDataProposta().compareTo(p2.getDataProposta());
			}
		});
		
		Proposta ultimaProposta = this.negociacao.getPropostas().get(this.negociacao.getPropostas().size() - 1);
		
		if(ultimaProposta.getAutorProposta().getTipoUsuario() != TIPO_USUARIO.ASSISTENTE){
			this.renderizarBotoes = true;
		}
		
		this.propostaEditada = new Proposta();
		
		this.propostaEditada.setEndereco(ultimaProposta.getEndereco());	
		this.propostaEditada.setComentario("");
		this.propostaEditada.setDataRealizacaoServico(ultimaProposta.getDataRealizacaoServico());
		this.propostaEditada.setNegociacao(this.negociacao);
		this.propostaEditada.setPreco(ultimaProposta.getPreco());
		this.propostaEditada.setTipoPagamento(ultimaProposta.getTipoPagamento());
		this.propostaEditada.setAutorProposta(this.negociacao.getAssistente());
	}
	
	public void editarProposta(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		
		String msg = "";
		Severity severity = null;
		
		try {
			this.propostaService.save(this.propostaEditada);
			
			this.propostaEditada = this.propostaService.refresh(this.propostaEditada);
			
			this.negociacao.getPropostas().add(this.propostaEditada);
			
			this.negociacaoService.update(this.negociacao);
			
			msg = "Proposta atualizada e enviada com sucesso, aguarde a resposta do contratante";
			severity = FacesMessage.SEVERITY_INFO;
		}
		catch (ServiceException e) {
			msg = "Erro ao tentar atualizar proposta, atualize a página e tente novamente";
			severity = FacesMessage.SEVERITY_ERROR;

			e.printStackTrace();
		}

		facesContext.addMessage(null, new FacesMessage(severity, msg, ""));
		
		try {
			facesContext.getExternalContext().redirect(facesContext.getExternalContext().getApplicationContextPath() + "/assistente/negociacao.xhtml?id="+this.negociacao.getId());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void cancelarEdicao() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		try {
			facesContext.getExternalContext().redirect(facesContext.getExternalContext().getApplicationContextPath() + "/assistente/negociacao.xhtml?id="+this.negociacao.getId());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void aceitarProposta(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		
		String msg = "";
		Severity severity = null;
		
		try {
			
			this.negociacao.setStatus(ESTADO_NEGOCIACAO.CONCLUÍDA);
			
			Proposta ultimaProposta = this.negociacao.getPropostas().get(this.negociacao.getPropostas().size() - 1);
			
			Servico servico = new Servico(ultimaProposta.getPreco(), ultimaProposta.getTipoPagamento(), 
					this.negociacao.getAssistente().getCategoriaServico(), 
					this.negociacao.getAssistente(), this.negociacao.getContratante(), 
					ultimaProposta.getDataRealizacaoServico(), ultimaProposta.getEndereco());
			
			this.servicoService.save(servico);
			
			this.negociacaoService.update(this.negociacao);
			
			msg = "Proposta aceita com sucesso !";
			severity = FacesMessage.SEVERITY_INFO;
		}
		catch (ServiceException e) {
			msg = "Erro ao tentar aceitar proposta, atualize a página e tente novamente";
			severity = FacesMessage.SEVERITY_ERROR;

			e.printStackTrace();
		}

		facesContext.addMessage(null, new FacesMessage(severity, msg, ""));
		
		try {
			facesContext.getExternalContext().redirect(facesContext.getExternalContext().getApplicationContextPath() + "/assistente/index.xhtml");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void encerrarNegociacao(){
		
	}
	
	public Negociacao getNegociacao() {
		return negociacao;
	}

	public void setNegociacao(Negociacao negociacao) {
		this.negociacao = negociacao;
	}

	public Proposta getPropostaEditada() {
		return propostaEditada;
	}

	public void setPropostaEditada(Proposta propostaEditada) {
		this.propostaEditada = propostaEditada;
	}

	public boolean isRenderizarBotoes() {
		return renderizarBotoes;
	}

	public void setRenderizarBotoes(boolean renderizarBotoes) {
		this.renderizarBotoes = renderizarBotoes;
	}

}

