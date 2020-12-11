package com.oumar.kata.application;

import com.oumar.kata.domain.ClientAccount;
import com.oumar.kata.domain.ClientOperation;

import java.util.List;

public interface AccountRepository {

    List<ClientOperation> getClientOperations(ClientAccount clientAccount);

    void executeOperation(ClientOperation clientOperation);
}
