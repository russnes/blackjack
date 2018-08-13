package com.finn.blackjack;

import java.util.ArrayList;
import java.util.List;

public class DeckOfPlayingCards {

    private List<PlayingCard> playingCards;

    public DeckOfPlayingCards() {
        playingCards = new ArrayList<>();
        for(PlayingCard.Suite suite : PlayingCard.Suite.values()) {
            for(int i = 1; i<14; i++) {
                playingCards.add(new PlayingCard(i, suite));
            }
        }
    }

    List<PlayingCard> getPlayingCards() {
        return playingCards;
    }

}
