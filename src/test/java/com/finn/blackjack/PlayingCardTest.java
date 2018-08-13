package com.finn.blackjack;

import org.junit.Test;

import java.security.InvalidParameterException;

public class PlayingCardTest {

    @Test(expected = IllegalArgumentException.class)
    public void playingCardsMustHaveValueGreaterThan0() {
        PlayingCard playingCard = new PlayingCard(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void playingCardsMustHaveValueLessThan14() {
        PlayingCard playingCard = new PlayingCard(14);
    }

}
