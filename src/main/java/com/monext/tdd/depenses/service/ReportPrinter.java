package com.monext.tdd.depenses.service;

import com.monext.tdd.depenses.bean.Total;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

public class ReportPrinter {


    public static final String PRINT = "%-70s%9.2f";

    public void generateReport(final List<Total> totals, final PrintStream printStream) {

        BigDecimal sum = new BigDecimal("0");
        for (final Total t : totals) {
            final BigDecimal value = new BigDecimal(t.getAmount().getValue()).divide(new BigDecimal("100"));
            printStream.println(String.format(Locale.ENGLISH, PRINT, t.getCategory().getName(), value));
            sum = sum.add(value);
        }

        printStream.println(String.format(Locale.ENGLISH, PRINT, "total", sum));


    }
}
