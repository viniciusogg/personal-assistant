package br.com.personalassistant.beans;

import java.io.Serializable;
import java.security.Principal;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


public abstract class AbstractBean implements Serializable{

	private static final long serialVersionUID = 1L;

	public boolean isUserInRole(String role) {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		return externalContext.isUserInRole(role);
	}
	
	public String getUserLogin() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal userPrincipal = externalContext.getUserPrincipal();
		
		if (userPrincipal == null) {
			return "";
		}
		
		return userPrincipal.getName();
	}
	
}
