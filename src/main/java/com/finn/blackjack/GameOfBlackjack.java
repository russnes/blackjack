package com.finn.blackjack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameOfBlackjack {

    public static final String NAME_SAM = "sam";
    public static final String NAME_DEALER = "dealer";
    private Player sam;
    private Player theDealer;
    private final List<Player> players;
    private DeckOfPlayingCards deckOfPlayingCards;
    private boolean someoneHasBlackjack;
    private Player winner;
    private boolean initialHandWasDealt;

    public GameOfBlackjack() {
        initialHandWasDealt = false;
        someoneHasBlackjack = false;
        deckOfPlayingCards = new DeckOfPlayingCards();
        deckOfPlayingCards.shuffle();
        sam = new Player(NAME_SAM);
        theDealer = new Player(NAME_DEALER);
        players = new ArrayList<>();
        players.add(sam);
        players.add(theDealer);
    }

    public void initializeWithRandomDeck() {
        deckOfPlayingCards.initiateNewShuffledDeck();
    }

    public void initializeWithLoadedDeck(String path) throws IOException {
        deckOfPlayingCards.initiateNewLoadedDeck(path);
    }

    /**
     * Deals initial hands of two cards to each player
     */
    public void deal() {
        for(int i = 0; i<2; i++) {
            for(Player player : players) {
                player.getPlayingCards().add(deckOfPlayingCards.dealPlayingCard());
            }
        }
        determineInitialHandResults();
        initialHandWasDealt = true;
    }

    private void determineInitialHandResults() {
        for(Player player : players) {
            if(player.getScoreOfHand() == 21) {
                someoneHasBlackjack = true;
                if(winner == null) {
                    winner = player;
                }
            } else if(player.getScoreOfHand() == 22) {
                if(player.equals(sam)) {
                    winner = theDealer;
                } else {
                    if(winner == null) {
                        winner = sam;
                    }
                }
            }
        }

        if(winner == null) {
            if (sam.getScoreOfHand() >= 17 && theDealer.getScoreOfHand() > sam.getScoreOfHand()) {
                winner = theDealer;
            }
        }
    }

    public void dealNextCard() {
        if(sam.getScoreOfHand()<17) {
            dealCardToPlayer(sam);
        } else {
            dealCardToPlayer(theDealer);
        }
    }

    public void dealCardToPlayer(Player player) {
        if(!initialHandWasDealt) {
            throw new IllegalStateException("Cannot draw cards before dealing initial hands!");
        }
        if(winner != null) {
            throw new IllegalStateException("Cannot deal card when game has been won");
        }
        if(sam.equals(player)) {
            if(player.getScoreOfHand() >= 17) {
                throw new IllegalStateException("Player cannot draw when score of hand is equal to or greater than 17");
            }
        } else {
            if(sam.getScoreOfHand() < 17) {
                throw new IllegalStateException("Dealer cannot draw before Sam has finished drawing");
            } else if(player.getScoreOfHand()>sam.getScoreOfHand()) {
                throw new IllegalStateException("Dealer must stop drawing after reaching higher score than Sam "
                        + theDealer.getScoreOfHand() + '/' + sam.getScoreOfHand());
            }
        }

        player.getPlayingCards().add(deckOfPlayingCards.dealPlayingCard());
        if(player.getScoreOfHand() > 21) {
            loseGameForPlayer(player);
        } else if(theDealer.equals(player)) {
            if(player.getScoreOfHand() > sam.getScoreOfHand()) {
                loseGameForPlayer(sam);
            }
        } else if(sam.equals(player)) {
            if(sam.getScoreOfHand() >= 17 && theDealer.getScoreOfHand() > sam.getScoreOfHand()) {
                loseGameForPlayer(sam);
            }
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

    public void setDeckOfPlayingCards(DeckOfPlayingCards deckOfPlayingCards) {
        this.deckOfPlayingCards = deckOfPlayingCards;
    }

    public boolean someoneHasBlackjack() {
        return someoneHasBlackjack;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getSam() {
        return sam;
    }

    public Player getTheDealer() {
        return theDealer;
    }
}
