package com.monext.tdd.depenses.bean;

import java.util.Date;
import java.util.Objects;

public class Expense {
    final Date date;
    final Category category;
    final Amount amount;

    public Expense(final Date date, final Category category, final Amount amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public Amount getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Expense expense = (Expense) o;
        return Objects.equals(date, expense.date) && Objects.equals(category, expense.category) && Objects.equals(amount, expense.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, category, amount);
    }
}
