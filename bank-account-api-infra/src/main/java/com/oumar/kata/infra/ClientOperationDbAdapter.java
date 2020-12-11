package com.oumar.kata.infra;

import com.oumar.kata.domain.ClientOperation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientOperationDbAdapter {

    public ClientOperation toDomain(ClientOperationDb clientOperationDb) {
        if (clientOperationDb == null) {
            return null;
        }
        return new ClientOperation.Builder()
                .withClientAccountId(ClientAccountIdDbAdapter.toDomain(clientOperationDb.getClientAccountId()))
                .withAmount(clientOperationDb.getAmount())
                .withOperationType(clientOperationDb.getOperationType().toDomain())
                .withOperationDate(clientOperationDb.getOperationDate())
                .build();
    }

    public List<ClientOperation> toDomains(List<ClientOperationDb> clientOperationDbs) {
        return clientOperationDbs
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public ClientOperationDb fromDomain(ClientOperation clientOperation) {
        if (clientOperation == null) {
            return null;
        }
        ClientOperationDb clientOperationDb = new ClientOperationDb();
        clientOperationDb.setClientAccountId(ClientAccountIdDbAdapter.fromDomain(clientOperation.getClientAccountId()));
        clientOperationDb.setAmount(clientOperation.getAmount());
        clientOperationDb.setOperationType(OperationTypeDb.fromDomain(clientOperation.getOperationType()));
        clientOperationDb.setOperationDate(clientOperation.getOperationDate());
        return clientOperationDb;
    }
}
