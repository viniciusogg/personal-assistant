package br.com.personalassistant.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.personalassistant.enums.ESTADO_LANCE;

@Table(name = "TB_LANCE")
@Entity(name = "Lance")
public class Lance {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	@Column(nullable = false)
	private double valor;
	
	private ESTADO_LANCE status;
	
	@ManyToOne
	@JoinColumn(name = "assistente_FK")
	private Assistente assistente; // bidirecional
	
	private OfertaServico oferstaServico; // bidirecional
	private DataRealizacaoServico dataRealizacaoServico; // unidirecional

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
}
