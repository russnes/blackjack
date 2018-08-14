package com.finn.blackjack;

import java.util.ArrayList;
import java.util.List;

public class GameOfBlackjack {

    private final List<Player> players;
    private DeckOfPlayingCards deckOfPlayingCards;

    public GameOfBlackjack() {
        deckOfPlayingCards = new DeckOfPlayingCards();
        deckOfPlayingCards.shuffle();
        players = new ArrayList<>();
        players.add(new Player("the Dealer"));
        players.add(new Player("Sam"));
    }

    public void deal() {
        for(int i = 0; i<2; i++) {
            for(Player player : players) {
                player.getPlayingCards().add(deckOfPlayingCards.dealPlayingCard());
            }
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public DeckOfPlayingCards getDeckOfPlayingCards() {
        return deckOfPlayingCards;
    }
}
