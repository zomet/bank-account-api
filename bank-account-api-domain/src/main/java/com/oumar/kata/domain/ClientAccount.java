package com.oumar.kata.domain;

import java.util.Objects;

public class ClientAccount {

    private final AccountId accountId;

    public ClientAccount(AccountId accountId) {
        this.accountId = accountId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAccount that = (ClientAccount) o;
        return accountId.equals(that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

    public static class AccountId {
        private final Long value;

        public AccountId(Long value) {
            this.value = Objects.requireNonNull(value);
        }

        public Long getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AccountId accountId = (AccountId) o;
            return value.equals(accountId.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
