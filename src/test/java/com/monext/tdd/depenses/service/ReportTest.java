package com.monext.tdd.depenses.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportTest {


    @InjectMocks
    private Report underTest;


    @Test
    void shouldReadCsvAndPrintReport() throws IOException {

        final InputStream in = new ByteArrayInputStream(
                ("2021/02/20;\"groceries\";10.00\n" +
                        "2021/01/20;\"car insurance\";-20.00\n"
                        + "2021/01/18;\"groceries\";25.00\n"
                        + "2021/02/18;\"car insurance\";10.00"
                )
                        .getBytes());

        final OutputStream out = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(out);
        underTest.readCsvAndPrintReport(in, printStream);


        final String str = out.toString();
        final String[] split = str.split("\n");

        assertEquals(80, split[0].length());
        assertTrue(split[0].startsWith("car insurance"));
        assertTrue(split[0].endsWith("-10.00\r"));

        assertEquals(80, split[1].length());
        assertTrue(split[1].startsWith("groceries"));
        assertTrue(split[1].endsWith("35.00\r"));

        assertEquals(80, split[2].length());
        assertTrue(split[2].startsWith("total"));
        assertTrue(split[2].endsWith("25.00\r"));

    }


}