package com.oumar.kata.infra;

import com.oumar.kata.domain.OperationType;

public enum OperationTypeDb {
    DEPOSIT {
        @Override
        public OperationType toDomain() {
            return OperationType.DEPOSIT;
        }
    },
    WITHDRAWAL {
        @Override
        public OperationType toDomain() {
            return OperationType.WITHDRAWAL;
        }
    };

    public static OperationTypeDb fromDomain(OperationType operationType) {
        return OperationTypeDb.valueOf(operationType.name());
    }

    public abstract OperationType toDomain();

}
