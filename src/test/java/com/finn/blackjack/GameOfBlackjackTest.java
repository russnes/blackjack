package com.finn.blackjack;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameOfBlackjackTest {

    static GameOfBlackjack gameOfBlackjack;

    @BeforeClass
    public static void init() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
    }

    @Test(expected = IllegalStateException.class)
    public void cardsAreTakenFromAShuffledDeck() {
        gameOfBlackjack.dealCard()
    }

}
