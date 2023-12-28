package com.cursojunit5.cursojunit5.barriga.domain;

import java.time.LocalDate;

public class Transacao {
    
    private Long id;
    private String descricao;
    private Double valor;
    private Conta conta;
    private LocalDate data;
    private boolean status;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Conta getConta() {
        return conta;
    }
    public void setConta(Conta conta) {
        this.conta = conta;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    
}
