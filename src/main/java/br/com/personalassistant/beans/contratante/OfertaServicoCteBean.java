package br.com.personalassistant.beans.contratante;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.Capacidade;
import br.com.personalassistant.entidades.CategoriaServico;
import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.entidades.OfertaServico;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ObjetoNaoExisteException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.CategoriaServicoService;
import br.com.personalassistant.services.ContratanteService;
import br.com.personalassistant.services.OfertaServicoService;

@ViewScoped
@Named
public class OfertaServicoCteBean extends AbstractBean {

	private static final long serialVersionUID = -8650273559758651189L;

	@Inject private ContratanteService contratanteService;
	@Inject private CategoriaServicoService categoriaServicoService;
	@Inject private OfertaServicoService ofertaServicoService;
	private List<CategoriaServico> categoriasServicos;
	private List<OfertaServico> ofertasServicos;
	private OfertaServico ofertaServico;
	private Contratante contratante;
	private boolean precisaEndereco;
	private List<String> nomesCapacidades = new ArrayList<String>();
	
	public void preRenderView(){
	
		try {
			this.contratante = contratanteService.getContratanteByEmail(getEmailUsuarioLogado());
			
			this.ofertasServicos = ofertaServicoService.getAllByIdContratante(this.contratante.getId());
			
			this.ofertaServico = new OfertaServico();
			this.ofertaServico.setContratante(this.contratante);
			
			categoriasServicos = categoriaServicoService.getAll();
		} 
		catch (ServiceException | NaoExistemObjetosException | ObjetoNaoExisteException e) {
			e.printStackTrace();
		}
	}

	public void criarOfertaServico(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		String msg = "";
		Severity severity = null;
		
		try{
			if(precisaEndereco){
				this.ofertaServico.setEndereco(this.contratante.getEndereco());
			}

			for(String nome: nomesCapacidades){
				this.ofertaServico.getCapacidades().add(new Capacidade(nome));
			}
			
			this.ofertaServicoService.save(this.ofertaServico);
			
			msg = "Oferta de serviço criada com sucesso";
			severity = FacesMessage.SEVERITY_INFO;
		}
		catch(ServiceException e){
			msg = "Erro ao tentar criar oferta de serviço, atualize a página e tente novamente";
			severity = FacesMessage.SEVERITY_ERROR;
		}
		
		context.addMessage(null, new FacesMessage(severity, msg, ""));;
	}
	
	public void verDetalhesOferta(Long id){
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.getExternalContext().redirect(facesContext.getExternalContext().getApplicationContextPath() + "/contratante/oferta.xhtml?id="+id);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<CategoriaServico> getCategoriasServicos() {
		return categoriasServicos;
	}

	public void setCategoriasServicos(List<CategoriaServico> categoriasServicos) {
		this.categoriasServicos = categoriasServicos;
	}

	public OfertaServico getOfertaServico() {
		return ofertaServico;
	}

	public void setOfertaServico(OfertaServico ofertaServico) {
		this.ofertaServico = ofertaServico;
	}

	public boolean isPrecisaEndereco() {
		return precisaEndereco;
	}

	public void setPrecisaEndereco(boolean precisaEndereco) {
		this.precisaEndereco = precisaEndereco;
	}

	public List<OfertaServico> getOfertasServicos() {
		return ofertasServicos;
	}

	public void setOfertasServicos(List<OfertaServico> ofertasServicos) {
		this.ofertasServicos = ofertasServicos;
	}

	public List<String> getNomesCapacidades() {
		return nomesCapacidades;
	}

	public void setNomesCapacidades(List<String> nomesCapacidades) {
		this.nomesCapacidades = nomesCapacidades;
	}
	
	
}
