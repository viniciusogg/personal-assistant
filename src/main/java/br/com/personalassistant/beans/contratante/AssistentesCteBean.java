package br.com.personalassistant.beans.contratante;

import java.util.ArrayList;
import java.util.Date;
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
import br.com.personalassistant.entidades.DataRealizacaoServico;
import br.com.personalassistant.entidades.Proposta;
import br.com.personalassistant.enums.ESTADO_NEGOCIACAO;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.AssistenteService;
import br.com.personalassistant.services.AvaliacaoAssistenteService;
import br.com.personalassistant.services.ContratanteService;
import br.com.personalassistant.services.PropostaService;

@ViewScoped
@Named
public class AssistentesCteBean extends AbstractBean {

	private static final long serialVersionUID = 3584566994926099104L;

	@Inject private AssistenteService assistenteService;
	@Inject private ContratanteService contratanteService;
	@Inject private AvaliacaoAssistenteService avaliacaoAssistenteService;
	@Inject private PropostaService propostaService;
	private Assistente assistente; 
	private Contratante contratante;
	private List<Assistente> assistentes;
	private Proposta proposta;
	private boolean precisaEndereco;
	
	public void preRenderView() {
		
		proposta = new Proposta();
		
		try {
			contratante = contratanteService.getContratanteByEmail(getEmailUsuarioLogado());
			
			DataRealizacaoServico dataRealizacaoServico = new DataRealizacaoServico();
			dataRealizacaoServico.setDataRealizacaoInicial(new Date());
			dataRealizacaoServico.setDataRealizacaoLimite(new Date());
			
			proposta.setContratante(contratante);
			proposta.setDataRealizacaoServico(dataRealizacaoServico);
			proposta.setStatus(ESTADO_NEGOCIACAO.EM_ANDAMENTO);
			
			assistentes = assistenteService.getAll();
			
			for(Assistente a: assistentes){
				a.setNivelExperiencia(calcularNivelExperiencia(a.getId()));
				a.setQuantServicosPrestados(calcularQuantServicosPrestados(a.getId()));
				a.setReputacao(calcularReputacao(a.getId()));
			}
		}
		catch (ServiceException | ObjetoNaoExisteException | NaoExistemObjetosException e) {
			e.printStackTrace();
		}
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

	public void enviarProposta(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		String msg = "";
		Severity severity = null;
		
		try {			
			proposta.setContratante(this.contratante);
			proposta.setAssistente(this.assistente);
			
			//this.contratante.getPropostas().add(proposta);
			
			//contratanteService.update(contratante);
			
			//assistenteService.update(assistente);
			
			propostaService.save(proposta);
			
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

	private int calcularReputacao(Long id){
		
		int indiceMaior = 0;
		
		try {
			ArrayList<Double> valores = avaliacaoAssistenteService.getAvaliacoesByIdAssistente(id);
			
			if(valores == null || valores.isEmpty()){
				return 0;
			}
			
			indiceMaior = -1;
			Double maior = valores.get(0);
			
			for(int i = 1; i < valores.size(); i++){
				if(valores.get(i) > maior){
					indiceMaior = i;
					maior = valores.get(i);
				}
				else if(valores.get(i).equals(maior) && valores.get(i) != 0){
					indiceMaior = Math.round((indiceMaior + i) / 2);
				}
			}
		}
		
		catch (ServiceException e) {
			e.printStackTrace();
		} 
		catch (ObjetoNaoExisteException e) {
			e.printStackTrace();
		}
		
		if(indiceMaior == -1){
			return 0;
		}
		
		return indiceMaior + 1;
	}
	
	private String calcularNivelExperiencia(Long id){
	
		Long experiencia = calcularQuantServicosPrestados(id);
		
		if(experiencia <= 50){
			return "INEXPERIENTE";
		}
		else if(experiencia > 50 && experiencia <= 100){
			return "POUCA EXPERIENCIA";
		}
		else if(experiencia > 100 && experiencia <= 200){
			return "EXPERIENTE";
		}
		else{
			return "MUITO EXPERIENTE";
		}
	}

	private Long calcularQuantServicosPrestados(Long id){
		
		Long experiencia = null;
		
		try {
			experiencia = assistenteService.getQuantidadeServicosById(id);
		} 
		catch (ServiceException e) {
			e.printStackTrace();
		}
		catch (ObjetoNaoExisteException e) {
			e.printStackTrace();
		}
		
		return experiencia;
	}
}
