package com.finn.blackjack;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class BlackjackSimulatorTest {

    @Test
    public void passingArgumentPathInitiatesGameWithParsedDeck() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("example_deck").getFile());
        String pathToFile = file.getAbsolutePath();

        GameOfBlackjackBuilder mockedGameOfBlackjackBuilder = mock(GameOfBlackjackBuilder.class);

        BlackjackSimulator blackjackSimulator = new BlackjackSimulator();
        blackjackSimulator.setGameOfBlackjackBuilder(mockedGameOfBlackjackBuilder);
        blackjackSimulator.simulateBlackjackGame(pathToFile);

        verify(mockedGameOfBlackjackBuilder, times(1)).createGameWithParsedDeck(pathToFile);
    }

    @Test
    public void passingNoArgumentsInitiatesGameWithRandomDeck() throws IOException {
        GameOfBlackjackBuilder mockedGameOfBlackjackBuilder = mock(GameOfBlackjackBuilder.class);

        BlackjackSimulator blackjackSimulator = new BlackjackSimulator();
        blackjackSimulator.setGameOfBlackjackBuilder(mockedGameOfBlackjackBuilder);
        blackjackSimulator.simulateBlackjackGame();

        verify(mockedGameOfBlackjackBuilder, times(1)).createGameWithRandomDeck();
    }
}
