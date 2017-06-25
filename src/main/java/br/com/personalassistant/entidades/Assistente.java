package br.com.personalassistant.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "TB_ASSISTENTE")
@Entity(name = "Assistente")
@DiscriminatorValue("Assistente")
public class Assistente extends Usuario{

	private static final long serialVersionUID = 1L;
	
	private int experiencia; // quantidade de assistencias prestadas

	@Column(nullable = false)
	private double precoFixo;
	
	@Column(nullable = false)
	private double precoHora;
	
	@JoinColumn(name = "endereco_FK")
	@OneToOne(cascade = CascadeType.ALL)
	@Column(nullable = false)
	private Endereco endereco; // unidirecional

	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	@Column(nullable = false)
	@JoinColumn(name = "assistente_FK")
	private List<CategoriaServico> categoriasServicos; // unidirecional

	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "assistente")
	private List<Lance> lances; // bidirecional
	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "assistente_avaliado_FK")
	private List<AvaliacaoAssistente> avaliacoesRecebidas; // unidirecional
	
	@OneToMany(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "assistente_avaliador_FK")
	private List<AvaliacaoContratante> avaliacoesFeitas; // unidirecional
	
	@OneToMany(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "assistente_FK")
	private List<Proposta> propostas; // unidirecional (são as negociações)

	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "assistente_FK")
	private List<Capacidade> capacidades; // unidirecional
	
	public Assistente() {
		super();
	}
	
	public Assistente(String nome, String email, String senha, String numTelefonico, 
					  double precoFixo, double precoHora, Endereco endereco,
					  List<CategoriaServico> categoriasServicos, 
					  List<Capacidade> capacidades) {
		super();
		this.setNome(nome);
		this.setEmail(email);
		this.setSenha(senha);
		this.setNumTelefonico(numTelefonico);
		this.precoFixo = precoFixo;
		this.precoHora = precoHora;
		this.endereco = endereco;
		this.categoriasServicos = categoriasServicos;
		this.capacidades = capacidades;
	}

	public double getPrecoFixo() {
		return precoFixo;
	}

	public void setPrecoFixo(double precoFixo) {
		this.precoFixo = precoFixo;
	}
	
	public double getPrecoHora() {
		return precoHora;
	}

	public void setPrecoHora(double precoHora) {
		this.precoHora = precoHora;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public List<CategoriaServico> getCategoriasServicos() {
		return categoriasServicos;
	}

	public void setCategoriasServicos(List<CategoriaServico> categoriasServicos) {
		this.categoriasServicos = categoriasServicos;
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

	public List<AvaliacaoAssistente> getAvaliacoesRecebidas() {
		return avaliacoesRecebidas;
	}

	public void setAvaliacoesRecebidas(List<AvaliacaoAssistente> avaliacoesRecebidas) {
		this.avaliacoesRecebidas = avaliacoesRecebidas;
	}

	public List<AvaliacaoContratante> getAvaliacoesFeitas() {
		return avaliacoesFeitas;
	}

	public void setAvaliacoesFeitas(List<AvaliacaoContratante> avaliacoesFeitas) {
		this.avaliacoesFeitas = avaliacoesFeitas;
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

	public void setPalavrasChave(List<Capacidade> capacidades){
		this.capacidades = capacidades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avaliacoesFeitas == null) ? 0 : avaliacoesFeitas.hashCode());
		result = prime * result + ((avaliacoesRecebidas == null) ? 0 : avaliacoesRecebidas.hashCode());
		result = prime * result + ((categoriasServicos == null) ? 0 : categoriasServicos.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + experiencia;
		result = prime * result + ((lances == null) ? 0 : lances.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precoFixo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(precoHora);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((propostas == null) ? 0 : propostas.hashCode());
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
		Assistente other = (Assistente) obj;
		if (avaliacoesFeitas == null) {
			if (other.avaliacoesFeitas != null)
				return false;
		} else if (!avaliacoesFeitas.equals(other.avaliacoesFeitas))
			return false;
		if (avaliacoesRecebidas == null) {
			if (other.avaliacoesRecebidas != null)
				return false;
		} else if (!avaliacoesRecebidas.equals(other.avaliacoesRecebidas))
			return false;
		if (categoriasServicos == null) {
			if (other.categoriasServicos != null)
				return false;
		} else if (!categoriasServicos.equals(other.categoriasServicos))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (experiencia != other.experiencia)
			return false;
		if (lances == null) {
			if (other.lances != null)
				return false;
		} else if (!lances.equals(other.lances))
			return false;
		if (Double.doubleToLongBits(precoFixo) != Double.doubleToLongBits(other.precoFixo))
			return false;
		if (Double.doubleToLongBits(precoHora) != Double.doubleToLongBits(other.precoHora))
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
		return "Assistente [experiencia=" + experiencia + ", precoFixo=" + precoFixo + ", precoHora=" + precoHora
				+ ", endereco=" + endereco + ", categoriasServicos=" + categoriasServicos + ", lances=" + lances
				+ ", avaliacoesRecebidas=" + avaliacoesRecebidas + ", avaliacoesFeitas=" + avaliacoesFeitas
				+ ", propostas=" + propostas + ", getId()=" + getId() + ", getNome()=" + getNome() + ", getEmail()="
				+ getEmail() + ", getSenha()=" + getSenha() + ", getNumTelefonico()=" + getNumTelefonico() + "]";
	}

}
