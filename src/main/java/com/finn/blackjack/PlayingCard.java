package com.finn.blackjack;

public class PlayingCard {

    public enum Suite {
        HEARTS,
        SPADES,
        DIAMONDS,
        CLUBS
    }

    private int value;
    private Suite suite;

    public PlayingCard(int value, Suite suite) {
        if(value <= 0) {
            throw new IllegalArgumentException("Value must be greater than 0!");
        } else if(value > 13) {
            throw new IllegalArgumentException("Value must be less than 14!");
        }
        if(suite == null) {
            throw new IllegalArgumentException("suite cannot be null!");
        }
        this.value = value;
        this.suite = suite;
    }

    public int getValue() {
        return value;
    }

    public int getPointValue() {
        if(value == 1) {
            return 11;
        } else if(value <= 10) {
            return value;
        } else {
            return 10;
        }
    }

    public Suite getSuite() {
        return suite;
    }
}
