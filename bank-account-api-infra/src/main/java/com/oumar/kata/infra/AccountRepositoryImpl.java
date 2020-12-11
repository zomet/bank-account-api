package com.oumar.kata.infra;

import com.oumar.kata.application.AccountRepository;
import com.oumar.kata.domain.ClientAccount;
import com.oumar.kata.domain.ClientOperation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final ClientOperationDbAdapter clientOperationDbAdapter;

    private final Map<ClientAccountIdDb, List<ClientOperationDb>> infraDataSource = new ConcurrentHashMap<>();

    public AccountRepositoryImpl(ClientOperationDbAdapter clientOperationDbAdapter) {
        this.clientOperationDbAdapter = clientOperationDbAdapter;
    }

    @Override
    public List<ClientOperation> getClientOperations(ClientAccount clientAccount) {
        ClientAccountIdDb clientAccountIdDb = ClientAccountIdDbAdapter.fromDomain(clientAccount.getAccountId());
        List<ClientOperationDb> clientOperationDbs = infraDataSource.get(clientAccountIdDb);
        return clientOperationDbAdapter.toDomains(clientOperationDbs);
    }

    @Override
    public void executeOperation(ClientOperation clientOperation) {
        ClientAccountIdDb clientAccountIdDb = ClientAccountIdDbAdapter.fromDomain(clientOperation.getClientAccountId());
        ClientOperationDb clientOperationDb = clientOperationDbAdapter.fromDomain(clientOperation);
        infraDataSource.computeIfAbsent(clientAccountIdDb, v -> new ArrayList<>())
                .add(clientOperationDb);
    }

}
