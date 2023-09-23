package com.cursojunit5.cursojunit5.barriga.domain.builders;

import com.cursojunit5.cursojunit5.barriga.domain.Usuario;

public class UsuarioBuilder {

	private Integer id;
	private String nome;
	private String email;
	private String senha;
	
	private UsuarioBuilder() {
	}
	
	public static UsuarioBuilder umUsuario() {
		UsuarioBuilder builder = new UsuarioBuilder();
		iniciarlizarDadosPadroes(builder);
		return builder;
	}

	private static void iniciarlizarDadosPadroes(UsuarioBuilder builder) {
		builder.id = 1;
		builder.nome = "Usuario Valido";
		builder.email = "user@mail.com";
		builder.senha = "123456";
	}
	
	public UsuarioBuilder comId(int param) {
		id = param;
		return this;
	}
	
	public UsuarioBuilder comNome(String param) {
		nome = param;
		return this;
	}
	
	public UsuarioBuilder comEmail(String param) {
		email = param;
		return this;
	}
	
	public UsuarioBuilder comSenha(String param) {
		senha = param;
		return this;
	}
	
	public Usuario agora() {
		return new Usuario(id, nome, email, senha);
	}
	
}
