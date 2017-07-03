package br.com.personalassistant.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

@ManagedBean
@ViewScoped
public class FiltroTabelaBean {
	
	public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Editado com sucesso", "Editado com sucesso");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição cancelada","Edição cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Valor atualizado com sucesso", "Valor anterior: " + oldValue + ", Novo valor:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
