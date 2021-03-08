package com.monext.tdd.depenses.bean;

import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Recap {

    public static final String CURRENCY_EURO = "euro";

    public List<Total> totals(final List<Expense> expenses) {
        return totalsWithFilters(expenses, (e) -> true);
    }

    public List<Total> totalsByMonths(final List<Expense> expenses, final YearMonth yearMonth) {
        final Predicate<Expense> yearMonthPredicate = isInMonthPredicate(yearMonth);
        return totalsWithFilters(expenses, yearMonthPredicate);
    }

    private List<Total> totalsWithFilters(final List<Expense> expenses, final Predicate<Expense> predicate) {
        final Collector<Expense, ?, Map<String, Long>> collector = Collectors.groupingBy(e -> e.getCategory().getName(),
                Collectors.summingLong(e -> e.getAmount().getValue()));
        final Map<String, Long> expensesByCategory = expenses.stream()
                .filter(predicate)
                .collect(collector);
        return expensesByCategory.entrySet().stream()
                .map(e -> new Total(new Amount(e.getValue(), CURRENCY_EURO), new Category(e.getKey())))
                .sorted(Comparator.comparing((Total t) -> t.getCategory().getName()))
                .collect(Collectors.toList());
    }

    public List<Total> totalsByMonthsAndCategory(final List<Expense> expenses, final YearMonth yearMonth, final String categoryName) {
        final Predicate<Expense> yearMonthPredicate = isInMonthPredicate(yearMonth);
        final Predicate<Expense> categoryPredicate = (e) -> e.getCategory().getName().equals(categoryName);
        final Predicate<Expense> predicate = yearMonthPredicate.and(categoryPredicate);
        return totalsWithFilters(expenses, predicate);
    }

    private Predicate<Expense> isInMonthPredicate(final YearMonth yearMonth) {
        return (e) -> YearMonth.from(e.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).equals(yearMonth);
    }
}
