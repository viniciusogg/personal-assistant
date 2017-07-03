package br.com.personalassistant.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.personalassistant.enums.ESTADO_OFERTA;

@Entity(name = "OfertaServico")
@Table(name = "TB_OFERTA_SERVICO")
public class OfertaServico implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@Basic(optional = false)
	private int duracaoOferta;
	
	@Basic(optional = false)
	private double precoInicial;
	
	@Enumerated(EnumType.STRING)
	private ESTADO_OFERTA status;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Endereco endereco; // unidirecional
	
	@ManyToOne
	@JoinColumn(name = "categoria_servico_FK", nullable = false)
	private CategoriaServico categoriaServico; // unidirecional
	
	@ManyToOne
	@JoinColumn(name = "contratante_FK", nullable=false)
	private Contratante contratante; // bidirecional
	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "oferstaServico")
	private List<Lance> lances; // bidirecional
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "data_realizacao_servico_FK", nullable = false)
	private DataRealizacaoServico dataRealizacaoServico; // unidirecional
	
	public OfertaServico() {
		super();
	}
	
	public OfertaServico(int duracaoOferta, double precoInicial, Endereco endereco, CategoriaServico categoriaServico,
			Contratante contratante, DataRealizacaoServico dataRealizacaoServico) {
		super();
		this.duracaoOferta = duracaoOferta;
		this.precoInicial = precoInicial;
		this.endereco = endereco;
		this.categoriaServico = categoriaServico;
		this.contratante = contratante;
		this.dataRealizacaoServico = dataRealizacaoServico;
	}

	public long getId() {
		return id;
	}

	public int getDuracaoOferta() {
		return duracaoOferta;
	}

	public void setDuracaoOferta(int duracaoOferta) {
		this.duracaoOferta = duracaoOferta;
	}

	public double getPrecoInicial() {
		return precoInicial;
	}

	public void setPrecoInicial(double precoInicial) {
		this.precoInicial = precoInicial;
	}

	public ESTADO_OFERTA getStatus() {
		return status;
	}

	public void setStatus(ESTADO_OFERTA status) {
		this.status = status;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public CategoriaServico getCategoriaServico() {
		return categoriaServico;
	}

	public void setCategoriaServico(CategoriaServico categoriaServico) {
		this.categoriaServico = categoriaServico;
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public List<Lance> getLances() {
		return lances;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
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
		result = prime * result + ((categoriaServico == null) ? 0 : categoriaServico.hashCode());
		result = prime * result + ((contratante == null) ? 0 : contratante.hashCode());
		result = prime * result + ((dataRealizacaoServico == null) ? 0 : dataRealizacaoServico.hashCode());
		result = prime * result + duracaoOferta;
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lances == null) ? 0 : lances.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precoInicial);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		OfertaServico other = (OfertaServico) obj;
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
		if (dataRealizacaoServico == null) {
			if (other.dataRealizacaoServico != null)
				return false;
		} else if (!dataRealizacaoServico.equals(other.dataRealizacaoServico))
			return false;
		if (duracaoOferta != other.duracaoOferta)
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (id != other.id)
			return false;
		if (lances == null) {
			if (other.lances != null)
				return false;
		} else if (!lances.equals(other.lances))
			return false;
		if (Double.doubleToLongBits(precoInicial) != Double.doubleToLongBits(other.precoInicial))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OfertaServico [id=" + id + ", duracaoOferta=" + duracaoOferta + ", precoInicial=" + precoInicial
				+ ", status=" + status + ", endereco=" + endereco + ", categoriaServico=" + categoriaServico
				+ ", contratante=" + contratante + ", lances=" + lances + ", dataRealizacaoServico="
				+ dataRealizacaoServico + "]";
	}
	
}
