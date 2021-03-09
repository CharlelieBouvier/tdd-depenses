package com.monext.tdd.depenses.service;

import com.monext.tdd.depenses.bean.Amount;
import com.monext.tdd.depenses.bean.Category;
import com.monext.tdd.depenses.bean.Expense;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
