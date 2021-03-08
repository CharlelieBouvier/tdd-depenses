package com.monext.tdd.depenses;

import com.monext.tdd.depenses.bean.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecaptTest {

    private static final String CURRENCY_EURO = "euro";
    private static DateFormat DATE_FORMAT_YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

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
        final List<Expense> expenses = Arrays.asList(newExpenseToday(2000L, "groceries"));

        List<Total> result = recapUnderTest.totals(expenses);

        // assert
        assertEquals(new Total(new Amount(2000L, CURRENCY_EURO), new Category("groceries")), result.get(0));
    }

    @Test
    void whenGivenTwoExpensesOnSameCategoryShouldReturnSum() {
        // action
        final List<Expense> expenses = Arrays.asList(
                newExpenseToday(2000L, "groceries"),
                newExpenseToday(3000L, "groceries")
        );

        List<Total> result = recapUnderTest.totals(expenses);


        // assert
        assertEquals(new Total(new Amount(5000L, CURRENCY_EURO), new Category("groceries")), result.get(0));
    }

    @Test
    void whenGivenTwoExpensesOnDifferentCategoriesShouldReturnAmountByCategory() {
        // action
        final List<Expense> expenses = Arrays.asList(
                newExpenseToday(2000L, "groceries"),
                newExpenseToday(3000L, "finances")
        );

        List<Total> result = recapUnderTest.totals(expenses);

        // assert
        assertEquals(new Total(new Amount(3000L, CURRENCY_EURO), new Category("finances")), result.get(0));
        assertEquals(new Total(new Amount(2000L, CURRENCY_EURO), new Category("groceries")), result.get(1));
    }


    @Test
    void whenGivenTwoExpensesOnDifferentCategoriesShouldReturnAmountByCategorySortedByName() {
        // action
        final List<Expense> expenses = Arrays.asList(
                newExpenseToday(2000L, "groceries"),
                newExpenseToday(3000L, "hobbies")
        );

        List<Total> result = recapUnderTest.totals(expenses);

        // assert
        assertEquals(new Total(new Amount(2000L, CURRENCY_EURO), new Category("groceries")), result.get(0));
        assertEquals(new Total(new Amount(3000L, CURRENCY_EURO), new Category("hobbies")), result.get(1));
    }


    @Test
    void whenGivenTwoExpensesOnDifferentDateShouldReturnTotalByMonth() {
        // action
        final List<Expense> expenses = Arrays.asList(
                newExpense(2000L, "groceries", "2021-02-20"),
                newExpense(3000L, "hobbies", "2021-03-18"),
                newExpense(5000L, "groceries", "2021-03-19"),
                newExpense(9000L, "groceries", "2021-03-23"),
                newExpense(2000L, "groceries", "2021-04-01")
        );

        List<Total> result = recapUnderTest.totalsByMonths(expenses, YearMonth.of(2021, Month.MARCH));

        // assert
        assertEquals(new Total(new Amount(14000L, CURRENCY_EURO), new Category("groceries")), result.get(0));
        assertEquals(new Total(new Amount(3000L, CURRENCY_EURO), new Category("hobbies")), result.get(1));
    }


    @Test
    void whenGivenMonthAndCategoryExpensesShouldReturnTotalOnThisMonthAndCategory() {
        // action
        final List<Expense> expenses = Arrays.asList(
                newExpense(2000L, "groceries", "2021-02-20"),
                newExpense(1000L, "hobbies", "2021-02-10"),
                newExpense(3000L, "hobbies", "2021-03-18"),
                newExpense(3000L, "hobbies", "2021-03-19"),
                newExpense(5000L, "groceries", "2021-03-19"),
                newExpense(9000L, "groceries", "2021-03-23"),
                newExpense(2000L, "groceries", "2021-04-01")
        );

        List<Total> result = recapUnderTest.totalsByMonthsAndCategory(expenses, YearMonth.of(2021, Month.MARCH), "hobbies");

        // assert
        assertEquals(1, result.size());
        assertEquals(new Total(new Amount(6000L, CURRENCY_EURO), new Category("hobbies")), result.get(0));
    }



    private Expense newExpenseToday(final long amountValue, final String categoryName) {
        final Date date = new Date();
        return newExpense(amountValue, categoryName, DATE_FORMAT_YYYYMMDD.format(date));
    }

    private Expense newExpense(final long amountValue, final String categoryName, final String date) {
        final Category category = new Category(categoryName);
        final Amount amount = new Amount(amountValue, CURRENCY_EURO);
        try {
            return new Expense(DATE_FORMAT_YYYYMMDD.parse(date), category, amount);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
