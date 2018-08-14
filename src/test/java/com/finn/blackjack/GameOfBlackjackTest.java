package com.finn.blackjack;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void playersAreDealtTwoCardsInitially() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        gameOfBlackjack.deal();

        Assert.assertEquals(2, gameOfBlackjack.getPlayers().get(0).getPlayingCards().size());
        Assert.assertEquals(2, gameOfBlackjack.getPlayers().get(1).getPlayingCards().size());
    }

    @Test
    public void cardsInPlayersHandsAreFromTheGameDeck() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        gameOfBlackjack.deal();

        for(Player player : gameOfBlackjack.getPlayers()) {
            for(PlayingCard playingCard : player.getPlayingCards()) {
                Assert.assertTrue(gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().contains(playingCard)
                || gameOfBlackjack.getDeckOfPlayingCards().getDealtPlayingCards().contains(playingCard));
            }
        }
    }

    @Test
    public void cardsAreDealtInitiallyInAlternatingOrder() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        List<PlayingCard> originallyOrderedPlayingCards = new ArrayList<>();
        for(int i = 0; i<4; i++) {
            originallyOrderedPlayingCards.add(gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().get(i));
        }

        gameOfBlackjack.deal();
        for(Player player : gameOfBlackjack.getPlayers()) {
            PlayingCard firstPlayingCard = player.getPlayingCards().get(0);
            PlayingCard secondPlayingCard = player.getPlayingCards().get(1);
            int indexOfFirstPlayingCard = originallyOrderedPlayingCards.indexOf(firstPlayingCard);
            int indexOfSecondPlayingCard = originallyOrderedPlayingCards.indexOf(secondPlayingCard);
            Assert.assertFalse(indexOfSecondPlayingCard == indexOfFirstPlayingCard+1);
        }
    }

    @Test
    public void samIsDealtTheFirstCard() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        PlayingCard firstPlayingCard = gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().get(0);
        gameOfBlackjack.deal();
        for(Player player : gameOfBlackjack.getPlayers()) {
            if("Sam".equals(player.getName())) {
                Assert.assertTrue(player.getPlayingCards().contains(firstPlayingCard));
            }
        }
    }


}
