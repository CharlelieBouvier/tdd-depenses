package com.monext.tdd.depenses.bean;

import java.util.Objects;

public class Total {
    final Amount amount;
    final Category category;

    public Total(final Amount amount, final Category category) {

        this.amount = amount;
        this.category = category;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Total total = (Total) o;
        return Objects.equals(amount, total.amount) && Objects.equals(category, total.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, category);
    }
}
