package br.com.personalassistant.beans.contratante;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.CategoriaServico;
import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.entidades.DataRealizacaoServico;
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
	private OfertaServico ofertaServico;
	private boolean precisaEndereco;
	
	public void preRenderView(){
	
		try {
			Contratante contratante = contratanteService.getContratanteByEmail(getEmailUsuarioLogado());

			this.ofertaServico = new OfertaServico();
			this.ofertaServico.setDataRealizacaoServico(new DataRealizacaoServico());
			this.ofertaServico.setContratante(contratante);
			
			categoriasServicos = categoriaServicoService.getAll();
		} 
		catch (ServiceException | NaoExistemObjetosException | ObjetoNaoExisteException e) {
			e.printStackTrace();
		}
	}

	public void criarOfertaServico(){
		
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
	
	
}
