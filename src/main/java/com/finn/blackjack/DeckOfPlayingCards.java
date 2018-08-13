package com.finn.blackjack;

import java.util.ArrayList;
import java.util.List;

public class DeckOfPlayingCards {

    List<PlayingCard> playingCards;

    public DeckOfPlayingCards() {
        playingCards = new ArrayList<>();
        for(int i = 0; i<52; i++) {
            playingCards.add(new PlayingCard(13));
        }
    }

    List<PlayingCard> getPlayingCards() {
        return playingCards;
    }

}
