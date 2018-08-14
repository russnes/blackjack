package com.finn.blackjack;

import java.util.ArrayList;
import java.util.List;

public class GameOfBlackjack {

    private final List<Player> players;
    private DeckOfPlayingCards deckOfPlayingCards;

    public GameOfBlackjack() {
        deckOfPlayingCards = new DeckOfPlayingCards();
        players = new ArrayList<>();
        players.add(new Player("the Dealer"));
        players.add(new Player("Sam"));
    }

    public void deal() {
        deckOfPlayingCards.shuffle();
        for(Player player : players) {
            player.getPlayingCards().add(deckOfPlayingCards.dealPlayingCard());
            player.getPlayingCards().add(deckOfPlayingCards.dealPlayingCard());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public DeckOfPlayingCards getDeckOfPlayingCards() {
        return deckOfPlayingCards;
    }
}
