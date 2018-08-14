package com.finn.blackjack;

import java.util.ArrayList;
import java.util.List;

public class GameOfBlackjack {

    private final List<Player> players;
    private DeckOfPlayingCards deckOfPlayingCards;
    private boolean someoneHasBlackjack;
    private Player winner;

    public GameOfBlackjack() {
        someoneHasBlackjack = false;
        deckOfPlayingCards = new DeckOfPlayingCards();
        deckOfPlayingCards.shuffle();
        players = new ArrayList<>();
        players.add(new Player("Sam"));
        players.add(new Player("the Dealer"));
    }

    public void deal() {
        for(int i = 0; i<2; i++) {
            for(Player player : players) {
                player.getPlayingCards().add(deckOfPlayingCards.dealPlayingCard());
            }
        }

        for(Player player : players) {
            if(player.getScoreOfHand() == 21) {
                someoneHasBlackjack = true;
                if(winner == null) {
                    winner = player;
                }
            }
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public DeckOfPlayingCards getDeckOfPlayingCards() {
        return deckOfPlayingCards;
    }

    public boolean someoneHasBlackjack() {
        return someoneHasBlackjack;
    }

    public Player getWinner() {
        return winner;
    }
}
