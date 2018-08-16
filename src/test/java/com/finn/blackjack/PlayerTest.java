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

    @Test
    public void creatingHandStringReflectsCardsInHand() {
        Player player = new Player("new player");
        PlayingCard playingCard1 = new PlayingCard(11, PlayingCard.Suite.HEARTS);
        PlayingCard playingCard2 = new PlayingCard(12, PlayingCard.Suite.SPADES);
        PlayingCard playingCard3 = new PlayingCard(13, PlayingCard.Suite.DIAMONDS);
        player.getPlayingCards().add(playingCard1);
        player.getPlayingCards().add(playingCard2);
        player.getPlayingCards().add(playingCard3);

        StringBuilder stringBuilder = new StringBuilder();
        String card1String = playingCard1.getTextRepresentationOfCard(stringBuilder);
        stringBuilder.setLength(0);
        String card2String = playingCard2.getTextRepresentationOfCard(stringBuilder);
        stringBuilder.setLength(0);
        String card3String = playingCard3.getTextRepresentationOfCard(stringBuilder);
        stringBuilder.setLength(0);
        stringBuilder.append(card1String);
        stringBuilder.append(", ");
        stringBuilder.append(card2String);
        stringBuilder.append(", ");
        stringBuilder.append(card3String);

        String expectedHandString = stringBuilder.toString();
        stringBuilder.setLength(0);

        Assert.assertEquals(expectedHandString, player.getHandString());
    }
}
