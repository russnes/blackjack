package com.finn.blackjack;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeckOfPlayingCardsTest {

    @Test
    public void deckOfPlayingCardsShallHave52Cards() {
        DeckOfPlayingCards deckOfPlayingCards = new DeckOfPlayingCards();
        deckOfPlayingCards.initiateNewShuffledDeck();
        Assert.assertEquals(52, deckOfPlayingCards.getPlayingCardsInDeck().size() + deckOfPlayingCards.getDealtPlayingCards().size());
    }

    @Test
    public void eachPlayingCardInDeckIsUnique() {
        DeckOfPlayingCards deckOfPlayingCards = new DeckOfPlayingCards();
        deckOfPlayingCards.initiateNewShuffledDeck();
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
        DeckOfPlayingCards deckOfPlayingCards = new DeckOfPlayingCards();
        deckOfPlayingCards.initiateNewShuffledDeck();
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
        deckOfPlayingCards.initiateNewShuffledDeck();
        deckOfPlayingCards.shuffle();
        PlayingCard dealtCard = deckOfPlayingCards.dealPlayingCard();
        PlayingCard anotherDealtCard = deckOfPlayingCards.dealPlayingCard();
        Assert.assertNotEquals(dealtCard, anotherDealtCard);
    }

    @Test
    public void cardsParsedFromFileAppearInTheProvidedOrder() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("example_deck").getFile());
        String pathToFile = file.getAbsolutePath();
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(pathToFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String inputText = new String(bytes);
        inputText = inputText.trim();
        String[] cards = inputText.split(",");

        DeckOfPlayingCards deckOfPlayingCards = null;
        deckOfPlayingCards = new DeckOfPlayingCards();
        deckOfPlayingCards.initiateNewLoadedDeck(pathToFile);
        
        StringBuilder stringBuilder = new StringBuilder();
        Assert.assertEquals(cards.length, deckOfPlayingCards.getPlayingCardsInDeck().size());
        for(int i = 0; i<cards.length; i++) {
            stringBuilder.setLength(0);
            String cardAsProvidedText = cards[i];
            cardAsProvidedText = cardAsProvidedText.trim();
            PlayingCard correspondingPlayingCard = deckOfPlayingCards.getPlayingCardsInDeck().get(i);

            switch (correspondingPlayingCard.getSuite()) {
                case DIAMONDS:
                    stringBuilder.append(PlayingCard.CHAR_DIAMONDS);
                    break;
                case SPADES:
                    stringBuilder.append(PlayingCard.CHAR_SPADES);
                    break;
                case HEARTS:
                    stringBuilder.append(PlayingCard.CHAR_HEARTS);
                    break;
                case CLUBS:
                    stringBuilder.append(PlayingCard.CHAR_CLUBS);
                    break;
            }

            switch (correspondingPlayingCard.getValue()) {
                case 1:
                    stringBuilder.append(PlayingCard.CHAR_ACE);
                    break;
                case 11:
                    stringBuilder.append(PlayingCard.CHAR_JACK);
                    break;
                case 12:
                    stringBuilder.append(PlayingCard.CHAR_QUEEN);
                    break;
                case 13:
                    stringBuilder.append(PlayingCard.CHAR_KING);
                    break;
                default:
                    stringBuilder.append(correspondingPlayingCard.getValue());
                    break;
            }

            Assert.assertEquals(cardAsProvidedText, stringBuilder.toString());
        }
    }
}
