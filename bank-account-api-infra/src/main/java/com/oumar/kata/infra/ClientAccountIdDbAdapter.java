package com.oumar.kata.infra;

import com.oumar.kata.domain.ClientAccount;

public class ClientAccountIdDbAdapter {

    private ClientAccountIdDbAdapter() {

    }

    public static ClientAccountIdDb fromDomain(ClientAccount.AccountId accountId) {
        if (accountId == null) {
            return null;
        }
        return new ClientAccountIdDb(accountId.getValue());
    }

    public static ClientAccount.AccountId toDomain(ClientAccountIdDb clientAccountIdDb) {
        return new ClientAccount.AccountId(clientAccountIdDb.getValue());
    }
}
