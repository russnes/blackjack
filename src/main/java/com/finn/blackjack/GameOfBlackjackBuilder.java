package com.finn.blackjack;

import java.io.IOException;

public class GameOfBlackjackBuilder {

    public GameOfBlackjack gameOfBlackjack;

    public GameOfBlackjackBuilder() {
        gameOfBlackjack = new GameOfBlackjack();
    }

    public GameOfBlackjack createGameWithParsedDeck(String pathToFile) throws IOException {
        gameOfBlackjack.initializeWithLoadedDeck(pathToFile);
        return gameOfBlackjack;
    }

    public GameOfBlackjack createGameWithRandomDeck() {
        gameOfBlackjack.initializeWithRandomDeck();
        return gameOfBlackjack;
    }

    public void setGameOfBlackjack(GameOfBlackjack gameOfBlackjack) {
        this.gameOfBlackjack = gameOfBlackjack;
    }
}
