package com.finn.blackjack;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class LoggerTest {

    @Test
    public void printedMessageIsLoggedToSystemOutWithNewLine() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));

        String message = "testing 123";
        Logger logger = new Logger();
        logger.print(message);

        assertEquals(message + '\n', outContent.toString());

        System.setOut(originalOut);
    }
}
