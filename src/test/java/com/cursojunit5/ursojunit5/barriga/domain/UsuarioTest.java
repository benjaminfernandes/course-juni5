package com.cursojunit5.ursojunit5.barriga.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cursojunit5.cursojunit5.barriga.domain.Usuario;
import com.cursojunit5.cursojunit5.barriga.domain.builders.UsuarioBuilder;
import com.cursojunit5.cursojunit5.barriga.domain.exceptions.ValidationException;

@DisplayName("Domínio: Usuário")
public class UsuarioTest {

	@Test
	@DisplayName("Deve criar um usuário válido")
	public void deveCriarUsuarioValido() {
		var usuario = UsuarioBuilder.umUsuario().agora();
		Assertions.assertAll("Usuario",
				() -> assertEquals(1, usuario.id()),
				() -> assertEquals("Usuario Valido", usuario.nome()),
				() ->assertEquals("user@mail.com", usuario.email()),
				() ->assertEquals("123456", usuario.senha())
		);
		
		/*
		 * Neste cenário ele para a execução assim que encontrar uma falha
		 * e no exemplo acima ele captura todas as falhas encontradas
		 * assertEquals(1, usuario.id());
		assertEquals("Usuario", usuario.nome());
		assertEquals("usuario@email.com", usuario.email());
		assertEquals("123456", usuario.senha());*/
	}
	
	@Test
	public void deveRejeitarUsuarioSemNome() {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () -> {
			UsuarioBuilder.umUsuario().comNome(null).agora();
		});
		assertEquals("Nome obrigatório", ex.getMessage());
	}
	
}
