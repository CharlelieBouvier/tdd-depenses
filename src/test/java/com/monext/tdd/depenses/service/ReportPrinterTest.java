package com.monext.tdd.depenses.service;

import com.monext.tdd.depenses.ExpenseHelper;
import com.monext.tdd.depenses.bean.Amount;
import com.monext.tdd.depenses.bean.Category;
import com.monext.tdd.depenses.bean.Expense;
import com.monext.tdd.depenses.bean.Total;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.CollectionUtils;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportPrinterTest {

    @InjectMocks
    private ReportPrinter underTest;


    @Test
    void whenGivenTotalListShouldOutputReport() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final List<Total> totals = Arrays.asList(new Total(new Amount(2000L, ExpenseHelper.CURRENCY_EURO), new Category("groceries")));
        final PrintStream printStream = new PrintStream(baos);

        underTest.generateReport(totals, printStream);

        final String str = baos.toString();
        final String[] split = str.split("\n");

        assertEquals(80, split[0].length());
        assertTrue(split[0].startsWith("groceries"));
        assertTrue(split[0].endsWith("20.00\r"));

        assertEquals(80, split[1].length());
        assertTrue(split[1].startsWith("total"));
        assertTrue(split[1].endsWith("20.00\r"));

    }


    @Test
    void whenGivenServeralTotalsShouldOutputSum() {

        final List<Total> totals = Arrays.asList(
                new Total(new Amount(1000L, ExpenseHelper.CURRENCY_EURO), new Category("car insurance")),
                new Total(new Amount(5000L, ExpenseHelper.CURRENCY_EURO), new Category("groceries"))


        );

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(baos);

        underTest.generateReport(totals, printStream);

        final String str = baos.toString();
        final String[] split = str.split("\n");

        assertEquals(80, split[0].length());
        assertTrue(split[0].startsWith("car insurance"));
        assertTrue(split[0].endsWith("10.00\r"));

        assertEquals(80, split[1].length());
        assertTrue(split[1].startsWith("groceries"));
        assertTrue(split[1].endsWith("50.00\r"));

        assertEquals(80, split[2].length());
        assertTrue(split[2].startsWith("total"));
        assertTrue(split[2].endsWith("60.00\r"));

    }

}