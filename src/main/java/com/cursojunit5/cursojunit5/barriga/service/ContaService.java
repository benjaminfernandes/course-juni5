package com.cursojunit5.cursojunit5.barriga.service;

import java.util.List;

import com.cursojunit5.cursojunit5.barriga.domain.Conta;
import com.cursojunit5.cursojunit5.barriga.domain.exceptions.ValidationException;
import com.cursojunit5.cursojunit5.barriga.service.repositories.ContaRepository;

public class ContaService {
    
    private ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta salvar(Conta conta) {

       List<Conta> contas = this.contaRepository.obterContasPorUsuario(conta.usuario().id());
    
       contas.stream().forEach(contaExistente -> {
        if(conta.nome().equalsIgnoreCase(contaExistente.nome()))
            throw new ValidationException("Usuário já possui uma conta com este nome");
       });


        return this.contaRepository.salvar(conta);
    }

}
