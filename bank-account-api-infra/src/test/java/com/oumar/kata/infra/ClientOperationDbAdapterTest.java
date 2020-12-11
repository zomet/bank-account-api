package com.oumar.kata.infra;

import com.oumar.kata.domain.ClientAccount;
import com.oumar.kata.domain.ClientOperation;
import com.oumar.kata.domain.OperationType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientOperationDbAdapterTest {

    ClientOperationDbAdapter clientOperationDbAdapter;

    @BeforeAll
    public void init() {
        clientOperationDbAdapter = new ClientOperationDbAdapter();
    }

    @Test
    void null_input_adapter_toDomain_test_should_return_null() {
        assertThat(clientOperationDbAdapter.toDomain(null)).isNull();
    }

    @Test
    void toDomain_adapter_test() {
        ClientOperationDb clientOperationDb = createClientOperationDb();
        ClientOperation clientOperation = clientOperationDbAdapter.toDomain(clientOperationDb);
        assertThat(2L).isEqualTo(clientOperation.getClientAccountId().getValue());
        assertThat(BigDecimal.TEN).isEqualTo(clientOperation.getAmount());
        assertThat(OperationType.DEPOSIT).isEqualTo(clientOperation.getOperationType());
        assertThat(LocalDateTime.of(2020, Month.JANUARY, 1, 0, 1)).isEqualTo(clientOperation.getOperationDate());
    }

    private ClientOperationDb createClientOperationDb() {
        ClientOperationDb clientOperationDb = new ClientOperationDb();
        clientOperationDb.setClientAccountId(new ClientAccountIdDb(2L));
        clientOperationDb.setAmount(BigDecimal.TEN);
        clientOperationDb.setOperationType(OperationTypeDb.DEPOSIT);
        clientOperationDb.setOperationDate(LocalDateTime.of(2020, Month.JANUARY, 1, 0, 1));
        return clientOperationDb;
    }

    @Test
    void toDomains_test() {
        ClientOperation clientOperation = new ClientOperation.Builder()
                .withClientAccountId(new ClientAccount.AccountId(1L))
                .withAmount(BigDecimal.TEN)
                .withOperationType(OperationType.WITHDRAWAL)
                .withOperationDate(LocalDateTime.of(2020, Month.FEBRUARY, 1, 0, 1))
                .build();
        ClientOperationDbAdapter mockAdapter = Mockito.mock(ClientOperationDbAdapter.class);
        when(mockAdapter.toDomains(anyList())).thenCallRealMethod();
        when(mockAdapter.toDomain(any(ClientOperationDb.class))).thenReturn(clientOperation);
        ClientOperationDb clientOperationDb = new ClientOperationDb();
        List<ClientOperationDb> clientOperationDbs = Arrays.asList(clientOperationDb, clientOperationDb);
        List<ClientOperation> clientOperations = mockAdapter.toDomains(clientOperationDbs);
        verify(mockAdapter, times(2)).toDomain(clientOperationDb);
        assertThat(clientOperations).hasSize(2);
        assertThat(clientOperations).containsExactly(clientOperation, clientOperation);
    }

    @Test
    void null_input_adapter_fromDomain_test_should_return_null() {
        assertThat(clientOperationDbAdapter.fromDomain(null)).isNull();
    }

    @Test
    void fromDomain_test() {
        ClientOperation clientOperation = new ClientOperation.Builder()
                .withClientAccountId(new ClientAccount.AccountId(1L))
                .withAmount(BigDecimal.TEN)
                .withOperationType(OperationType.WITHDRAWAL)
                .withOperationDate(LocalDateTime.now())
                .build();
        ClientOperationDb clientOperationDb = clientOperationDbAdapter.fromDomain(clientOperation);
        assertThat(1L).isEqualTo(clientOperationDb.getClientAccountId().getValue());
        assertThat(BigDecimal.TEN).isEqualTo(clientOperationDb.getAmount());
        assertThat(OperationTypeDb.WITHDRAWAL).isEqualTo(clientOperationDb.getOperationType());
        assertThat(LocalDateTime.now()).isEqualToIgnoringMinutes(clientOperationDb.getOperationDate());
    }
}