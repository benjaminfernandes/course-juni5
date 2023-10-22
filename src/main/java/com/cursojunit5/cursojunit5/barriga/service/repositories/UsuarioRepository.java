package com.cursojunit5.cursojunit5.barriga.service.repositories;

import java.util.Optional;

import com.cursojunit5.cursojunit5.barriga.domain.Usuario;

public interface UsuarioRepository {

	Usuario salvar(Usuario usuario);
	Optional<Usuario> getUserByEmail(String email);
	
}
