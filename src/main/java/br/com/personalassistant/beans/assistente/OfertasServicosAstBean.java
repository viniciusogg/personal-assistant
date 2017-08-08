package br.com.personalassistant.beans.assistente;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.personalassistant.beans.AbstractBean;
import br.com.personalassistant.services.OfertaServicoService;

@Named
@ViewScoped
public class OfertasServicosAstBean extends AbstractBean {

	private static final long serialVersionUID = -3648717368496249778L;
	@Inject private OfertaServicoService ofertaServicoService;

	
	public void preRenderView(){
		
	}


	
}
