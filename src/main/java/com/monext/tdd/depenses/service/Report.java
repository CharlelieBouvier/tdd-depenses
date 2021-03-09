package com.monext.tdd.depenses.service;

import com.monext.tdd.depenses.bean.Expense;
import com.monext.tdd.depenses.bean.Total;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public class Report {
    public void readCsvAndPrintReport(final InputStream in, final PrintStream printStream) throws IOException {

        final ExpenseLoader expenseLoader = new ExpenseLoader();
        final Recap recap = new Recap();
        final ReportPrinter reportPrinter = new ReportPrinter();

        final List<Expense> expenses = expenseLoader.read(in);
        final List<Total> totals = recap.totals(expenses);
        reportPrinter.generateReport(totals, printStream);

    }
}
