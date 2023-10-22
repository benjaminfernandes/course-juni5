package com.cursojunit5.cursojunit5.barriga.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cursojunit5.cursojunit5.barriga.domain.Usuario;
import com.cursojunit5.cursojunit5.barriga.service.repositories.UsuarioRepository;

public class UsuarioMemoryRepository implements UsuarioRepository {

	private List<Usuario> users;
	private Integer currentId;
	
	public UsuarioMemoryRepository() {
		this.currentId = 0;
		this.users = new ArrayList<>();
		salvar(new Usuario(null, "Usuario 01", "usuario01@mail.com", "123456"));
	}
	
	@Override
	public Usuario salvar(Usuario usuario) {
		Usuario newUser = new Usuario(nextId(), usuario.nome(), usuario.email(), usuario.senha());
		this.users.add(newUser);
		return newUser;
	}

	@Override
	public Optional<Usuario> getUserByEmail(String email) {
		return this.users.stream().filter(user -> 
			user.email().equalsIgnoreCase(email)).findFirst();
	}
	
	private Integer nextId() {
		return ++this.currentId;
	}
	
	public void printUsers() {
		System.out.println(this.users);
	}
	
}
