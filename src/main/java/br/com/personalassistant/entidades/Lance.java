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

	private static final long serialVersionUID = -1668806308541842964L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(nullable = false)
	private Double precoFixo;

	private Double precoHoraTrabalhada;
	
	@Enumerated(EnumType.STRING)
	private ESTADO_LANCE status;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Assistente assistente; // bidirecional
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private OfertaServico oferstaServico; // bidirecional
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "data_realizacao_servico_FK", nullable = false)
	private DataRealizacaoServico dataRealizacaoServico; // unidirecional
	
	public Lance() {
		super();
	}

	public Lance(Double precoFixo, Double precoHoraTrabalhada, Assistente assistente, OfertaServico oferstaServico,
			DataRealizacaoServico dataRealizacaoServico) {
		super();
		this.precoFixo = precoFixo;
		this.precoHoraTrabalhada = precoHoraTrabalhada;
		this.assistente = assistente;
		this.oferstaServico = oferstaServico;
		this.dataRealizacaoServico = dataRealizacaoServico;
	}

	public Long getId() {
		return id;
	}

	public Double getPrecoFixo() {
		return precoFixo;
	}

	public void setPrecoFixo(Double precoFixo) {
		this.precoFixo = precoFixo;
	}

	public Double getPrecoHoraTrabalhada() {
		return precoHoraTrabalhada;
	}

	public void setPrecoHoraTrabalhada(Double precoHoraTrabalhada) {
		this.precoHoraTrabalhada = precoHoraTrabalhada;
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
		result = prime * result + ((precoFixo == null) ? 0 : precoFixo.hashCode());
		result = prime * result + ((precoHoraTrabalhada == null) ? 0 : precoHoraTrabalhada.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (precoFixo == null) {
			if (other.precoFixo != null)
				return false;
		} else if (!precoFixo.equals(other.precoFixo))
			return false;
		if (precoHoraTrabalhada == null) {
			if (other.precoHoraTrabalhada != null)
				return false;
		} else if (!precoHoraTrabalhada.equals(other.precoHoraTrabalhada))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lance [id=" + id + ", precoFixo=" + precoFixo + ", precoHoraTrabalhada=" + precoHoraTrabalhada
				+ ", status=" + status + ", assistente=" + assistente + ", oferstaServico=" + oferstaServico
				+ ", dataRealizacaoServico=" + dataRealizacaoServico + "]";
	}

}
