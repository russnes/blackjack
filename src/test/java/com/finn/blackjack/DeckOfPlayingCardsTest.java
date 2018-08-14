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
        Assert.assertEquals(52, deckOfPlayingCards.getPlayingCardsInDeck().size() + deckOfPlayingCards.getDealtPlayingCards().size());
    }

    @Test
    public void eachPlayingCardInDeckIsUnique() {
        List<PlayingCard> playingCardsToCheck = new ArrayList<>();
        playingCardsToCheck.addAll(deckOfPlayingCards.getPlayingCardsInDeck());
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
        orderOfCardsBeforeShuffling.addAll(deckOfPlayingCards.getPlayingCardsInDeck());

        deckOfPlayingCards.shuffle(1337);
        Assert.assertFalse(orderOfCardsBeforeShuffling.equals(deckOfPlayingCards.getPlayingCardsInDeck()));
    }

    @Test(expected = IllegalStateException.class)
    public void cardsAreDealtFromAShuffledDeck() {
        DeckOfPlayingCards deckOfPlayingCards = new DeckOfPlayingCards();
        PlayingCard dealtPlayingCard = deckOfPlayingCards.dealPlayingCard();
    }


    @Test
    public void dealtCardsAreRemovedFromTheTopOfTheDeck() {
        DeckOfPlayingCards deckOfPlayingCards = new DeckOfPlayingCards();
        deckOfPlayingCards.shuffle();
        PlayingCard dealtCard = deckOfPlayingCards.dealPlayingCard();
        PlayingCard anotherDealtCard = deckOfPlayingCards.dealPlayingCard();
        Assert.assertNotEquals(dealtCard, anotherDealtCard);
    }


}
