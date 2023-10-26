package com.cursojunit5.ursojunit5.barriga.servico;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cursojunit5.cursojunit5.barriga.domain.Conta;
import com.cursojunit5.cursojunit5.barriga.domain.exceptions.ValidationException;

import static com.cursojunit5.cursojunit5.barriga.domain.builders.ContaBuilder.umaConta;

import java.util.Arrays;

import com.cursojunit5.cursojunit5.barriga.service.ContaService;
import com.cursojunit5.cursojunit5.barriga.service.external.ContaEvent;
import com.cursojunit5.cursojunit5.barriga.service.external.ContaEvent.EventType;
import com.cursojunit5.cursojunit5.barriga.service.repositories.ContaRepository;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {
    
    @InjectMocks private ContaService contaService;
    @Mock private ContaRepository contaRepository;
    @Mock private ContaEvent event;

    //Existe a anotação do mockito @Captor que serve para quando não temos controle, porém nos importamos com o valor. Casos de valores randômicos gerados pela aplicação.

    @Test
    public void deveSalvarComSucesso() throws Exception{
        Conta contaToSave = umaConta().comId(null).agora();
        Mockito.when(contaRepository.salvar(contaToSave))
            .thenReturn(umaConta().agora());
        
        Mockito.doNothing().when(event).dispatch(umaConta().agora(), EventType.CREATED);//faz o mock do event para que não faça nada quando chamado, mas verifica se foi chamado o evento. Serve para métodos void também

        Conta savedConta = contaService.salvar(contaToSave);
        Assertions.assertNotNull(savedConta.id());
    }

    @Test
    public void deveRejeitarContaRepetida(){
        Conta contaToSave = umaConta().comId(null).agora();

        Mockito.when(contaRepository.obterContasPorUsuario(contaToSave.usuario().id()))
            .thenReturn(Arrays.asList(umaConta().agora()));
      
        String mensagem = Assertions.assertThrows(ValidationException.class, () -> contaService.salvar(contaToSave)).getMessage();
        Assertions.assertEquals("Usuário já possui uma conta com este nome", mensagem);

    }

     @Test
    public void deveSalvarContaMesmoJaExistindoOutras(){
        Conta contaToSave = umaConta().comId(null).agora();

        Mockito.when(contaRepository.obterContasPorUsuario(contaToSave.usuario().id()))
            .thenReturn(Arrays.asList(umaConta().comNome("Outra conta").agora()));

        Mockito.when(contaRepository.salvar(contaToSave))
            .thenReturn(umaConta().agora());

        Conta savedConta = contaService.salvar(contaToSave);
        Assertions.assertNotNull(savedConta.id());

    }

    @Test
    public void naoDeveManterContaSemEvento() throws Exception{
        Conta contaToSave = umaConta().comId(null).agora();
        Conta contaSalva = umaConta().agora();
        Mockito.when(contaRepository.salvar(contaToSave))
            .thenReturn(contaSalva);
        
        Mockito.doThrow(new Exception("Falha catastrófica")).when(event).dispatch(contaSalva, EventType.CREATED);//faz o mock do event para que não faça nada quando chamado, mas verifica se foi chamado o evento. Serve para métodos void também

        String mensagem = Assertions.assertThrows(Exception.class, () -> contaService.salvar(contaToSave)).getMessage();
        Assertions.assertEquals("Falha na criação da conta, tente novamente", mensagem);

        Mockito.verify(contaRepository).delete(contaSalva);
    }



}
