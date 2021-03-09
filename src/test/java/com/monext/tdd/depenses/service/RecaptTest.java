package com.monext.tdd.depenses.service;

import com.monext.tdd.depenses.bean.*;
import com.monext.tdd.depenses.ExpenseHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.YearMonth;
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
        List<Total> result = recapUnderTest.totals(Collections.emptyList());
        // assert
        assertEquals(Collections.emptyList(), result);

    }

    @Test
    void whenGivenOneExpenseShouldReturnThatAmount() {
        // action
        final List<Expense> expenses = Arrays.asList(ExpenseHelper.newExpenseToday(2000L, "groceries"));

        List<Total> result = recapUnderTest.totals(expenses);

        // assert
        assertEquals(new Total(new Amount(2000L, ExpenseHelper.CURRENCY_EURO), new Category("groceries")), result.get(0));
    }

    @Test
    void whenGivenTwoExpensesOnSameCategoryShouldReturnSum() {
        // action
        final List<Expense> expenses = Arrays.asList(
                ExpenseHelper.newExpenseToday(2000L, "groceries"),
                ExpenseHelper.newExpenseToday(3000L, "groceries")
        );

        List<Total> result = recapUnderTest.totals(expenses);


        // assert
        assertEquals(new Total(new Amount(5000L, ExpenseHelper.CURRENCY_EURO), new Category("groceries")), result.get(0));
    }

    @Test
    void whenGivenTwoExpensesOnDifferentCategoriesShouldReturnAmountByCategory() {
        // action
        final List<Expense> expenses = Arrays.asList(
                ExpenseHelper.newExpenseToday(2000L, "groceries"),
                ExpenseHelper.newExpenseToday(3000L, "finances")
        );

        List<Total> result = recapUnderTest.totals(expenses);

        // assert
        assertEquals(new Total(new Amount(3000L, ExpenseHelper.CURRENCY_EURO), new Category("finances")), result.get(0));
        assertEquals(new Total(new Amount(2000L, ExpenseHelper.CURRENCY_EURO), new Category("groceries")), result.get(1));
    }


    @Test
    void whenGivenTwoExpensesOnDifferentCategoriesShouldReturnAmountByCategorySortedByName() {
        // action
        final List<Expense> expenses = Arrays.asList(
                ExpenseHelper.newExpenseToday(2000L, "groceries"),
                ExpenseHelper.newExpenseToday(3000L, "hobbies")
        );

        List<Total> result = recapUnderTest.totals(expenses);

        // assert
        assertEquals(new Total(new Amount(2000L, ExpenseHelper.CURRENCY_EURO), new Category("groceries")), result.get(0));
        assertEquals(new Total(new Amount(3000L, ExpenseHelper.CURRENCY_EURO), new Category("hobbies")), result.get(1));
    }


    @Test
    void whenGivenTwoExpensesOnDifferentDateShouldReturnTotalByMonth() {
        // action
        final List<Expense> expenses = Arrays.asList(
                ExpenseHelper.newExpense(2000L, "groceries", "2021-02-20"),
                ExpenseHelper.newExpense(3000L, "hobbies", "2021-03-18"),
                ExpenseHelper.newExpense(5000L, "groceries", "2021-03-19"),
                ExpenseHelper.newExpense(9000L, "groceries", "2021-03-23"),
                ExpenseHelper.newExpense(2000L, "groceries", "2021-04-01")
        );

        List<Total> result = recapUnderTest.totalsByMonths(expenses, YearMonth.of(2021, Month.MARCH));

        // assert
        assertEquals(new Total(new Amount(14000L, ExpenseHelper.CURRENCY_EURO), new Category("groceries")), result.get(0));
        assertEquals(new Total(new Amount(3000L, ExpenseHelper.CURRENCY_EURO), new Category("hobbies")), result.get(1));
    }


    @Test
    void whenGivenMonthAndCategoryExpensesShouldReturnTotalOnThisMonthAndCategory() {
        // action
        final List<Expense> expenses = Arrays.asList(
                ExpenseHelper.newExpense(2000L, "groceries", "2021-02-20"),
                ExpenseHelper.newExpense(1000L, "hobbies", "2021-02-10"),
                ExpenseHelper.newExpense(3000L, "hobbies", "2021-03-18"),
                ExpenseHelper.newExpense(3000L, "hobbies", "2021-03-19"),
                ExpenseHelper.newExpense(5000L, "groceries", "2021-03-19"),
                ExpenseHelper.newExpense(9000L, "groceries", "2021-03-23"),
                ExpenseHelper.newExpense(2000L, "groceries", "2021-04-01")
        );

        List<Total> result = recapUnderTest.totalsByMonthsAndCategory(expenses, YearMonth.of(2021, Month.MARCH), "hobbies");

        // assert
        assertEquals(1, result.size());
        assertEquals(new Total(new Amount(6000L, ExpenseHelper.CURRENCY_EURO), new Category("hobbies")), result.get(0));
    }


}
