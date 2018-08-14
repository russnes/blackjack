package com.finn.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DeckOfPlayingCards {

    private final List<PlayingCard> playingCardsInDeck;
    private final List<PlayingCard> dealtPlayingCards;
    private boolean shuffled;

    public DeckOfPlayingCards() {
        this.shuffled = false;
        playingCardsInDeck = new ArrayList<>();
        dealtPlayingCards = new ArrayList<>();
        for(PlayingCard.Suite suite : PlayingCard.Suite.values()) {
            for(int i = 1; i<14; i++) {
                playingCardsInDeck.add(new PlayingCard(i, suite));
            }
        }
    }

    public List<PlayingCard> getPlayingCardsInDeck() {
        return playingCardsInDeck;
    }

    public List<PlayingCard> getDealtPlayingCards() {
        return dealtPlayingCards;
    }

    public void shuffle(long seed) {
        Random random = new Random(seed);
        Collections.shuffle(playingCardsInDeck, random);
        shuffled = true;
    }

    public void shuffle() {
        shuffle(System.currentTimeMillis());
    }

    public PlayingCard dealPlayingCard() {
        if(!shuffled) {
            throw new IllegalStateException("Deck must be shuffled before dealing a new Playing Card!");
        } else {
            PlayingCard dealtPlayingCard = playingCardsInDeck.remove(0);
            dealtPlayingCards.add(dealtPlayingCard);
            return dealtPlayingCard;
        }
    }

}
