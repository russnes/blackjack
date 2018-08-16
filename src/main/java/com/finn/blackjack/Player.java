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
        StringBuilder stringBuilderSingleCard = new StringBuilder();
        StringBuilder stringBuilderFullHand = new StringBuilder();
        for(int i = 0; i<playingCards.size(); i++) {
            PlayingCard playingCard = playingCards.get(i);
            stringBuilderFullHand.append(playingCard.getTextRepresentationOfCard(stringBuilderSingleCard));
            if(i<playingCards.size()-1) {
                stringBuilderFullHand.append(", ");
            }
            stringBuilderSingleCard.setLength(0);
        }
        return stringBuilderFullHand.toString();
    }

    public String getName() {
        return name;
    }

    public List<PlayingCard> getPlayingCards() {
        return playingCards;
    }
}
