package com.monext.tdd.depenses;

import com.monext.tdd.depenses.service.Report;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class ReportMain {

    public static void main(final String[] args) {
        new ReportMain().go(args[0], System.out);
    }

    public void go(final String fileName, final PrintStream out) {
        final InputStream input = ReportMain.class.getClassLoader().getResourceAsStream(fileName);
        final Report report = new Report();
        final PrintStream printStream = new PrintStream(out);
        try {
            report.readCsvAndPrintReport(input, printStream);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
