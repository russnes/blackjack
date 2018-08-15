package com.finn.blackjack;

import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GameOfBlackjackBuilderTest {

    @Test
    public void callingBuildGameWithLoadedDeckCreatesGameWithDeckFileInput() throws IOException {
        GameOfBlackjackBuilder gameOfBlackjackBuilder = new GameOfBlackjackBuilder();
        GameOfBlackjack mockedGameOfBlackjack = mock(GameOfBlackjack.class);
        gameOfBlackjackBuilder.setGameOfBlackjack(mockedGameOfBlackjack);

        String path = "path";

        gameOfBlackjackBuilder.createGameWithParsedDeck(path);

        verify(mockedGameOfBlackjack, times(1)).initializeWithLoadedDeck(path);
    }

    @Test
    public void callingBuildWithRandomDeckInitiatesGameWithRandomDeck() {
        GameOfBlackjackBuilder gameOfBlackjackBuilder = new GameOfBlackjackBuilder();
        GameOfBlackjack mockedGameOfBlackjack = mock(GameOfBlackjack.class);
        gameOfBlackjackBuilder.setGameOfBlackjack(mockedGameOfBlackjack);

        gameOfBlackjackBuilder.createGameWithRandomDeck();

        verify(mockedGameOfBlackjack, times(1)).initializeWithRandomDeck();
    }
}
