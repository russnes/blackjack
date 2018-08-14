package com.finn.blackjack;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void scoreOfPlayerHandIsSumOfHeldCards() {
        Player player = new Player("");
        player.getPlayingCards().add(new PlayingCard(5, PlayingCard.Suite.HEARTS));
        player.getPlayingCards().add(new PlayingCard(8, PlayingCard.Suite.DIAMONDS));
        Assert.assertEquals(5+8, player.getScoreOfHand());
    }

    @Test
    public void jackQueenAndKingAreValued10() {
        Player player = new Player("");
        player.getPlayingCards().add(new PlayingCard(11, PlayingCard.Suite.HEARTS));
        player.getPlayingCards().add(new PlayingCard(12, PlayingCard.Suite.HEARTS));
        player.getPlayingCards().add(new PlayingCard(13, PlayingCard.Suite.HEARTS));
        Assert.assertEquals(10+10+10, player.getScoreOfHand());
    }
}
