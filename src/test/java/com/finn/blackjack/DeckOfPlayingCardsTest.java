package com.finn.blackjack;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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

    @Test
    public void eachPlayingCardInDeckIsUnique() {
        List<PlayingCard> playingCardsToCheck = new ArrayList<>();
        playingCardsToCheck.addAll(deckOfPlayingCards.getPlayingCards());
        Iterator<PlayingCard> playingCardIterator = playingCardsToCheck.iterator();
        while (playingCardIterator.hasNext()) {
            PlayingCard playingCardToCheck = playingCardsToCheck.remove(0);
            for(PlayingCard playingCardLeftToCheck : playingCardsToCheck) {
                if(playingCardToCheck.getValue() == playingCardLeftToCheck.getValue() &&
                        playingCardToCheck.getSuite() == playingCardLeftToCheck.getSuite()) {
                    Assert.fail();
                }
            }
        }
    }

    @Test
    public void cardsAreInRandomOrderAfterShuffling() {
        List<PlayingCard> orderOfCardsBeforeShuffling = new ArrayList<>();
        orderOfCardsBeforeShuffling.addAll(deckOfPlayingCards.getPlayingCards());

        deckOfPlayingCards.shuffle(1337);
        Assert.assertFalse(orderOfCardsBeforeShuffling.equals(deckOfPlayingCards.getPlayingCards()));
    }


}
