package com.finn.blackjack;

import java.io.IOException;

public class Main {
    public static void main(String... args) {
        try {
            new BlackjackSimulator().simulateBlackjackGame(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
