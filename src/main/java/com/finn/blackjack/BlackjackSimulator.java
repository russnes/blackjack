package com.finn.blackjack;

import java.io.IOException;

public class BlackjackSimulator {

    public GameOfBlackjackBuilder gameOfBlackjackBuilder;

    public BlackjackSimulator() {
        gameOfBlackjackBuilder = new GameOfBlackjackBuilder();
    }

    public void initializeGame(String... args) {
        if(args.length > 0) {
            try {
                simulateGameWithImportedDeck(args[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            simulateGameWithRandomDeck();
        }
    }

    public void playGame() {

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
