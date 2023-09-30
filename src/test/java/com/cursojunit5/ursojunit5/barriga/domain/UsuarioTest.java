package com.cursojunit5.ursojunit5.barriga.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import com.cursojunit5.cursojunit5.barriga.domain.builders.UsuarioBuilder;
import com.cursojunit5.cursojunit5.barriga.domain.exceptions.ValidationException;

@DisplayName("Domínio: Usuário")
public class UsuarioTest {

	@Test
	@DisplayName("Deve criar um usuário válido")
	public void deveCriarUsuarioValido() {
		var usuario = UsuarioBuilder.umUsuario().agora();
		Assertions.assertAll("Usuario",
				() -> assertEquals(0, usuario.id()),
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
	@DisplayName("Deve rejeitar um usuário sem nome")
	public void deveRejeitarUsuarioSemNome() {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () -> {
			UsuarioBuilder.umUsuario().comNome(null).agora();
		});
		assertEquals("Nome obrigatório", ex.getMessage());
	}
	
	@Test
	@DisplayName("Deve rejeitar um usuário sem email")
	public void deveRejeitarUsuarioSemEmail() {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () -> {
			UsuarioBuilder.umUsuario().comEmail(null).agora();
		});
		assertEquals("Email obrigatório", ex.getMessage());
	}
	
	@Test
	@DisplayName("Deve rejeitar um usuário sem senha")
	public void deveRejeitarUsuarioSemSenha() {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () -> {
			UsuarioBuilder.umUsuario().comSenha(null).agora();
		});
		assertEquals("Senha obrigatório", ex.getMessage());
	}
	
	
	//Com o Parameterized os testes "comuns" ou parecidos podem ser tratados em um único teste
	@DisplayName("Deve validar campos obrigatórios")
	@ParameterizedTest(name = "[{index}] - {4}")
	@CsvSource(value = {
			"1, NULL, user@mail.com, 123456, Nome obrigatório",
			"1, Nome usuário, NULL, 123456, Email obrigatório",
			"1, Nome usuário, user@mail.com, NULL, Senha obrigatório"
	}, nullValues = "NULL")
	public void deveValidarCamposObrigatorios(int id, String nome, String email, String senha, String mensagem) {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () -> {
			UsuarioBuilder.umUsuario()
				.comId(id)
				.comNome(nome)
				.comEmail(email)
				.comSenha(senha)
			.agora();
		});
		assertEquals(mensagem, ex.getMessage());
	}
	
	@DisplayName("Deve validar campos obrigatórios")
	@ParameterizedTest(name = "[{index}] - {4}")
	@CsvFileSource(files = "src\\test\\resources\\camposObrigatoriosUsuario.csv", 
	nullValues = "NULL", useHeadersInDisplayName = true)
	public void deveValidarCamposObrigatorios2(int id, String nome, String email, String senha, String mensagem) {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () -> {
			UsuarioBuilder.umUsuario()
				.comId(id)
				.comNome(nome)
				.comEmail(email)
				.comSenha(senha)
			.agora();
		});
		assertEquals(mensagem, ex.getMessage());
	}
	
	@DisplayName("Deve validar campos obrigatórios")
	@ParameterizedTest(name = "[{index}] - {4}")
	@CsvFileSource(files = "src\\test\\resources\\camposObrigatoriosUsuario.csv", 
	nullValues = "NULL", numLinesToSkip = 1)
	public void deveValidarCamposObrigatorios3(int id, String nome, String email, String senha, String mensagem) {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () -> {
			UsuarioBuilder.umUsuario()
				.comId(id)
				.comNome(nome)
				.comEmail(email)
				.comSenha(senha)
			.agora();
		});
		assertEquals(mensagem, ex.getMessage());
	}
	
}
