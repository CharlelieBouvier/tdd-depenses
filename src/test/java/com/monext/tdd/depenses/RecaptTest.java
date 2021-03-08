package com.monext.tdd.depenses;

import com.monext.tdd.depenses.bean.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecaptTest {

    private Recap recapUnderTest;

    @BeforeEach
    void setUp() {
        recapUnderTest = new Recap();
    }

    @Test
    void shouldReturnEmptyWhenGivenAnEmptyList() {
        // action
        List<Total> result = recapUnderTest.getTotals(Collections.emptyList());
        // assert
        assertEquals(Collections.emptyList(), result);

    }

    @Test
    void whenGivenOneExpenseShouldReturnThatAmount() {
        // action
        final List<Expense> expenses = Arrays.asList(newExpenseToday(2000L, "groceries"));

        List<Total> result = recapUnderTest.getTotals(expenses);

        // assert
        assertEquals(new Total(new Amount(2000L, "euro"), new Category("groceries")), result.get(0));
    }

    @Test
    void whenGivenTwoExpensesOnSameCategoryShouldReturnSum() {
        // action
        final List<Expense> expenses = Arrays.asList(
                newExpenseToday(2000L, "groceries"),
                newExpenseToday(3000L, "groceries")
                );

        List<Total> result = recapUnderTest.getTotals(expenses);

        // assert
        assertEquals(new Total(new Amount(5000L, "euro"), new Category("groceries")), result.get(0));
    }

    @Test
    void whenGivenTwoExpensesOnDifferentCategoriesShouldReturnAmountByCategory() {
        // action
        final List<Expense> expenses = Arrays.asList(
                newExpenseToday(2000L, "groceries"),
                newExpenseToday(3000L, "hobbies")
        );

        List<Total> result = recapUnderTest.getTotals(expenses);

        // assert
        assertEquals(new Total(new Amount(3000L, "euro"), new Category("hobbies")), result.get(0));
        assertEquals(new Total(new Amount(2000L, "euro"), new Category("groceries")), result.get(1));
    }

    private Expense newExpenseToday(final long amountValue, final String categoryName) {
        return new Expense(new Date(), new Category(categoryName), new Amount(amountValue, "euro"));
    }
}
