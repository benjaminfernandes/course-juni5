package com.cursojunit5.ursojunit5.barriga.servico;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.cursojunit5.cursojunit5.barriga.domain.Usuario;
import com.cursojunit5.cursojunit5.barriga.domain.builders.UsuarioBuilder;
import com.cursojunit5.cursojunit5.barriga.service.UsuarioService;
import com.cursojunit5.cursojunit5.barriga.service.repositories.UsuarioRepository;

public class UsuarioServiceTest {

	private UsuarioService service;
	
	@Test
	public void deveRetornarEmptyQuandoUsuarioInexistente() {
		UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioService(repository);
		Mockito.when(repository.getUserByEmail("mail@mail.com"))
			.thenReturn(Optional.empty());
		Optional<Usuario> user = service.getUserByEmail("mail@mail.com");
		Assertions.assertTrue(user.isEmpty());
	}
	
	
	@Test
	public void deveRetornarUsuarioPorEmail() {
		UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioService(repository);
		Mockito.when(repository.getUserByEmail("mail@mail.com"))
			.thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()));
		Optional<Usuario> user = service.getUserByEmail("mail@mail.com");
		Assertions.assertFalse(user.isEmpty());
	}
	
}
