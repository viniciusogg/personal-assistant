package br.com.personalassistant.beans.assistente;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.entidades.Servico;
import br.com.personalassistant.enums.ESTADO_SERVICO;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AssistenteService;
import br.com.personalassistant.services.ServicoService;

@Named
@ViewScoped
public class IndexAssistenteBean extends AbstractBean{

	private static final long serialVersionUID = 1328929115686436730L;
	
	@Inject private ServicoService servicoService;
	@Inject private AssistenteService assistenteService;
	private Assistente assistente;
	private List<Servico> servicos;
	
	public void preRenderView(){
		
		try {
			this.assistente = assistenteService.getAssistenteByEmail(getEmailUsuarioLogado());
			this.servicos = this.servicoService.getAllByIdAssistente(this.assistente.getId());
		} 
		catch (ObjetoNaoExisteException | NaoExistemObjetosException e) {} 
		catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getNivelExperiencia(){
		
		if(this.assistente.getQuantServicosPrestados() < 500){
			return "SEM EXPERIÊNCIA";
		}
		else if(this.assistente.getQuantServicosPrestados() > 500 && this.assistente.getQuantServicosPrestados() < 1500){
			return "POUCO EXPERIENTE";
		}
		else if(this.assistente.getQuantServicosPrestados() > 1500 && this.assistente.getQuantServicosPrestados() < 3000){
			return "EXPERIENTE";
		}
		
		return "MUITO EXPERIENTE";
	}

	public String getReputacao(){
		
		if(assistente.getReputacao() == 0){
			return "SEM REPUTAÇÃO";
		}
		else if(assistente.getReputacao() < 2){
			return "RUIM";
		}
		else if(assistente.getReputacao() == 3){
			return "REGULAR";
		}
		else if(assistente.getReputacao() == 4){
			return "BOA";			
		}
		
		return "MUITO BOA";		
	}
	
	public void gerenciarServico(Long id, boolean concluiu){
		System.out.println("ENTROU");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().getFlash().setKeepMessages(true);

		String msg = "";
		Severity severity = FacesMessage.SEVERITY_INFO;

		try {
			Servico servico = this.servicoService.getById(id);
			
			if(concluiu){
				servico.setStatus(ESTADO_SERVICO.CONCLUIDO);
				msg = "Concluiu o serviço com sucesso";				
			}
			else{
				servico.setStatus(ESTADO_SERVICO.CANCELADO);
				msg = "Desistiu do serviço com sucesso";
			}
			
			servicoService.update(servico);
		}
		catch (ServiceException | ObjetoNaoExisteException e) {
			e.printStackTrace();
			msg = e.getMessage();
			severity = FacesMessage.SEVERITY_ERROR;
		}
		
		facesContext.addMessage(null, new FacesMessage(severity, msg, ""));
		
		try {
			facesContext.getExternalContext().redirect(facesContext.getExternalContext().getApplicationContextPath() + 
					"/assistente/index.xhtml");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Assistente getAssistente() {
		return assistente;
	}

	public void setAssistente(Assistente assistente) {
		this.assistente = assistente;
	}
	
	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

}
