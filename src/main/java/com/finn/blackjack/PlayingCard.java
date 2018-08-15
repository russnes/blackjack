package com.finn.blackjack;

public class PlayingCard {

    public static final char CHAR_HEARTS = 'H';
    public static final char CHAR_SPADES = 'S';
    public static final char CHAR_DIAMONDS = 'D';
    public static final char CHAR_CLUBS = 'C';
    public static final char CHAR_ACE = 'A';
    public static final char CHAR_KING = 'K';
    public static final char CHAR_QUEEN = 'Q';
    public static final char CHAR_JACK = 'J';

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

    public PlayingCard(String definition) {
        char suiteChar = definition.charAt(0);
        switch (suiteChar) {
            case CHAR_DIAMONDS:
                suite = Suite.DIAMONDS;
                break;
            case CHAR_CLUBS:
                suite = Suite.CLUBS;
                break;
            case CHAR_HEARTS:
                suite = Suite.HEARTS;
                break;
            case CHAR_SPADES:
                suite = Suite.SPADES;
                break;
                default:
                    throw new IllegalArgumentException("Trying to parse card with invalid suite!");
        }

        char valueChar = definition.charAt(1);
        switch (valueChar) {
            case CHAR_ACE:
                value = 1;
                break;
            case CHAR_KING:
                value = 13;
                break;
            case CHAR_QUEEN:
                value = 12;
                break;
            case CHAR_JACK:
                value = 11;
                break;
                default:
                    value = Integer.valueOf(definition.substring(1));
        }
    }

    public String getTextRepresentationOfCard(StringBuilder stringBuilder) {
        switch (suite) {
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

        switch (value) {
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
                stringBuilder.append(value);
                break;
        }
        return stringBuilder.toString();
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
