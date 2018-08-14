package com.finn.blackjack;

import java.util.ArrayList;
import java.util.List;

public class GameOfBlackjack {

    private final List<Player> players;

    public GameOfBlackjack() {
        players = new ArrayList<>();
        players.add(new Player("the Dealer"));
        players.add(new Player("Sam"));
    }

    public List<Player> getPlayers() {
        return players;
    }
}
