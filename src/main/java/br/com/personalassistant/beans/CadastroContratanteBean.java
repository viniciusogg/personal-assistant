package br.com.personalassistant.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.personalassistant.dao.ContratanteDAO;
import br.com.personalassistant.entidades.Contratante;
import br.com.personalassistant.entidades.Endereco;
import br.com.personalassistant.excecoes.PersistenciaException;

@ManagedBean
@ViewScoped
public class CadastroContratanteBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Contratante contratante;
	private Endereco endereco;
	private ContratanteDAO contratanteDAO;
	
	public void preRenderView(){
		
		contratanteDAO = new ContratanteDAO();
		
		if(this.contratante == null){
			this.contratante = new Contratante();
		}
		
		if(this.endereco == null){
			this.endereco = new Endereco();
		}
	}
	
	public void salvarContratante(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			List<Endereco> enderecos = new ArrayList<Endereco>();
			enderecos.add(endereco);
			contratante.setEnderecos(enderecos);
			
			contratanteDAO.save(contratante);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro concluído com sucesso", ""));
		} 
		catch (PersistenciaException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro", ""));
			e.printStackTrace();
		}
	}

	public Contratante getContratante() {
		return contratante;
	}
	
	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
