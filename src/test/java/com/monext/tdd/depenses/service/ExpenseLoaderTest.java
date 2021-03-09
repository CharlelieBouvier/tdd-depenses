package com.monext.tdd.depenses.service;

import com.monext.tdd.depenses.ExpenseHelper;
import com.monext.tdd.depenses.bean.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseLoaderTest {

    @InjectMocks
    private ExpenseLoader underTest;

    @BeforeEach
    void setUp() {
        underTest = new ExpenseLoader();
    }

    @Test
    void shouldReturnEmptyWhenGivenAnEmptyCsv() throws IOException {
        // action
        final InputStream in = new ByteArrayInputStream("".getBytes());

        final List<Expense> result = underTest.read(in);

        // assert
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void shouldReturnOneExpenseWhenGivenAnCsvWithOneLine() throws IOException {

        // action
        final InputStream in = new ByteArrayInputStream("2021/02/20;\"groceries\";10.00".getBytes());

        final List<Expense> result = underTest.read(in);

        // assert
        assertEquals(ExpenseHelper.newExpense(1000L, "groceries", "2021-02-20"), result.get(0));
    }


    @Test
    void shouldReturnOneExpenseWhenGivenAnCsvWithMoreThanOneLine() throws IOException {

        // action
        final InputStream in = new ByteArrayInputStream(
                ("2021/02/20;\"groceries\";10.00\n" +
                        "2021/01/20;\"car insurance\";-20.00")
                        .getBytes());

        final List<Expense> result = underTest.read(in);

        // assert
        assertEquals(2, result.size());
        assertEquals(ExpenseHelper.newExpense(1000L, "groceries", "2021-02-20"), result.get(0));
        assertEquals(ExpenseHelper.newExpense(-2000L, "car insurance", "2021-01-20"), result.get(1));
    }
}