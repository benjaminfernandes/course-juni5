package com.cursojunit5.cursojunit5.barriga.service.repositories;

import java.util.List;

import com.cursojunit5.cursojunit5.barriga.domain.Conta;

public interface ContaRepository {
    Conta salvar(Conta conta);
    List<Conta> obterContasPorUsuario(Integer usuarioId);
}
