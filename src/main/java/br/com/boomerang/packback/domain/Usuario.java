package br.com.boomerang.packback.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = "senha", allowSetters = true)
public class Usuario {

    @Id
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    private Long id;
    
    private TipoUsuario tipo;

	@Column(unique=true)
    private String email;

    private String nome;
    private String cpf;

    @Transient
    private String senha;

    @Column(name = "razao_social")
    private String razaoSocial;

    private String cnpj;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnore
    private Collection<Endereco> enderecos;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Collection<Pontuacao> pontuacao = new ArrayList<>();

	@OneToOne
	@JsonIgnore
	private Login login;
    
    public Usuario() {
    	this("Indefinido", "Indefinido", null, null, null, null);
    }
    
    public Usuario(String email, String nome, String cpf, String senha, String razaoSocial, String cnpj) {
		super();
		this.email = email;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.enderecos = new ArrayList<>();
		
		if(cnpj != null && !cnpj.isEmpty()) {
			this.tipo = TipoUsuario.PRODUTOR;
		} else {
			this.tipo = TipoUsuario.CONSUMIDOR;
		}
	}

	@JsonIgnore
	public boolean isTipoConsumidor() {
    	return this.getTipo().equals(TipoUsuario.CONSUMIDOR);
	}

	@JsonIgnore
	public boolean isNotTipoConsumidor() {
		return !this.isTipoConsumidor();
	}

	public Double getPontos() {
    	return this.pontuacao.stream().mapToDouble(Pontuacao::getPontos).sum();
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Endereco> getEnderecos() {
		return List.copyOf(enderecos);
	}

	public void setEnderecos(Collection<Endereco> enderecos) {
		for(Endereco endereco: enderecos)
			adicionaEndereco(endereco);
	}

	public void adicionaEndereco(Endereco endereco) {
		enderecos.add(endereco);
		endereco.setUsuario(this);
	}

	public void removeEndereco(Endereco endereco) {
		enderecos.remove(endereco);
		endereco.setUsuario(null);
	}

	public List<Pontuacao> getPontuacao() {
		return List.copyOf(pontuacao);
	}

	public void setPontuacao(Collection<Pontuacao> pontuacao) {
    	for(var pontos: pontuacao)
			this.adicionaPontuacao(pontos);
	}

	public void adicionaPontuacao(Pontuacao pontuacao) {
		this.pontuacao.add(pontuacao);
		pontuacao.setUsuario(this);
	}

	public void removePontuacao(Pontuacao pontuacao) {
		this.pontuacao.remove(pontuacao);
		pontuacao.setUsuario(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enderecos == null) ? 0 : enderecos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Usuario other = (Usuario) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enderecos == null) {
			if (other.enderecos != null)
				return false;
		} else if (!enderecos.equals(other.enderecos))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"id=" + id +
				", tipo=" + tipo +
				", email='" + email + '\'' +
				", nome='" + nome + '\'' +
				", cpf='" + cpf + '\'' +
				", razaoSocial='" + razaoSocial + '\'' +
				", cnpj='" + cnpj + '\'' +
				", enderecos=" + enderecos +
				", pontuacao=" + pontuacao +
				'}';
	}
}
