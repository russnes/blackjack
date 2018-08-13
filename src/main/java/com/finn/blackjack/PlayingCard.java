package com.finn.blackjack;

public class PlayingCard {
    private int value;

    public PlayingCard(int value) {
        if(value <= 0) {
            throw new IllegalArgumentException("Value must be greater than 0!");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
