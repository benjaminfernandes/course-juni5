package com.cursojunit5.ursojunit5.barriga.domain;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.cursojunit5.cursojunit5.barriga.domain.Conta;
import com.cursojunit5.cursojunit5.barriga.domain.Usuario;
import com.cursojunit5.cursojunit5.barriga.domain.builders.ContaBuilder;
import com.cursojunit5.cursojunit5.barriga.domain.builders.UsuarioBuilder;
import com.cursojunit5.cursojunit5.barriga.domain.exceptions.ValidationException;

public class ContaTest {
	
	@Test
	@DisplayName("Deve criar conta válida")
	public void deveCriarContaValida() {
		//Criar uma conta
		Conta conta = ContaBuilder.umaConta().agora();
		//Assertivas em cima da conta
		Assertions.assertAll("Conta",
				() -> Assertions.assertEquals(1L, conta.id()),
				() -> Assertions.assertEquals("Conta Válida", conta.nome()),
				() -> Assertions.assertEquals(UsuarioBuilder.umUsuario().agora(), conta.usuario()));
	}
	
	@ParameterizedTest
	@MethodSource(value = "dataProvider")
	public void deveRejeitarContaInvalida(Long id, String nome, Usuario usuario, String mensagem) {
		String erroMessage = Assertions.assertThrows(ValidationException.class, () -> 
			ContaBuilder.umaConta().comId(id).comNome(nome).comUsuario(usuario).agora())
		.getMessage();
		Assertions.assertEquals(mensagem, erroMessage);
	}
	
	private static Stream<Arguments> dataProvider(){
		return Stream.of(
				Arguments.of(1L, null, UsuarioBuilder.umUsuario().agora(), "Nome é obrigatorio"),
				Arguments.of(1L, "Conta Válida", null, "Usuário é obrigatorio")
				);
	}
	
}
