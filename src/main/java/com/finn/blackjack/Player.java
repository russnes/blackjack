package com.finn.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private final List<PlayingCard> playingCards;

    public Player(String name) {
        this.name = name;
        playingCards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<PlayingCard> getPlayingCards() {
        return playingCards;
    }
}
