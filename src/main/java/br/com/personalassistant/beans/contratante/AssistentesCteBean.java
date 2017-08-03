package br.com.personalassistant.beans.contratante;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Assistente;
import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.entidades.Proposta;
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
	private List<Assistente> assistentes;
	private Proposta proposta;
	private boolean precisaEndereco;
	
	public void preRenderView() {
		
		proposta = new Proposta();
		
		try {
			String email = getEmailUsuarioLogado();
			
			Contratante contratante = contratanteService.getContratanteByEmail(email);
			
			proposta.setContratante(contratante);
			
			assistentes = assistenteService.getAllSemPropostasDoContratante(contratante.getId());
			
			for(Assistente a: assistentes){
				a.setNivelExperiencia(calcularNivelExperiencia(a.getId()));
				a.setQuantServicosPrestados(calcularQuantServicosPrestados(a.getId()));
				a.setReputacao(calcularReputacao(a.getId()));
			}
		}
		catch (ServiceException | ObjetoNaoExisteException e) {
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

	public void enviarProposta(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			proposta.getAssistente().getPropostas().add(proposta);
		
			propostaService.save(proposta);
			
		} 
		catch (ServiceException e) {
			e.printStackTrace();
		}
		
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
