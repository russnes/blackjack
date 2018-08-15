package com.finn.blackjack;

public class GameOfBlackjackBuilder {

    public GameOfBlackjack gameOfBlackjack;

    public GameOfBlackjackBuilder() {
        gameOfBlackjack = new GameOfBlackjack();
    }

    public void createGameWithParsedDeck(String pathToFile) {
        gameOfBlackjack.initializeWithLoadedDeck(pathToFile);
    }

    public void createGameWithRandomDeck() {

    }

    public void setGameOfBlackjack(GameOfBlackjack gameOfBlackjack) {
        this.gameOfBlackjack = gameOfBlackjack;
    }
}
