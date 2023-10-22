package com.cursojunit5.ursojunit5.barriga.infra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.cursojunit5.cursojunit5.barriga.domain.Usuario;
import com.cursojunit5.cursojunit5.barriga.domain.builders.UsuarioBuilder;
import com.cursojunit5.cursojunit5.barriga.domain.exceptions.ValidationException;
import com.cursojunit5.cursojunit5.barriga.infra.UsuarioMemoryRepository;
import com.cursojunit5.cursojunit5.barriga.service.UsuarioService;

//PAra colocar a ordem da execução dos tests caso necessário
//Porém fere o princípio FIRST 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceComUserMemoryRepositoryTest {

	private static UsuarioService service = new UsuarioService(new UsuarioMemoryRepository());
	
	@Test
	@Order(1)
	public void deveSalvarUsuarioValido() {
		Usuario user = service.salvar(UsuarioBuilder.umUsuario().comId(null).agora());
		Assertions.assertNotNull(user.id());
		//Assertions.assertEquals(2, user.id());
	}
	
	@Test
	@Order(2)
	public void deveRejeitarUsuarioExistente() {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () -> 
			service.salvar(UsuarioBuilder.umUsuario().comId(null).agora()));
		Assertions.assertEquals("Usuário user@mail.com já cadastrado!", ex.getMessage());
	}
	
}
