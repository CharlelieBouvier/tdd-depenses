package com.monext.tdd.depenses.bean;

import java.util.Objects;

public class Amount {
    final long value;
    final String currency;

    public Amount(final long value, final String currency) {

        this.value = value;
        this.currency = currency;
    }

    public long getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Amount amount1 = (Amount) o;
        return value == amount1.value && Objects.equals(currency, amount1.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public String toString() {
        return "Amount{" +
                "amount=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }
}
