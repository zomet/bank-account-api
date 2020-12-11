package com.oumar.kata.infra;

import com.oumar.kata.domain.ClientAccount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ClientAccountIdDbAdapterTest {

    @Test
    void fromDomain_test_with_null_accountId_should_return_null() {
        assertThat(ClientAccountIdDbAdapter.fromDomain(null)).isNull();
    }

    @Test
    void fromDomain_test_with_not_null_accountId_should_return_valid_Id() {
        assertThat(ClientAccountIdDbAdapter.fromDomain(new ClientAccount.AccountId(10L))).isNotNull();
    }

    @Test
    void toDomain_test() {
        ClientAccountIdDb clientAccountIdDb = new ClientAccountIdDb(1L);
        ClientAccount.AccountId accountId = ClientAccountIdDbAdapter.toDomain(clientAccountIdDb);
        assertThat(1L).isEqualTo(accountId.getValue());
    }
}