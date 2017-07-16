package br.com.personalassistant.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@ViewScoped
@Named
public class TemplateBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private boolean menuVisivel=true; 
	private String modo;
	private String cssMenuLateral = "painel mega-menu-visivel";
	
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
	
	public String getCssMenuLateral() {
		return cssMenuLateral;
	}

	public void setCssMenuLateral(String cssMenuLateral) {
		this.cssMenuLateral = cssMenuLateral;
	}

	public void acionarMenuLateral(){

		if(menuVisivel){
			menuVisivel=false;
			modo = "'hide'";
			cssMenuLateral = "painel mega-menu-oculto";
		}
		else{
			menuVisivel=true;
			modo = "'show'";
			cssMenuLateral = "painel mega-menu-visivel";
		}

	}
}
