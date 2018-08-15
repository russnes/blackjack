package com.finn.blackjack;

import java.io.IOException;

public class GameOfBlackjackBuilder {

    public GameOfBlackjack gameOfBlackjack;

    public GameOfBlackjackBuilder() {
        gameOfBlackjack = new GameOfBlackjack();
    }

    public void createGameWithParsedDeck(String pathToFile) throws IOException {
        gameOfBlackjack.initializeWithLoadedDeck(pathToFile);
    }

    public void createGameWithRandomDeck() {
        gameOfBlackjack.initializeWithRandomDeck();
    }

    public void setGameOfBlackjack(GameOfBlackjack gameOfBlackjack) {
        this.gameOfBlackjack = gameOfBlackjack;
    }
}
