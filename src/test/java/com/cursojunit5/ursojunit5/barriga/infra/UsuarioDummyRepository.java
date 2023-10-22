package com.cursojunit5.ursojunit5.barriga.infra;

import java.util.Optional;

import com.cursojunit5.cursojunit5.barriga.domain.Usuario;
import com.cursojunit5.cursojunit5.barriga.domain.builders.UsuarioBuilder;
import com.cursojunit5.cursojunit5.barriga.service.repositories.UsuarioRepository;

public class UsuarioDummyRepository implements UsuarioRepository {

	@Override
	public Usuario salvar(Usuario usuario) {
		return UsuarioBuilder.umUsuario()
				.comNome(usuario.nome())
				.comEmail(usuario.email())
				.comSenha(usuario.senha())
			.agora();
	}

	@Override
	public Optional<Usuario> getUserByEmail(String email) {
		if("user@mail.com".equals(email))
			return Optional.of(UsuarioBuilder.umUsuario().comEmail(email).agora());
		return Optional.empty();
	}

}
