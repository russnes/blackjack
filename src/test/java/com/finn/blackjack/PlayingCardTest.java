package com.finn.blackjack;

import org.junit.Assert;
import org.junit.Test;

public class PlayingCardTest {

    @Test(expected = IllegalArgumentException.class)
    public void playingCardsMustHaveValueGreaterThan0() {
        PlayingCard playingCard = new PlayingCard(0, PlayingCard.Suite.CLUBS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void playingCardsMustHaveValueLessThan14() {
        PlayingCard playingCard = new PlayingCard(14, PlayingCard.Suite.CLUBS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void playingCardSuiteCannotBeNull() {
        PlayingCard playingCard = new PlayingCard(13, null);
    }

    @Test
    public void cardParsedFromTextIsCorrespondsToOriginalText() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PlayingCard.CHAR_HEARTS);
        stringBuilder.append(7);
        String stringToParse = stringBuilder.toString();
        stringBuilder.setLength(0);
        PlayingCard parsedPlayingCard = new PlayingCard(stringToParse);
        Assert.assertEquals(stringToParse, parsedPlayingCard.getTextRepresentationOfCard(stringBuilder));
        stringBuilder.setLength(0);

        stringBuilder.append(PlayingCard.CHAR_SPADES);
        stringBuilder.append(PlayingCard.CHAR_ACE);
        stringToParse = stringBuilder.toString();
        stringBuilder.setLength(0);
        parsedPlayingCard = new PlayingCard(stringToParse);
        Assert.assertEquals(stringToParse, parsedPlayingCard.getTextRepresentationOfCard(stringBuilder));
        stringBuilder.setLength(0);
    }
}
