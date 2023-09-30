package com.cursojunit5.cursojunit5.barriga.domain.builders;

import com.cursojunit5.cursojunit5.barriga.domain.Usuario;

public class UsuarioBuilder {
	private Integer id;
	private String nome;
	private String email;
	private String senha;

	private UsuarioBuilder(){}

	public static UsuarioBuilder umUsuario() {
		UsuarioBuilder builder = new UsuarioBuilder();
		inicializarDadosPadroes(builder);
		return builder;
	}

	private static void inicializarDadosPadroes(UsuarioBuilder builder) {
		builder.id = 0;
		builder.nome = "Usuario Valido";
		builder.email = "user@mail.com";
		builder.senha = "123456";
	}

	public UsuarioBuilder comId(Integer id) {
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

	public UsuarioBuilder comSenha(String senha) {
		this.senha = senha;
		return this;
	}

	public Usuario agora() {
		return new Usuario(id, nome, email, senha);
	}
}

