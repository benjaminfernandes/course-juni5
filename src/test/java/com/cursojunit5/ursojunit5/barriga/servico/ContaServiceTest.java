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
import com.cursojunit5.cursojunit5.barriga.service.repositories.ContaRepository;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {
    
    @InjectMocks private ContaService contaService;
    @Mock private ContaRepository contaRepository;

    @Test
    public void deveSalvarComSucesso(){
        Conta contaToSave = umaConta().comId(null).agora();
        Mockito.when(contaRepository.salvar(contaToSave))
            .thenReturn(umaConta().agora());
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


}
