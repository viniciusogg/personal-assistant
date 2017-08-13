package br.com.personalassistant.beans.contratante;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.entidades.Servico;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.ContratanteService;
import br.com.personalassistant.services.ServicoService;
import br.com.personalassistant.services.UsuarioService;

@Named
@ViewScoped
public class IndexContratanteBean extends AbstractBean{

	private static final long serialVersionUID = -7936163110122575442L;

	@Inject private ServicoService servicoService;
	@Inject private UsuarioService contratanteService;
	private Contratante contratante;
	private List<Servico> servicos;
	
	public void preRenderView(){
		try {
			this.contratante = (Contratante) contratanteService.getUsuarioByEmail(getEmailUsuarioLogado());
			this.servicos = this.servicoService.getAllByIdContratante(this.contratante.getPk().getId());
		} 
		catch (ObjetoNaoExisteException | NaoExistemObjetosException e) {} 
		catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	public String getReputacao(){
		
		if(contratante.getReputacao() == 0){
			return "SEM REPUTAÇÃO";
		}
		else if(contratante.getReputacao() < 2){
			return "RUIM";
		}
		else if(contratante.getReputacao() == 3){
			return "REGULAR";
		}
		else if(contratante.getReputacao() == 4){
			return "BOA";			
		}
		
		return "MUITO BOA";		
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	
}
