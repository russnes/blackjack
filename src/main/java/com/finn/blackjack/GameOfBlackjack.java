package com.finn.blackjack;

import java.util.ArrayList;
import java.util.List;

public class GameOfBlackjack {

    private Player sam;
    private Player theDealer;
    private final List<Player> players;
    private DeckOfPlayingCards deckOfPlayingCards;
    private boolean someoneHasBlackjack;
    private Player winner;

    public GameOfBlackjack() {
        someoneHasBlackjack = false;
        deckOfPlayingCards = new DeckOfPlayingCards();
        deckOfPlayingCards.shuffle();
        sam = new Player("Sam");
        theDealer = new Player("the Dealer");
        players = new ArrayList<>();
        players.add(sam);
        players.add(theDealer);
    }

    public void deal() {
        for(int i = 0; i<2; i++) {
            for(Player player : players) {
                player.getPlayingCards().add(deckOfPlayingCards.dealPlayingCard());
            }
        }

        boolean had22 = false;
        for(Player player : players) {
            if(player.getScoreOfHand() == 21) {
                someoneHasBlackjack = true;
                if(winner == null) {
                    winner = player;
                }
            }

            if(player.getScoreOfHand() == 22) {
                if(had22) {
                    winner = player;
                } else {
                    had22 = true;
                }
            }
        }
    }

    public void dealCardToPlayer(Player player) {
        if(winner != null) {
            throw new IllegalStateException("Cannot deal card when game has been won");
        }
        if(sam.equals(player)) {
            if(player.getScoreOfHand() >= 17) {
                throw new IllegalStateException("Player cannot draw when score of hand is equal to or greater than 17");
            }
        } else {
            if(sam.getScoreOfHand() <= 17) {
                throw new IllegalStateException("Dealer cannot draw before Sam has finished drawing");
            }
        }

        player.getPlayingCards().add(deckOfPlayingCards.dealPlayingCard());
        if(player.getScoreOfHand() > 21) {
            loseGameForPlayer(player);
        }
    }

    private void loseGameForPlayer(Player loser) {
        for(Player player : players) {
            if(!loser.equals(player)) {
                this.winner = player;
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
