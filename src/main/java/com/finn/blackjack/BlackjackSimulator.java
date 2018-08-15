package com.finn.blackjack;

import java.io.IOException;

public class BlackjackSimulator {

    public GameOfBlackjackBuilder gameOfBlackjackBuilder;

    public BlackjackSimulator() {
        gameOfBlackjackBuilder = new GameOfBlackjackBuilder();
    }

    public void simulateBlackjackGame(String... args) throws IOException {
        if(args.length > 0) {
            simulateGameWithImportedDeck(args[0]);
        } else {
            simulateGameWithRandomDeck();
        }
    }

    public void simulateGameWithRandomDeck() {
        gameOfBlackjackBuilder.createGameWithRandomDeck();
    }

    public void simulateGameWithImportedDeck(String pathToDeckFile) throws IOException {
        gameOfBlackjackBuilder.createGameWithParsedDeck(pathToDeckFile);
    }

    public void setGameOfBlackjackBuilder(GameOfBlackjackBuilder gameOfBlackjackBuilder) {
        this.gameOfBlackjackBuilder = gameOfBlackjackBuilder;
    }
}
