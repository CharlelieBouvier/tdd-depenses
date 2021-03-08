package com.monext.tdd.depenses.bean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Recap {
    public List<Total> getTotals(final List<Expense> emptyList) {
        if (!emptyList.isEmpty()) {
            final Expense expense = emptyList.get(0);
            return Arrays.asList(new Total(expense.getAmount(), expense.getCategory()));
        }
        return Collections.emptyList();
    }
}
