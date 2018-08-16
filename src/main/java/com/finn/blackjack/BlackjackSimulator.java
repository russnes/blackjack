package com.finn.blackjack;

import java.io.IOException;

public class BlackjackSimulator {

    private GameOfBlackjackBuilder gameOfBlackjackBuilder;
    private GameOfBlackjack gameOfBlackjack;
    private Logger logger;

    public BlackjackSimulator() {
        logger = new Logger();
        gameOfBlackjackBuilder = new GameOfBlackjackBuilder();
    }

    public void initializeGame(String... args) throws IOException {
        if(args.length > 0) {
            gameOfBlackjack = createGameWithImportedDeck(args[0]);
        } else {
            gameOfBlackjack = createGameWithRandomDeck();
        }
    }

    public void playGame() {
        gameOfBlackjack.deal();
        while (gameOfBlackjack.getWinner() == null) {
            gameOfBlackjack.dealNextCard();
        }
        logger.print(gameOfBlackjack.getSam().getHandString());
        logger.print(gameOfBlackjack.getWinner().getName());
    }

    public GameOfBlackjack createGameWithRandomDeck() {
        return gameOfBlackjackBuilder.createGameWithRandomDeck();
    }

    public GameOfBlackjack createGameWithImportedDeck(String pathToDeckFile) throws IOException {
        return gameOfBlackjackBuilder.createGameWithParsedDeck(pathToDeckFile);
    }

    public void setGameOfBlackjackBuilder(GameOfBlackjackBuilder gameOfBlackjackBuilder) {
        this.gameOfBlackjackBuilder = gameOfBlackjackBuilder;
    }

    public void setGameOfBlackjack(GameOfBlackjack gameOfBlackjack) {
        this.gameOfBlackjack = gameOfBlackjack;
    }

    public GameOfBlackjack getGameOfBlackjack() {
        return gameOfBlackjack;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
