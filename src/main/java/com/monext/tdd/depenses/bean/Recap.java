package com.monext.tdd.depenses.bean;

import java.util.*;
import java.util.stream.Collectors;

public class Recap {
    public List<Total> getTotals(final List<Expense> emptyList) {
        if (!emptyList.isEmpty()) {
            Map<String, Long> temp = emptyList.stream().collect(Collectors.groupingBy(e -> e.getCategory().getName(), Collectors.summingLong(e -> e.getAmount().getValue())));

            return temp.entrySet().stream().map(e -> new Total(new Amount(e.getValue(), "euro"), new Category(e.getKey()))).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
