package com.cursojunit5.cursojunit5.barriga.service.external;

import com.cursojunit5.cursojunit5.barriga.domain.Conta;

public interface ContaEvent {
    public enum EventType {CREATED, UPDATED, DELETED};
    void dispatch(Conta conta, EventType type) throws Exception;
}
