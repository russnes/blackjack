package com.finn.blackjack;

public class GameOfBlackjackBuilder {

    public GameOfBlackjack gameOfBlackjack;

    public void createGameWithParsedDeck(String pathToFile) {
        gameOfBlackjack.initializeWithLoadedDeck(pathToFile);
    }

    public void setGameOfBlackjack(GameOfBlackjack gameOfBlackjack) {
        this.gameOfBlackjack = gameOfBlackjack;
    }
}
