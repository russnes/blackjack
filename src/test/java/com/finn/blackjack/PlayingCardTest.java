package com.finn.blackjack;

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
}
