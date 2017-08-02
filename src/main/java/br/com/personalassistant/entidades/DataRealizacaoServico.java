package br.com.personalassistant.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class DataRealizacaoServico implements Serializable{

	private static final long serialVersionUID = 2635410905509549734L;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataRealizacaoInicial;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataRealizacaoLimite;
	
	@Temporal(TemporalType.TIME)
	private Date horaRealizacaoServicoInicial;
	
	@Temporal(TemporalType.TIME)
	private Date horaRealizacaoServicoFinal;
	
	public DataRealizacaoServico() {
		super();
	}

	public Date getDataRealizacaoInicial() {
		return dataRealizacaoInicial;
	}

	public void setDataRealizacaoInicial(Date dataRealizacaoInicial) {
		this.dataRealizacaoInicial = dataRealizacaoInicial;
	}

	public Date getDataRealizacaoLimite() {
		return dataRealizacaoLimite;
	}

	public void setDataRealizacaoLimite(Date dataRealizacaoLimite) {
		this.dataRealizacaoLimite = dataRealizacaoLimite;
	}

	public Date getHoraRealizacaoServicoInicial() {
		return horaRealizacaoServicoInicial;
	}

	public void setHoraRealizacaoServicoInicial(Date horaRealizacaoServicoInicial) {
		this.horaRealizacaoServicoInicial = horaRealizacaoServicoInicial;
	}

	public Date getHoraRealizacaoServicoFinal() {
		return horaRealizacaoServicoFinal;
	}

	public void setHoraRealizacaoServicoFinal(Date horaRealizacaoServicoFinal) {
		this.horaRealizacaoServicoFinal = horaRealizacaoServicoFinal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataRealizacaoInicial == null) ? 0 : dataRealizacaoInicial.hashCode());
		result = prime * result + ((dataRealizacaoLimite == null) ? 0 : dataRealizacaoLimite.hashCode());
		result = prime * result + ((horaRealizacaoServicoFinal == null) ? 0 : horaRealizacaoServicoFinal.hashCode());
		result = prime * result
				+ ((horaRealizacaoServicoInicial == null) ? 0 : horaRealizacaoServicoInicial.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataRealizacaoServico other = (DataRealizacaoServico) obj;
		if (dataRealizacaoInicial == null) {
			if (other.dataRealizacaoInicial != null)
				return false;
		} else if (!dataRealizacaoInicial.equals(other.dataRealizacaoInicial))
			return false;
		if (dataRealizacaoLimite == null) {
			if (other.dataRealizacaoLimite != null)
				return false;
		} else if (!dataRealizacaoLimite.equals(other.dataRealizacaoLimite))
			return false;
		if (horaRealizacaoServicoFinal == null) {
			if (other.horaRealizacaoServicoFinal != null)
				return false;
		} else if (!horaRealizacaoServicoFinal.equals(other.horaRealizacaoServicoFinal))
			return false;
		if (horaRealizacaoServicoInicial == null) {
			if (other.horaRealizacaoServicoInicial != null)
				return false;
		} else if (!horaRealizacaoServicoInicial.equals(other.horaRealizacaoServicoInicial))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataRealizacaoServico [dataRealizacaoInicial=" + dataRealizacaoInicial + ", dataRealizacaoLimite="
				+ dataRealizacaoLimite + ", horaRealizacaoServicoInicial=" + horaRealizacaoServicoInicial
				+ ", horaRealizacaoServicoFinal=" + horaRealizacaoServicoFinal + "]";
	}
	
}
