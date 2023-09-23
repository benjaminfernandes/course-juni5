package com.cursojunit5.cursojunit5.barriga.domain;

import com.cursojunit5.cursojunit5.barriga.domain.exceptions.ValidationException;

public class Usuario {

	private Integer id;
	private String nome;
	private String email;
	private String senha;
	
	public Usuario(Integer id, String nome, String email, String senha) {
		if(nome == null) throw new ValidationException("Nome obrigatório");
		if(email == null) throw new ValidationException("Email obrigatório");
		if(senha == null) throw new ValidationException("Senha obrigatório");
		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public Integer id() {
		return id;
	}

	public String nome() {
		return nome;
	}

	public String email() {
		return email;
	}

	public String senha() {
		return senha;
	}
	
}
