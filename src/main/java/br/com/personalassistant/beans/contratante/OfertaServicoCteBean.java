package br.com.personalassistant.beans.contratante;

import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.entidades.CategoriaServico;
import br.com.personalassistant.entidades.DataRealizacaoServico;
import br.com.personalassistant.entidades.OfertaServico;
import br.com.personalassistant.enums.ESTADO_OFERTA;
import br.com.personalassistant.excecoes.NaoExistemObjetosException;
import br.com.personalassistant.excecoes.ServiceException;
import br.com.personalassistant.services.CategoriaServicoService;

@ViewScoped
@Named
public class OfertaServicoCteBean extends AbstractBean {

	private static final long serialVersionUID = -8650273559758651189L;

	@Inject private CategoriaServicoService categoriaServicoService;
	private List<CategoriaServico> categoriasServicos;
	private OfertaServico ofertaServico;
	
	public void preRenderView(){
		
		DataRealizacaoServico dataRealizacaoServico = new DataRealizacaoServico();
		
		dataRealizacaoServico.setDataRealizacaoInicial(new Date());
		dataRealizacaoServico.setDataRealizacaoLimite(new Date());
		
		ofertaServico = new OfertaServico();
		ofertaServico.setDataRealizacaoServico(dataRealizacaoServico);
		ofertaServico.setStatus(ESTADO_OFERTA.EM_ESPERA);
		
		try {
			categoriasServicos = categoriaServicoService.getAll();
		} 
		catch (ServiceException | NaoExistemObjetosException e) {
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
	
	
}
