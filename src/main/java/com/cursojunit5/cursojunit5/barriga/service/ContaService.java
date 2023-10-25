package com.cursojunit5.cursojunit5.barriga.service;

import java.util.List;

import com.cursojunit5.cursojunit5.barriga.domain.Conta;
import com.cursojunit5.cursojunit5.barriga.domain.exceptions.ValidationException;
import com.cursojunit5.cursojunit5.barriga.service.external.ContaEvent;
import com.cursojunit5.cursojunit5.barriga.service.external.ContaEvent.EventType;
import com.cursojunit5.cursojunit5.barriga.service.repositories.ContaRepository;

public class ContaService {
    
    private ContaRepository contaRepository;
    private ContaEvent event;

    public ContaService(ContaRepository contaRepository, ContaEvent event) {
        this.contaRepository = contaRepository;
        this.event = event;
    }

    public Conta salvar(Conta conta) {

       List<Conta> contas = this.contaRepository.obterContasPorUsuario(conta.usuario().id());
    
       contas.stream().forEach(contaExistente -> {
        if(conta.nome().equalsIgnoreCase(contaExistente.nome()))
            throw new ValidationException("Usuário já possui uma conta com este nome");
       });

       Conta contaPersistida = this.contaRepository.salvar(conta);
       try {
         event.dispatch(contaPersistida, EventType.CREATED);
       } catch (Exception e) {
        this.contaRepository.delete(contaPersistida);
        throw new RuntimeException("Falha na criação da conta, tente novamente");
       }
      
       return contaPersistida;
    }

}
