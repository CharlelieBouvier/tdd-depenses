package com.monext.tdd.depenses.service;

import com.monext.tdd.depenses.bean.Amount;
import com.monext.tdd.depenses.bean.Category;
import com.monext.tdd.depenses.bean.Expense;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.io.InputStream;
import java.util.List;
import java.io.InputStreamReader;

import org.apache.commons.csv.*;

import java.io.*;

public class ExpenseLoader {

    private static DateFormat DATE_FORMAT_YYYYMMDD = new SimpleDateFormat("yyyy/MM/dd");

    public List<Expense> read(final InputStream in) throws IOException {

        final Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withSkipHeaderRecord()
                .withDelimiter(';')
                .parse(new InputStreamReader(in));

        final List<Expense> expenses = new ArrayList<>();
        Expense e;
        for (final CSVRecord record : records) {
            final String dateAsStr = record.get(0);
            final String categoryName = record.get(1);
            final String amountAsStr = record.get(2);

            final Amount amount = new Amount(Long.parseLong(amountAsStr.replace(".","")), "euro");
            final Category category = new Category(categoryName);
            try {
                e = new Expense(DATE_FORMAT_YYYYMMDD.parse(dateAsStr), category, amount);

                expenses.add(e);
            } catch (final ParseException parseException) {
                parseException.printStackTrace();
            }

        }
        return expenses;
    }


}
