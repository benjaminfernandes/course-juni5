package com.cursojunit5.cursojunit5.barriga.service;

import java.time.LocalDateTime;

import com.cursojunit5.cursojunit5.barriga.domain.Transacao;
import com.cursojunit5.cursojunit5.barriga.domain.exceptions.ValidationException;
import com.cursojunit5.cursojunit5.barriga.service.repositories.TransacaoDao;

public class TransacaoService {

    private TransacaoDao transacaoDao;
    
    public Transacao salvar(Transacao transacao) {
        //if(LocalDateTime.now().getHour() > 14)
            //throw new RuntimeException("Tente novamente amanhã");
            
        System.out.println(LocalDateTime.now());
        if(transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
        if(transacao.getValor() == null) throw new ValidationException("Valor inexistente");
        if(transacao.getData() == null) throw new ValidationException("Data inexistente");
        if(transacao.getConta() == null) throw new ValidationException("Conta inexistente");
        
        return transacaoDao.salvar(transacao);
    }
}
