package br.com.personalassistant.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class TemplateBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private boolean menuVisivel=true; 
	private String modo;
	
	public boolean getMenuVisivel(){
		return menuVisivel;
	}
	
	public void setMenuVisivel(boolean menuVisivel){
		this.menuVisivel = menuVisivel;
	}
	
	public String getModo(){
		return modo;
	}
	
	public void setModo(String modo){
		this.modo = modo;
	}
	
	public void acionarMenuLateral(){

		if(menuVisivel){
			menuVisivel=false;
			modo = "'hide'";
		}
		else{
			menuVisivel=true;
			modo = "'show'";
		}

	}
}
