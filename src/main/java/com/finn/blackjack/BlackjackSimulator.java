package com.finn.blackjack;

public class BlackjackSimulator {

    public GameOfBlackjackBuilder gameOfBlackjackBuilder;

    public BlackjackSimulator() {
        gameOfBlackjackBuilder = new GameOfBlackjackBuilder();
    }

    public void simulateBlackjackGame(String... args) {
        if(args.length > 0) {
            simulateGameWithImportedDeck(args[0]);
        } else {
            simulateGameWithRandomDeck();
        }
    }

    public void simulateGameWithRandomDeck() {
        gameOfBlackjackBuilder.createGameWithRandomDeck();
    }

    public void simulateGameWithImportedDeck(String pathToDeckFile) {
        gameOfBlackjackBuilder.createGameWithParsedDeck(pathToDeckFile);
    }

    public void setGameOfBlackjackBuilder(GameOfBlackjackBuilder gameOfBlackjackBuilder) {
        this.gameOfBlackjackBuilder = gameOfBlackjackBuilder;
    }
}
