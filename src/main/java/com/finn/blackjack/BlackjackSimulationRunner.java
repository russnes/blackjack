package com.finn.blackjack;

import java.io.IOException;

public class BlackjackSimulationRunner {

    private BlackjackSimulator blackjackSimulator;

    public BlackjackSimulationRunner() {
        blackjackSimulator = new BlackjackSimulator();
    }

    public void simulateBlackjackGame(String... args) throws IOException {
        blackjackSimulator.initializeGame(args);
        blackjackSimulator.playGame();
    }

    public void setBlackjackSimulator(BlackjackSimulator blackjackSimulator) {
        this.blackjackSimulator = blackjackSimulator;
    }
}
