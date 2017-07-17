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
	private Long id;
	
	@Column(nullable = false)
	private Double valor;
	
	@Enumerated(EnumType.STRING)
	private ESTADO_LANCE status;
	
	@ManyToOne
	@JoinColumn(name = "assistente_FK", nullable = false)
	private Assistente assistente; // bidirecional
	
	@ManyToOne
	@JoinColumn(name = "oferta_servico_FK", nullable = false)
	private OfertaServico oferstaServico; // bidirecional
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "data_realizacao_servico_FK", nullable = false)
	private DataRealizacaoServico dataRealizacaoServico; // unidirecional
	
	public Lance() {
		super();
	}

	public Lance(Double valor, Assistente assistente, OfertaServico oferstaServico,
			DataRealizacaoServico dataRealizacaoServico) {
		super();
		this.valor = valor;
		this.assistente = assistente;
		this.oferstaServico = oferstaServico;
		this.dataRealizacaoServico = dataRealizacaoServico;
	}

	public Long getId() {
		return id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((oferstaServico == null) ? 0 : oferstaServico.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (oferstaServico == null) {
			if (other.oferstaServico != null)
				return false;
		} else if (!oferstaServico.equals(other.oferstaServico))
			return false;
		if (status != other.status)
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lance [id=" + id + ", valor=" + valor + ", status=" + status + ", assistente=" + assistente
				+ ", oferstaServico=" + oferstaServico + ", dataRealizacaoServico=" + dataRealizacaoServico + "]";
	}

}
