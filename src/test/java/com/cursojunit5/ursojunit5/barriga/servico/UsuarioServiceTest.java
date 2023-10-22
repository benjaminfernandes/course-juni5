package com.cursojunit5.ursojunit5.barriga.servico;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.cursojunit5.cursojunit5.barriga.domain.Usuario;
import com.cursojunit5.cursojunit5.barriga.domain.builders.UsuarioBuilder;
import com.cursojunit5.cursojunit5.barriga.service.UsuarioService;
import com.cursojunit5.ursojunit5.barriga.infra.UsuarioDummyRepository;

public class UsuarioServiceTest {

	private UsuarioService service;
	
	@Test
	public void deveSalvarUsuarioComSucesso() {
		service = new UsuarioService(new UsuarioDummyRepository());
		Usuario user = UsuarioBuilder.umUsuario()
				.comEmail("outro@email.com")
				.comId(null).agora();
		Usuario saverUser = service.salvar(user);
		Assertions.assertNotNull(saverUser.id());
	}
	
}
