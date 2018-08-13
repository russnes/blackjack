package com.finn.blackjack;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DeckOfPlayingCardsTest {

    static DeckOfPlayingCards deckOfPlayingCards;

    @BeforeClass
    public static void init() {
        deckOfPlayingCards = new DeckOfPlayingCards();
    }

    @Test
    public void deckOfPlayingCardsShallHave52Cards() {
        Assert.assertEquals(52, deckOfPlayingCards.getPlayingCards().size());
    }

}
