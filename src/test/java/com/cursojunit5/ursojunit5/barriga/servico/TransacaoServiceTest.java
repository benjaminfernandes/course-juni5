package com.cursojunit5.ursojunit5.barriga.servico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cursojunit5.cursojunit5.barriga.domain.Conta;
import com.cursojunit5.cursojunit5.barriga.domain.Transacao;
import com.cursojunit5.cursojunit5.barriga.domain.builders.ContaBuilder;
import com.cursojunit5.cursojunit5.barriga.domain.builders.TransacaoBuilder;
import com.cursojunit5.cursojunit5.barriga.domain.exceptions.ValidationException;
import com.cursojunit5.cursojunit5.barriga.service.TransacaoService;
import com.cursojunit5.cursojunit5.barriga.service.repositories.TransacaoDao;

//@EnabledIf(value = "isHoraValida")
@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
    
    @InjectMocks private TransacaoService service;
    @Mock private TransacaoDao dao;

   /* @BeforeEach
    public void checkTime() {
          Assumptions.assumeTrue(LocalDateTime.now().getHour() < 14);// testando assumptions - para ignorar certas execuções de acordo com o argumento se true ou false
    }
    */

    @Test
    public void deveSalvarTransacaoValida() {
              Transacao transacaoParaSalvar = TransacaoBuilder.umTransacao().comId(null).agora();
        Transacao transacaoPersistida = TransacaoBuilder.umTransacao().comId(null).agora();
        Mockito.when(dao.salvar(transacaoParaSalvar)).thenReturn(transacaoPersistida);
        Transacao transacaoSalva = service.salvar(transacaoParaSalvar);
        Assertions.assertEquals(transacaoPersistida, transacaoSalva);
    }

    @ParameterizedTest(name = "{6}")
    @MethodSource(value = "cenariosObrigatorios")
    public void deveValidarCamposObrigatoriosAoSalvar(Long id, String descricao, Double valor, LocalDate data, 
        Conta conta, Boolean status, String mensagem) {

       String message = Assertions.assertThrows(ValidationException.class, () -> {
            Transacao transacao = TransacaoBuilder.umTransacao().comId(id).comDescricao(descricao).comValor(valor)
                    .comData(data).comConta(conta).comStatus(status).agora();
            this.service.salvar(transacao);
        }).getMessage();

        Assertions.assertEquals(mensagem, message);
    }

    static Stream<Arguments> cenariosObrigatorios() {
        return Stream.of(
            Arguments.of(1L, null, 10.0, LocalDate.now(), ContaBuilder.umaConta().agora(), true, "Descrição inexistente"),
            Arguments.of(1L, "Descrição", null, LocalDate.now(), ContaBuilder.umaConta().agora(), true, "Valor inexistente"),
            Arguments.of(1L, "Descrição", 10.0, null, ContaBuilder.umaConta().agora(), true, "Data inexistente"),
            Arguments.of(1L, "Descrição", 10.0, LocalDate.now(), null, true, "Conta inexistente")
        );
    }

    static boolean isHoraValida() {
        return LocalDateTime.now().getHour() < 14;
    }
}
