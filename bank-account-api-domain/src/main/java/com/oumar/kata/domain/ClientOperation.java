package com.oumar.kata.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class ClientOperation {

    private final ClientAccount.AccountId clientAccountId;
    private final BigDecimal amount;
    private final OperationType operationType;
    private final LocalDateTime operationDate;

    private ClientOperation(Builder builder) {
        this.clientAccountId = requireNonNull(builder.clientAccountId);
        this.amount =  requireNonNull(builder.amount);
        this.operationType = requireNonNull(builder.operationType);
        this.operationDate = requireNonNull(builder.operationDate);
    }

    public ClientAccount.AccountId getClientAccountId() {
        return clientAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public LocalDateTime getOperationDate() {
        return operationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientOperation that = (ClientOperation) o;
        return clientAccountId.equals(that.clientAccountId) &&
                amount.equals(that.amount) &&
                operationType == that.operationType &&
                operationDate.equals(that.operationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientAccountId, amount, operationType, operationDate);
    }

    public static class Builder {

        private ClientAccount.AccountId clientAccountId;
        private BigDecimal amount;
        private OperationType operationType;
        private LocalDateTime operationDate;

        public Builder withClientAccountId(ClientAccount.AccountId clientAccountId) {
            this.clientAccountId = clientAccountId;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withOperationType(OperationType operationType) {
            this.operationType = operationType;
            return this;
        }

        public Builder withOperationDate(LocalDateTime operationDate) {
            this.operationDate = operationDate;
            return this;
        }

        public ClientOperation build() {
            return new ClientOperation(this);
        }

    }
}
