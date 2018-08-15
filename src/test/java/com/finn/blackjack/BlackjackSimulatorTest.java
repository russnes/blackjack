package com.finn.blackjack;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.mockito.Mockito.*;

public class BlackjackSimulatorTest {

    @Test
    public void passingArgumentPathInitiatesGameWithParsedDeck() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("example_deck").getFile());
        String pathToFile = file.getAbsolutePath();

        GameOfBlackjackBuilder mockedGameOfBlackjackBuilder = mock(GameOfBlackjackBuilder.class);

        BlackjackSimulator blackjackSimulator = new BlackjackSimulator();
        blackjackSimulator.setGameOfBlackjackBuilder(mockedGameOfBlackjackBuilder);
        blackjackSimulator.simulateBlackjackGame(pathToFile);

        verify(mockedGameOfBlackjackBuilder, times(1)).createGameWithParsedDeck(pathToFile);
    }
}
