package com.monext.tdd.depenses;

import com.monext.tdd.depenses.bean.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecaptTest {
    @Test
    void shouldReturnEmptyWhenGivenAnEmptyList() {
        // data
        final Recap recap = new Recap();
        // action
        List<Total> result = recap.getTotals(Collections.emptyList());
        // assert
        assertEquals(Collections.emptyList(), result);

    }

    @Test
    void whenGivenOneExpenseShouldReturnThatAmount() {
        // data
        final Recap recap = new Recap();

        // action
        final List<Expense> expenses = Arrays.asList(new Expense(new Date(), new Category("groceries"), new Amount(2000L, "euro")));

        List<Total> result = recap.getTotals(expenses);

        // assert
        assertEquals(new Total(new Amount(2000L, "euro"), new Category("groceries")), result.get(0));
    }
}
