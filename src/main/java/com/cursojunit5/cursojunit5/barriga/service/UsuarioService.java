package com.cursojunit5.cursojunit5.barriga.service;

import com.cursojunit5.cursojunit5.barriga.domain.Usuario;
import com.cursojunit5.cursojunit5.barriga.domain.exceptions.ValidationException;
import com.cursojunit5.cursojunit5.barriga.service.repositories.UsuarioRepository;

public class UsuarioService {

	private UsuarioRepository usuarioRepo;
	
	public UsuarioService(UsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}

	public Usuario salvar(Usuario usuario) {
		this.usuarioRepo.getUserByEmail(usuario.email()).ifPresent(user -> {
			throw new ValidationException(String.format("Usuário %s já cadastrado!", usuario.email()));
		});
		return this.usuarioRepo.salvar(usuario);
	}
	
}
