package br.com.personalassistant.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "DataRealizacaoServico")
@Table(name = "TB_DATA_REALIZACAO_SERVICO")
public class DataRealizacaoServico implements Serializable{

	private static final long serialVersionUID = 2635410905509549734L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataRealizacaoInicial;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataRealizacaoLimite;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaRealizacaoServico;
	
	public DataRealizacaoServico() {
		super();
	}
	
	public DataRealizacaoServico(Date dataRealizacaoInicial, Date dataRealizacaoLimite, Date horaRealizacaoServico) {
		super();
		this.dataRealizacaoInicial = dataRealizacaoInicial;
		this.dataRealizacaoLimite = dataRealizacaoLimite;
		this.horaRealizacaoServico = horaRealizacaoServico;
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
	
	public Date getHoraRealizacaoServico() {
		return horaRealizacaoServico;
	}
	
	public void setHoraRealizacaoServico(Date horaRealizacaoServico) {
		this.horaRealizacaoServico = horaRealizacaoServico;
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataRealizacaoInicial == null) ? 0 : dataRealizacaoInicial.hashCode());
		result = prime * result + ((dataRealizacaoLimite == null) ? 0 : dataRealizacaoLimite.hashCode());
		result = prime * result + ((horaRealizacaoServico == null) ? 0 : horaRealizacaoServico.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (horaRealizacaoServico == null) {
			if (other.horaRealizacaoServico != null)
				return false;
		} else if (!horaRealizacaoServico.equals(other.horaRealizacaoServico))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataRealizacaoServico [id=" + id + ", dataRealizacaoInicial=" + dataRealizacaoInicial
				+ ", dataRealizacaoLimite=" + dataRealizacaoLimite + ", horaRealizacaoServico=" + horaRealizacaoServico
				+ "]";
	}
	
}
