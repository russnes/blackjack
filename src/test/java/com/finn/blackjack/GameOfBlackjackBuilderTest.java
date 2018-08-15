package com.finn.blackjack;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GameOfBlackjackBuilderTest {
    @Test
    public void callingBuildGameWithLoadedDeckCreatesGameWithDeckFileInput() {
        GameOfBlackjackBuilder gameOfBlackjackBuilder = new GameOfBlackjackBuilder();
        GameOfBlackjack mockedGameOfBlackjack = mock(GameOfBlackjack.class);
        gameOfBlackjackBuilder.setGameOfBlackjack(mockedGameOfBlackjack);

        String path = "path";

        gameOfBlackjackBuilder.createGameWithParsedDeck(path);

        verify(mockedGameOfBlackjack, times(1)).initializeWithLoadedDeck(path);
    }
}
