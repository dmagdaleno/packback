package br.com.boomerang.packback.domain.builder;

import br.com.boomerang.packback.domain.Endereco;
import br.com.boomerang.packback.domain.Usuario;

import java.sql.ShardingKeyBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class UsuarioBuilder {

	private Long id = null;
	private String nome = "Indefinido";
	private String email = "Indefinido";
	private String cpf = null;
	private String razaoSocial = null;
	private String cnpj = null;
	private Collection<Endereco> enderecos = new ArrayList<>();
	
	public UsuarioBuilder comId(Long id) {
		this.id = id;
		return this;
	}

	public UsuarioBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public UsuarioBuilder comEmail(String email) {
		this.email = email;
		return this;
	}
	
	public UsuarioBuilder comCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}
	
	public UsuarioBuilder comRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
		return this;
	}
	
	public UsuarioBuilder comCnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}
	
	public UsuarioBuilder comEnderecos(Collection<Endereco> enderecos) {
		this.enderecos = enderecos;
		return this;
	}

	public UsuarioBuilder adicionaEndereco(Endereco endereco) {
		this.enderecos.add(endereco);
		return this;
	}
	
	public Usuario constroi() {
		Usuario usuario = new Usuario(email, nome, cpf, razaoSocial, cnpj);

		if(id != null && id > 0)
			usuario.setId(id);

		usuario.setEnderecos(enderecos);

		return usuario;
	}
}
