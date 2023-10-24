package com.cursojunit5.ursojunit5.barriga.servico;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cursojunit5.cursojunit5.barriga.domain.Usuario;
import com.cursojunit5.cursojunit5.barriga.domain.builders.UsuarioBuilder;
import com.cursojunit5.cursojunit5.barriga.domain.exceptions.ValidationException;
import com.cursojunit5.cursojunit5.barriga.service.UsuarioService;
import com.cursojunit5.cursojunit5.barriga.service.repositories.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
	@Mock //Instancia o repositório como um mock
	private UsuarioRepository repository;
	@InjectMocks // Injeta os mocks criados dentro da classe service
	private UsuarioService service;

	/*
	* Com a anotação @ExtendWith(MockitoExtension.class) não é necessário ter o before each para que o mockito identifique onde fazer as injeções e criações dos mocks
	@BeforeEach
	public void setup() {
		//repository = Mockito.mock(UsuarioRepository.class); Forma sem as anotações para criar o mock
		//service = new UsuarioService(repository);
		MockitoAnnotations.openMocks(this);//PEga o escopo e realiza/cria todos os mocks e injeções requeridas
	}
*/
	/*
	@AfterEach
	public void tearDown() {
		Mockito.verifyNoMoreInteractions(repository);//Verifica se houve alguma outra interação no mock. Sempre identificar a necessidade desta solução
		//É uma forma de amarrar o mockito para ele verificar o que eu configurar 
	}
	*/

	@Test
	public void deveRetornarEmptyQuandoUsuarioInexistente() {
		Mockito.when(repository.getUserByEmail("mail@mail.com"))
			.thenReturn(Optional.empty());
		Optional<Usuario> user = service.getUserByEmail("mail@mail.com");
		Assertions.assertTrue(user.isEmpty());

		Mockito.verify(repository).getUserByEmail("mail@mail.com");
	}
	
	
	@Test
	public void deveRetornarUsuarioPorEmail() {
		Mockito.when(repository.getUserByEmail("mail@mail.com"))
			.thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()));
		Optional<Usuario> user = service.getUserByEmail("mail@mail.com");
		Assertions.assertTrue(user.isPresent());

		//Sempre avaliar se é necessário os casos abaixo
		Mockito.verify(repository, Mockito.times(1)).getUserByEmail("mail@mail.com");//Indica a quantidade de chamadas
		Mockito.verify(repository, Mockito.atLeast(1)).getUserByEmail("mail@mail.com");//No mínimo invocada uma vez
		Mockito.verify(repository, Mockito.atLeastOnce()).getUserByEmail("mail@mail.com");//Deve ser chamado somente uma vez
		Mockito.verify(repository, Mockito.never()).getUserByEmail("outromail@mail.com");//indica que nunca deve chamar este método


	}

	@Test
	public void deveSalvarUsuarioComSucesso() {
		Usuario usuerToSave = UsuarioBuilder.umUsuario().comId(null).agora();
		//Mockito.when(repository.getUserByEmail(usuerToSave.email()))
			//.thenReturn(Optional.empty());
		Mockito.when(repository.salvar(usuerToSave)).thenReturn(UsuarioBuilder.umUsuario().agora());	
		Usuario userSaved = service.salvar(usuerToSave);
		Assertions.assertNotNull(userSaved.id());
		
		Mockito.verify(repository).getUserByEmail(usuerToSave.email());
		Mockito.verify(repository).salvar(usuerToSave);
	}

	@Test
	public void deveRejeitarUsuarioExistente() {
		Usuario userToSave = UsuarioBuilder.umUsuario().comId(null).agora();
		Mockito.when(repository.getUserByEmail(userToSave.email()))
			.thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()));
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () -> service.salvar(userToSave));
		Assertions.assertTrue(ex.getMessage().endsWith("já cadastrado!"));

		Mockito.verify(repository, Mockito.never()).salvar(userToSave);
	}

}
