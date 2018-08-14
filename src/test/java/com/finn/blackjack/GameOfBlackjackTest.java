package com.finn.blackjack;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameOfBlackjackTest {

    static GameOfBlackjack gameOfBlackjack;

    @BeforeClass
    public static void init() {
        gameOfBlackjack = new GameOfBlackjack();
    }

    @Test
    public void gameHasTwoPlayers() {
        Assert.assertEquals(2, gameOfBlackjack.getPlayers().size());
    }

    @Test
    public void playersAreNamedSamAndTheDealer() {
        boolean samExisted = false;
        boolean theDealerExisted = false;
        for(Player player : gameOfBlackjack.getPlayers()) {
            if("Sam".equals(player.getName())) {
                samExisted = true;
            } else if("the Dealer".equals(player.getName())) {
                theDealerExisted = true;
            }
        }
        Assert.assertTrue(samExisted && theDealerExisted);
    }

}
