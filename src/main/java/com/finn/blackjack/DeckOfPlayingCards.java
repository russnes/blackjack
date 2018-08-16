package com.finn.blackjack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    }

    public void initiateNewLoadedDeck(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String inputText = new String(bytes);
        String[] cards = inputText.split(",");
        for(String card : cards) {
            card = card.trim();
            PlayingCard playingCard = new PlayingCard(card);
            playingCardsInDeck.add(playingCard);
        }
    }

    public void initiateNewShuffledDeck() {
        playingCardsInDeck.clear();
        dealtPlayingCards.clear();
        for(PlayingCard.Suite suite : PlayingCard.Suite.values()) {
            for(int i = 1; i<14; i++) {
                playingCardsInDeck.add(new PlayingCard(i, suite));
            }
        }
        shuffle();
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
