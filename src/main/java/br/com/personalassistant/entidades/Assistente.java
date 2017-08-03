package br.com.personalassistant.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Table(name = "TB_ASSISTENTE")
@Entity(name = "Assistente")
@DiscriminatorValue("assist")
public class Assistente extends Usuario{
	
	private static final long serialVersionUID = 5159659883981518233L;
		
	@Column(nullable = false)
	private Double precoFixo;
	
	@Column(nullable = false)
	private Double precoHora;
	
	@Transient
	private Long quantServicosPrestados;
	
	@Transient
	private String nivelExperiencia;
	
	@Transient
	private int reputacao;
	
	@JoinColumn(name = "endereco_FK", nullable = false)
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco; // unidirecional

	@OneToOne
	@JoinColumn(name = "categoriaServico_FK", nullable = false)
	private CategoriaServico categoriaServico; // unidirecional

	@Column(name="assistente_FK")
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "assistente")
	private List<Lance> lances = new ArrayList<Lance>(); // bidirecional
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="assistente")
	@JoinColumn(name = "assistente_FK")
	private List<Proposta> propostas = new ArrayList<Proposta>(); // bidirecional (são as negociações)

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "assistente_FK")
	private List<Capacidade> capacidades; // unidirecional
	
	public Assistente() {
		super();
	}

	public Assistente(Double precoFixo, Double precoHora, Endereco endereco, CategoriaServico categoriaServico,
			List<Capacidade> capacidades) {
		super();
		this.precoFixo = precoFixo;
		this.precoHora = precoHora;
		this.endereco = endereco;
		this.categoriaServico = categoriaServico;
		this.capacidades = capacidades;
	}

	public Double getPrecoFixo() {
		return precoFixo;
	}

	public void setPrecoFixo(Double precoFixo) {
		this.precoFixo = precoFixo;
	}
	
	public Double getPrecoHora() {
		return precoHora;
	}

	public void setPrecoHora(Double precoHora) {
		this.precoHora = precoHora;
	}

	public CategoriaServico getCategoriaServico() {
		return categoriaServico;
	}

	public void setCategoriaServico(CategoriaServico categoriaServico) {
		this.categoriaServico = categoriaServico;
	}

	public List<Lance> getLances() {
		return lances;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Proposta> getPropostas() {
		return propostas;
	}

	public void setPropostas(List<Proposta> propostas) {
		this.propostas = propostas;
	}
	
	public List<Capacidade> getCapacidades(){
		return capacidades;
	}

	public void setCapacidades(List<Capacidade> capacidades){
		this.capacidades = capacidades;
	}
	
	public Long getQuantServicosPrestados() {
		return quantServicosPrestados;
	}

	public void setQuantServicosPrestados(Long quantServicosPrestados) {
		this.quantServicosPrestados = quantServicosPrestados;
	}

	public String getNivelExperiencia() {
		return nivelExperiencia;
	}

	public void setNivelExperiencia(String nivelExperiencia) {
		this.nivelExperiencia = nivelExperiencia;
	}

	public int getReputacao() {
		return reputacao;
	}

	public void setReputacao(int reputacao) {
		this.reputacao = reputacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((capacidades == null) ? 0 : capacidades.hashCode());
		result = prime * result + ((categoriaServico == null) ? 0 : categoriaServico.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((lances == null) ? 0 : lances.hashCode());
		result = prime * result + ((precoFixo == null) ? 0 : precoFixo.hashCode());
		result = prime * result + ((precoHora == null) ? 0 : precoHora.hashCode());
		result = prime * result + ((propostas == null) ? 0 : propostas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assistente other = (Assistente) obj;
		if (capacidades == null) {
			if (other.capacidades != null)
				return false;
		} else if (!capacidades.equals(other.capacidades))
			return false;
		if (categoriaServico == null) {
			if (other.categoriaServico != null)
				return false;
		} else if (!categoriaServico.equals(other.categoriaServico))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
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
		if (propostas == null) {
			if (other.propostas != null)
				return false;
		} else if (!propostas.equals(other.propostas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Assistente [quantServicos=" + getQuantServicosPrestados() + ", precoFixo=" + precoFixo
				+ ", precoHora=" + precoHora + ", endereco=" + endereco + ", categoriaServico=" + categoriaServico
				+ ", lances=" + lances + ", propostas=" + propostas + ", capacidades=" + capacidades + ", getId()="
				+ getId() + ", getNome()=" + getNome() + ", getEmail()=" + getEmail() + ", getSenha()=" + getSenha()
				+ ", getNumTelefonico()=" + getNumTelefonico() + ", getTipoUsuario()=" + getTipoUsuario() + "]";
	}
	
}
