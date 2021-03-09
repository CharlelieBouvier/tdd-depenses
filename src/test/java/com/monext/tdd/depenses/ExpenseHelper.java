package com.monext.tdd.depenses;

import com.monext.tdd.depenses.bean.Amount;
import com.monext.tdd.depenses.bean.Category;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpenseHelper {
    public static final String CURRENCY_EURO = "euro";

    private static DateFormat DATE_FORMAT_YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

    public static com.monext.tdd.depenses.bean.Expense newExpenseToday(final long amountValue, final String categoryName) {
        final Date date = new Date();
        return newExpense(amountValue, categoryName, DATE_FORMAT_YYYYMMDD.format(date));
    }

    public static com.monext.tdd.depenses.bean.Expense newExpense(final long amountValue, final String categoryName, final String date) {
        final Category category = new Category(categoryName);
        final Amount amount = new Amount(amountValue, CURRENCY_EURO);
        try {
            return new com.monext.tdd.depenses.bean.Expense(DATE_FORMAT_YYYYMMDD.parse(date), category, amount);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
