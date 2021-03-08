package com.monext.tdd.depenses.bean;

import java.util.Date;
import java.util.Objects;

public class Expense {
    final Date date;
    final Category category;
    final Amount euro;

    public Expense(final Date date, final Category category, final Amount euro) {
        this.date = date;
        this.category = category;
        this.euro = euro;
    }

    public Amount getAmount() {
        return euro;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Expense expense = (Expense) o;
        return Objects.equals(date, expense.date) && Objects.equals(category, expense.category) && Objects.equals(euro, expense.euro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, category, euro);
    }
}
