package br.com.personalassistant.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.personalassistant.enums.ESTADO_LANCE;
import br.com.personalassistant.enums.TIPO_PAGAMENTO;
import br.com.personalassistant.util.DataAtual;

@Table(name = "TB_LANCE")
@Entity(name = "Lance")
public class Lance implements Serializable{

	private static final long serialVersionUID = -1668806308541842964L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(nullable = false)
	private Double valorLance;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCriacaoLance;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TIPO_PAGAMENTO tipoPagamento;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ESTADO_LANCE status;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "assistente_FK", referencedColumnName="id_PK"),
		@JoinColumn(name = "ultimaAtualizacaoAssistente_FK", referencedColumnName="ultimaAtualizacao_PK")
	})
	//@JoinColumn(name="assistente_FK", nullable=false)
	private Assistente assistente; // bidirecional
	
	@ManyToOne
	@JoinColumn(nullable = false, name="ofertaServico_FK")
	private OfertaServico ofertaServico; // bidirecional
	
	@Embedded
	@Column(nullable = false)
	private DataRealizacaoServico dataRealizacaoServico = new DataRealizacaoServico(); // unidirecional
	
	public Lance() {
		super();
		
		this.status = ESTADO_LANCE.EM_ESPERA; 
		this.dataCriacaoLance = DataAtual.getDataAtual();
	}

	public Lance(Double valorLance, TIPO_PAGAMENTO tipoPagamento, Assistente assistente, OfertaServico ofertaServico,
			DataRealizacaoServico dataRealizacaoServico) {
		super();
		this.valorLance = valorLance;
		this.tipoPagamento = tipoPagamento;
		this.assistente = assistente;
		this.ofertaServico = ofertaServico;
		this.dataRealizacaoServico = dataRealizacaoServico;
		this.status = ESTADO_LANCE.EM_ESPERA; 
		this.dataCriacaoLance = DataAtual.getDataAtual();
	}

	public Long getId() {
		return id;
	}

	public Double getValorLance() {
		return valorLance;
	}

	public void setValorLance(Double valorLance) {
		this.valorLance = valorLance;
	}

	public TIPO_PAGAMENTO getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TIPO_PAGAMENTO tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
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

	public OfertaServico getOfertaServico() {
		return ofertaServico;
	}

	public void setOfertaServico(OfertaServico ofertaServico) {
		this.ofertaServico = ofertaServico;
	}
	
	public DataRealizacaoServico getDataRealizacaoServico() {
		return dataRealizacaoServico;
	}

	public void setDataRealizacaoServico(DataRealizacaoServico dataRealizacaoServico) {
		this.dataRealizacaoServico = dataRealizacaoServico;
	}
	
	public Date getDataCriacaoLance() {
		return dataCriacaoLance;
	}

	public void setDataCriacaoLance(Date dataCriacaoLance) {
		this.dataCriacaoLance = dataCriacaoLance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assistente == null) ? 0 : assistente.hashCode());
		result = prime * result + ((dataRealizacaoServico == null) ? 0 : dataRealizacaoServico.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ofertaServico == null) ? 0 : ofertaServico.hashCode());
		result = prime * result + ((valorLance == null) ? 0 : valorLance.hashCode());
		result = prime * result + ((tipoPagamento == null) ? 0 : tipoPagamento.hashCode());
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
		if (ofertaServico == null) {
			if (other.ofertaServico != null)
				return false;
		} else if (!ofertaServico.equals(other.ofertaServico))
			return false;
		if (valorLance == null) {
			if (other.valorLance != null)
				return false;
		} else if (!valorLance.equals(other.valorLance))
			return false;
		if (tipoPagamento == null) {
			if (other.tipoPagamento != null)
				return false;
		} else if (!tipoPagamento.equals(other.tipoPagamento))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lance [id=" + id + ", valorLance=" + valorLance + ", tipoPagamento=" + tipoPagamento
				+ ", status=" + status + ", assistente=" + assistente + ", ofertaServico=" + ofertaServico
				+ ", dataRealizacaoServico=" + dataRealizacaoServico + "]";
	}

}
