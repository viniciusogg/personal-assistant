package br.com.personalassistant.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.personalassistant.enums.ESTADO_LANCE;

@Table(name = "TB_LANCE")
@Entity(name = "Lance")
public class Lance implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	@Column(nullable = false)
	private double valor;
	
	@Enumerated(EnumType.STRING)
	private ESTADO_LANCE status;
	
	@ManyToOne
	@Column(nullable = false)
	@JoinColumn(name = "assistente_FK")
	private Assistente assistente; // bidirecional
	
	@ManyToOne
	@Column(nullable = false)
	@JoinColumn(name = "oferta_servico_FK")
	private OfertaServico oferstaServico; // bidirecional
	
	@Column(nullable = false)
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "data_realizacao_servico_FK")
	private DataRealizacaoServico dataRealizacaoServico; // unidirecional
	
	public Lance() {
		super();
	}

	public Lance(double valor, Assistente assistente, OfertaServico oferstaServico,
			DataRealizacaoServico dataRealizacaoServico) {
		super();
		this.valor = valor;
		this.assistente = assistente;
		this.oferstaServico = oferstaServico;
		this.dataRealizacaoServico = dataRealizacaoServico;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public ESTADO_LANCE getStatus() {
		return status;
	}

	public void setStatus(ESTADO_LANCE status) {
		this.status = status;
	}

	public Assistente getAssistente() {
		return assistente;
	}

	public void setAssistente(Assistente assistente) {
		this.assistente = assistente;
	}

	public OfertaServico getOferstaServico() {
		return oferstaServico;
	}

	public void setOferstaServico(OfertaServico oferstaServico) {
		this.oferstaServico = oferstaServico;
	}
	
	public DataRealizacaoServico getDataRealizacaoServico() {
		return dataRealizacaoServico;
	}

	public void setDataRealizacaoServico(DataRealizacaoServico dataRealizacaoServico) {
		this.dataRealizacaoServico = dataRealizacaoServico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assistente == null) ? 0 : assistente.hashCode());
		result = prime * result + ((dataRealizacaoServico == null) ? 0 : dataRealizacaoServico.hashCode());
		result = prime * result + ((oferstaServico == null) ? 0 : oferstaServico.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Lance other = (Lance) obj;
		if (assistente == null) {
			if (other.assistente != null)
				return false;
		} else if (!assistente.equals(other.assistente))
			return false;
		if (dataRealizacaoServico == null) {
			if (other.dataRealizacaoServico != null)
				return false;
		} else if (!dataRealizacaoServico.equals(other.dataRealizacaoServico))
			return false;
		if (oferstaServico == null) {
			if (other.oferstaServico != null)
				return false;
		} else if (!oferstaServico.equals(other.oferstaServico))
			return false;
		if (status != other.status)
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lance [id=" + id + ", valor=" + valor + ", status=" + status + ", assistente=" + assistente
				+ ", oferstaServico=" + oferstaServico + ", dataRealizacaoServico=" + dataRealizacaoServico + "]";
	}

}
