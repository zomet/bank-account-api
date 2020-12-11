package com.oumar.kata.infra;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ClientOperationDb {
    private ClientAccountIdDb clientAccountId;
    private BigDecimal amount;
    private OperationTypeDb operationType;
    private LocalDateTime operationDate;

    public ClientAccountIdDb getClientAccountId() {
        return clientAccountId;
    }

    public void setClientAccountId(ClientAccountIdDb clientAccountId) {
        this.clientAccountId = clientAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OperationTypeDb getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationTypeDb operationType) {
        this.operationType = operationType;
    }

    public LocalDateTime getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDateTime operationDate) {
        this.operationDate = operationDate;
    }

}
