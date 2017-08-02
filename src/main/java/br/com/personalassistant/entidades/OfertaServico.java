package br.com.personalassistant.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.com.personalassistant.enums.ESTADO_OFERTA;

@Entity(name = "OfertaServico")
@Table(name = "TB_OFERTA_SERVICO")
public class OfertaServico implements Serializable{

	private static final long serialVersionUID = 3150730779099906180L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(nullable = false)
	private Integer duracaoOferta;
	
	@Column(nullable = false)
	private Double precoHora;

	@Column(nullable = false)
	private Double precoFixo;
	
	@Column(nullable = false)
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ESTADO_OFERTA status;
	
	@ManyToOne
	private Endereco endereco; // unidirecional
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private CategoriaServico categoriaServico; // unidirecional
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Contratante contratante; // bidirecional
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ofertaServico")
	@Column(name="ofertaServico_FK")
	private List<Lance> lances; // bidirecional
	
	@Embedded
	@Column(nullable = false)
	private DataRealizacaoServico dataRealizacaoServico; // unidirecional
	
	public OfertaServico() {
		super();
	}

	public OfertaServico(Integer duracaoOferta, Double precoHora, Double precoFixo, String descricao, ESTADO_OFERTA status,
			CategoriaServico categoriaServico, Contratante contratante,
			DataRealizacaoServico dataRealizacaoServico) {
		super();
		this.duracaoOferta = duracaoOferta;
		this.precoHora = precoHora;
		this.precoFixo = precoFixo;
		this.descricao = descricao;
		this.status = status;
		this.categoriaServico = categoriaServico;
		this.contratante = contratante;
		this.dataRealizacaoServico = dataRealizacaoServico;
	}

	public Long getId() {
		return id;
	}

	public Integer getDuracaoOferta() {
		return duracaoOferta;
	}

	public void setDuracaoOferta(Integer duracaoOferta) {
		this.duracaoOferta = duracaoOferta;
	}

	public Double getPrecoHora() {
		return precoHora;
	}

	public void setPrecoHora(Double precoHora) {
		this.precoHora = precoHora;
	}
	
	public Double getPrecoFixo() {
		return precoFixo;
	}

	public void setPrecoFixo(Double precoFixo) {
		this.precoFixo = precoFixo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		result = prime * result + ((duracaoOferta == null) ? 0 : duracaoOferta.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lances == null) ? 0 : lances.hashCode());
		result = prime * result + ((precoFixo == null) ? 0 : precoFixo.hashCode());
		result = prime * result + ((precoHora == null) ? 0 : precoHora.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		if (duracaoOferta == null) {
			if (other.duracaoOferta != null)
				return false;
		} else if (!duracaoOferta.equals(other.duracaoOferta))
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
		if (lances == null) {
			if (other.lances != null)
				return false;
		} else if (!lances.equals(other.lances))
			return false;
		if (precoFixo == null) {
			if (other.precoFixo != null)
				return false;
		} else if (!precoFixo.equals(other.precoFixo))
			return false;
		if (precoHora == null) {
			if (other.precoHora != null)
				return false;
		} else if (!precoHora.equals(other.precoHora))
			return false;
		if (status != other.status)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OfertaServico [id=" + id + ", duracaoOferta=" + duracaoOferta + ", precoHora=" + precoHora
				+ ", precoFixo=" + precoFixo + ", descricao=" + descricao + ", status=" + status + ", endereco=" + endereco
				+ ", categoriaServico=" + categoriaServico + ", contratante=" + contratante + ", lances=" + lances
				+ ", dataRealizacaoServico=" + dataRealizacaoServico + "]";
	}

}
