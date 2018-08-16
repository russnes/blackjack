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

    public int getScoreOfHand() {
        int score = 0;
        for(PlayingCard playingCard : playingCards) {
            score += playingCard.getPointValue();
        }
        return score;
    }

    public String getHandString() {
        return null;
    }

    public String getName() {
        return name;
    }

    public List<PlayingCard> getPlayingCards() {
        return playingCards;
    }
}
