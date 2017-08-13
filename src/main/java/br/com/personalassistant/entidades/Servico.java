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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.personalassistant.enums.ESTADO_SERVICO;
import br.com.personalassistant.enums.TIPO_PAGAMENTO;
import br.com.personalassistant.util.DataAtual;

@Table(name="TB_SERVICO")
@Entity(name = "Servico")
public class Servico implements Serializable {

	private static final long serialVersionUID = 4364184463294200061L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ESTADO_SERVICO status;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataConclusaoNegociacao;
	
	@Column(nullable = false)
	private Double preco;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TIPO_PAGAMENTO tipoPagamento;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "categoriaServico_FK", referencedColumnName="id_PK"),
		@JoinColumn(name = "ultimaAtualizacaoCategoriaServico_FK", referencedColumnName="ultimaAtualizacao_PK")
	})
	//@JoinColumn(name="categoriaServico_FK")
	private CategoriaServico categoriaServico;
	
	@OneToOne
	@JoinColumn(name = "avaliacao_que_assistente_recebeu_FK")
	private AvaliacaoAssistente avaliacaoAssistenteRecebeu;
	
	@OneToOne
	@JoinColumn(name = "avaliacao_que_contratante_recebeu_FK")
	private AvaliacaoContratante avaliacaoContratanteRecebeu;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "assistente_FK", referencedColumnName="id_PK"),
		@JoinColumn(name = "ultimaAtualizacaoAssistente_FK", referencedColumnName="ultimaAtualizacao_PK")
	})
	//@JoinColumn(name="assistente_FK")
	private Assistente assistente;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "contratante_FK", referencedColumnName="id_PK"),
		@JoinColumn(name = "ultimaAtualizacaoContratante_FK", referencedColumnName="ultimaAtualizacao_PK")
	})
	//@JoinColumn(name="contratante_FK")
	private Contratante contratante;
	
	@Embedded
	@Column(nullable = false)
	private DataRealizacaoServico dataRealizacaoServico;
	
	@OneToOne
	@JoinColumns({
		@JoinColumn(name = "endereco_FK", referencedColumnName="id_PK"),
		@JoinColumn(name = "ultimaAtualizacaoEndereco_FK", referencedColumnName="ultimaAtualizacao_PK")
	})
	//@JoinColumn(name="endereco_FK")
	private Endereco endereco;
	
	public Servico(){
		super();
		
		this.status = ESTADO_SERVICO.EM_ANDAMENTO;
		this.dataConclusaoNegociacao = DataAtual.getDataAtual();
	}
	
	public Servico(Double preco, TIPO_PAGAMENTO tipoPagamento,
			CategoriaServico categoriaServico, Assistente assistente, Contratante contratante,
			DataRealizacaoServico dataRealizacaoServico, Endereco endereco) {
		super();
		this.preco = preco;
		this.tipoPagamento = tipoPagamento;
		this.categoriaServico = categoriaServico;
		this.assistente = assistente;
		this.contratante = contratante;
		this.dataRealizacaoServico = dataRealizacaoServico;
		this.status = ESTADO_SERVICO.EM_ANDAMENTO;
		this.dataConclusaoNegociacao = DataAtual.getDataAtual();
	}

	public ESTADO_SERVICO getStatus() {
		return status;
	}
	
	public void setStatus(ESTADO_SERVICO status) {
		this.status = status;
	}

	public Date getDataConclusaoNegociacao() {
		return dataConclusaoNegociacao;
	}

	public void setDataConclusaoNegociacao(Date dataConclusaoNegociacao) {
		this.dataConclusaoNegociacao = dataConclusaoNegociacao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public TIPO_PAGAMENTO getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TIPO_PAGAMENTO tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public CategoriaServico getCategoriaServico() {
		return categoriaServico;
	}

	public void setCategoriaServico(CategoriaServico categoriaServico) {
		this.categoriaServico = categoriaServico;
	}

	public AvaliacaoAssistente getAvaliacaoAssistenteRecebeu() {
		return avaliacaoAssistenteRecebeu;
	}

	public void setAvaliacaoAssistenteRecebeu(AvaliacaoAssistente avaliacaoAssistenteRecebeu) {
		this.avaliacaoAssistenteRecebeu = avaliacaoAssistenteRecebeu;
	}

	public AvaliacaoContratante getAvaliacaoContratanteRecebeu() {
		return avaliacaoContratanteRecebeu;
	}

	public void setAvaliacaoContratanteRecebeu(AvaliacaoContratante avaliacaoContratanteRecebeu) {
		this.avaliacaoContratanteRecebeu = avaliacaoContratanteRecebeu;
	}

	public Assistente getAssistente() {
		return assistente;
	}

	public void setAssistente(Assistente assistente) {
		this.assistente = assistente;
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public DataRealizacaoServico getDataRealizacaoServico() {
		return dataRealizacaoServico;
	}

	public void setDataRealizacaoServico(DataRealizacaoServico dataRealizacaoServico) {
		this.dataRealizacaoServico = dataRealizacaoServico;
	}

	public Long getId() {
		return id;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assistente == null) ? 0 : assistente.hashCode());
		result = prime * result + ((avaliacaoAssistenteRecebeu == null) ? 0 : avaliacaoAssistenteRecebeu.hashCode());
		result = prime * result + ((avaliacaoContratanteRecebeu == null) ? 0 : avaliacaoContratanteRecebeu.hashCode());
		result = prime * result + ((categoriaServico == null) ? 0 : categoriaServico.hashCode());
		result = prime * result + ((contratante == null) ? 0 : contratante.hashCode());
		result = prime * result + ((dataConclusaoNegociacao == null) ? 0 : dataConclusaoNegociacao.hashCode());
		result = prime * result + ((dataRealizacaoServico == null) ? 0 : dataRealizacaoServico.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tipoPagamento == null) ? 0 : tipoPagamento.hashCode());
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
		Servico other = (Servico) obj;
		if (assistente == null) {
			if (other.assistente != null)
				return false;
		} else if (!assistente.equals(other.assistente))
			return false;
		if (avaliacaoAssistenteRecebeu == null) {
			if (other.avaliacaoAssistenteRecebeu != null)
				return false;
		} else if (!avaliacaoAssistenteRecebeu.equals(other.avaliacaoAssistenteRecebeu))
			return false;
		if (avaliacaoContratanteRecebeu == null) {
			if (other.avaliacaoContratanteRecebeu != null)
				return false;
		} else if (!avaliacaoContratanteRecebeu.equals(other.avaliacaoContratanteRecebeu))
			return false;
		if (categoriaServico == null) {
			if (other.categoriaServico != null)
				return false;
		} else if (!categoriaServico.equals(other.categoriaServico))
			return false;
		if (contratante == null) {
			if (other.contratante != null)
				return false;
		} else if (!contratante.equals(other.contratante))
			return false;
		if (dataConclusaoNegociacao == null) {
			if (other.dataConclusaoNegociacao != null)
				return false;
		} else if (!dataConclusaoNegociacao.equals(other.dataConclusaoNegociacao))
			return false;
		if (dataRealizacaoServico == null) {
			if (other.dataRealizacaoServico != null)
				return false;
		} else if (!dataRealizacaoServico.equals(other.dataRealizacaoServico))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (status != other.status)
			return false;
		if (tipoPagamento != other.tipoPagamento)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Servico [id=" + id + ", status=" + status + ", dataConclusaoNegociacao=" + dataConclusaoNegociacao
				+ ", preco=" + preco + ", tipoPagamento=" + tipoPagamento + ", categoriaServico=" + categoriaServico
				+ ", avaliacaoAssistenteRecebeu=" + avaliacaoAssistenteRecebeu + ", avaliacaoContratanteRecebeu="
				+ avaliacaoContratanteRecebeu + ", assistente=" + assistente + ", contratante=" + contratante
				+ ", dataRealizacaoServico=" + dataRealizacaoServico + ", endereco=" + endereco + "]";
	}
	
}
