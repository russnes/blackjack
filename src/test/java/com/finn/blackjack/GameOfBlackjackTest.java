package com.finn.blackjack;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameOfBlackjackTest {

    static GameOfBlackjack gameOfBlackjack;

    @BeforeClass
    public static void init() {
        gameOfBlackjack = new GameOfBlackjack();
    }

    @Test
    public void gameHasTwoPlayers() {
        Assert.assertEquals(2, gameOfBlackjack.getPlayers().size());
    }

    @Test
    public void playersAreNamedSamAndTheDealer() {
        boolean samExisted = false;
        boolean theDealerExisted = false;
        for(Player player : gameOfBlackjack.getPlayers()) {
            if("Sam".equals(player.getName())) {
                samExisted = true;
            } else if("the Dealer".equals(player.getName())) {
                theDealerExisted = true;
            }
        }
        Assert.assertTrue(samExisted && theDealerExisted);
    }

    @Test
    public void playersAreDealtTwoCardsInitially() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        gameOfBlackjack.deal();

        Assert.assertEquals(2, gameOfBlackjack.getPlayers().get(0).getPlayingCards().size());
        Assert.assertEquals(2, gameOfBlackjack.getPlayers().get(1).getPlayingCards().size());
    }

    @Test
    public void cardsInPlayersHandsAreFromTheGameDeck() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        gameOfBlackjack.deal();

        for(Player player : gameOfBlackjack.getPlayers()) {
            for(PlayingCard playingCard : player.getPlayingCards()) {
                Assert.assertTrue(gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().contains(playingCard)
                || gameOfBlackjack.getDeckOfPlayingCards().getDealtPlayingCards().contains(playingCard));
            }
        }
    }

    @Test
    public void cardsAreDealtInitiallyInAlternatingOrder() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        List<PlayingCard> originallyOrderedPlayingCards = new ArrayList<>();
        for(int i = 0; i<4; i++) {
            originallyOrderedPlayingCards.add(gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().get(i));
        }

        gameOfBlackjack.deal();
        for(Player player : gameOfBlackjack.getPlayers()) {
            PlayingCard firstPlayingCard = player.getPlayingCards().get(0);
            PlayingCard secondPlayingCard = player.getPlayingCards().get(1);
            int indexOfFirstPlayingCard = originallyOrderedPlayingCards.indexOf(firstPlayingCard);
            int indexOfSecondPlayingCard = originallyOrderedPlayingCards.indexOf(secondPlayingCard);
            Assert.assertFalse(indexOfSecondPlayingCard == indexOfFirstPlayingCard+1);
        }
    }

    @Test
    public void samIsDealtTheFirstCard() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        PlayingCard firstPlayingCard = gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().get(0);
        gameOfBlackjack.deal();
        for(Player player : gameOfBlackjack.getPlayers()) {
            if("Sam".equals(player.getName())) {
                Assert.assertTrue(player.getPlayingCards().contains(firstPlayingCard));
            }
        }
    }

    @Test
    public void gameIdentifiesBlackjackIfFirstPlayerHasInitialHandOf21() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        Iterator<PlayingCard> playingCardIterator = gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().iterator();
        PlayingCard ace = null;
        PlayingCard ten = null;
        for(PlayingCard playingCard : gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck()) {
            if(playingCard.getPointValue() == 11) {
                ace = playingCard;
                if(ten != null) {
                    break;
                }
            } else if(playingCard.getPointValue() == 10) {
                ten = playingCard;
                if(ace != null) {
                    break;
                }
            }
        }

        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().remove(ace);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().remove(ten);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().add(0, ace);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().add(2, ten);

        gameOfBlackjack.deal();
        Assert.assertTrue(gameOfBlackjack.someoneHasBlackjack());
    }

    @Test
    public void gameIdentifiesBlackjackIfSecondPlayerHasInitialHandOf21() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        PlayingCard ace = null;
        PlayingCard ten = null;
        for(PlayingCard playingCard : gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck()) {
            if(playingCard.getPointValue() == 11) {
                ace = playingCard;
                if(ten != null) {
                    break;
                }
            } else if(playingCard.getPointValue() == 10) {
                ten = playingCard;
                if(ace != null) {
                    break;
                }
            }
        }

        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().remove(ace);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().remove(ten);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().add(1, ace);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().add(3, ten);

        gameOfBlackjack.deal();
        Assert.assertTrue(gameOfBlackjack.someoneHasBlackjack());
    }

    @Test
    public void playerWithInitial21WinsTheGame() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        PlayingCard ace = null;
        PlayingCard ten = null;
        for(PlayingCard playingCard : gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck()) {
            if(playingCard.getPointValue() == 11) {
                ace = playingCard;
                if(ten != null) {
                    break;
                }
            } else if(playingCard.getPointValue() == 10) {
                ten = playingCard;
                if(ace != null) {
                    break;
                }
            }
        }

        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().remove(ace);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().remove(ten);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().add(0, ace);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().add(2, ten);

        gameOfBlackjack.deal();
        Player winner = gameOfBlackjack.getWinner();
        Assert.assertEquals(gameOfBlackjack.getPlayers().get(0), winner);
    }

    @Test
    public void samWinsWhenBothPlayersHaveBlackjack() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        PlayingCard ace0 = null;
        PlayingCard ten0 = null;
        PlayingCard ace1 = null;
        PlayingCard ten1 = null;
        for(PlayingCard playingCard : gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck()) {
            if(playingCard.getPointValue() == 11) {
                if(ace0 == null) {
                    ace0 = playingCard;
                } else if(ace1 == null) {
                    ace1 = playingCard;
                }
            } else if(playingCard.getPointValue() == 10) {
                if(ten0 == null) {
                    ten0 = playingCard;
                } else if(ten1 == null) {
                    ten1 = playingCard;
                }
            }
        }

        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().remove(ace0);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().remove(ten0);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().remove(ace1);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().remove(ten1);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().add(0, ace0);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().add(1, ace1);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().add(2, ten0);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().add(3, ten0);

        gameOfBlackjack.deal();
        Player sam = null;
        for(Player player : gameOfBlackjack.getPlayers()) {
            if("Sam".equals(player.getName())) {
                sam = player;
            }
        }

        Assert.assertEquals(sam, gameOfBlackjack.getWinner());
    }

    @Test
    public void dealerWinsWhenBothHave22Initially() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        List<PlayingCard> aces = new ArrayList<>();
        for(PlayingCard playingCard : gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck()) {
            if(playingCard.getPointValue() == 11) {
                aces.add(playingCard);
            }
        }
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().removeAll(aces);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().addAll(0, aces);
        gameOfBlackjack.deal();

        Player theDealer = null;
        for(Player player : gameOfBlackjack.getPlayers()) {
            if("the Dealer".equals(player.getName())) {
                theDealer = player;
            }
        }

        Assert.assertEquals(theDealer, gameOfBlackjack.getWinner());
    }

    @Test
    public void samCanDrawCardsWhenNeitherPlayerHasBlackjack() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        List<PlayingCard> aces = new ArrayList<>();
        for(PlayingCard playingCard : gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck()) {
            if(playingCard.getPointValue() == 11) {
                aces.add(playingCard);
            }
        }
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().removeAll(aces);

        //all aces are put later in the shuffled deck to ensure no one has blackjack
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().addAll(6, aces);
        gameOfBlackjack.deal();

        Player sam = null;
        for(Player player : gameOfBlackjack.getPlayers()) {
            if("Sam".equals(player.getName())) {
                sam = player;
            }
        }

        gameOfBlackjack.dealCardToPlayer(sam);
    }

    @Test
    public void cardIsAddedToSamsHandWhenDealt() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        List<PlayingCard> aces = new ArrayList<>();
        for(PlayingCard playingCard : gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck()) {
            if(playingCard.getPointValue() == 11) {
                aces.add(playingCard);
            }
        }
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().removeAll(aces);

        //all aces are put later in the shuffled deck to ensure no one has blackjack
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().addAll(6, aces);
        gameOfBlackjack.deal();

        Player sam = null;
        for(Player player : gameOfBlackjack.getPlayers()) {
            if("Sam".equals(player.getName())) {
                sam = player;
            }
        }

        PlayingCard currentNextPlayingCard = gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().get(0);
        gameOfBlackjack.dealCardToPlayer(sam);
        Assert.assertTrue(sam.getPlayingCards().contains(currentNextPlayingCard));
    }

    @Test(expected = IllegalStateException.class)
    public void samCannotDrawIfHisHandScoreIsGreaterThan17() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        List<PlayingCard> tens = new ArrayList<>();
        for(PlayingCard playingCard : gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck()) {
            if(playingCard.getPointValue() == 10) {
                tens.add(playingCard);
            }
        }
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().removeAll(tens);

        //all tens are put first in the shuffled deck to ensure no one has blackjack
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().addAll(0, tens);
        gameOfBlackjack.deal();

        Player sam = null;
        for(Player player : gameOfBlackjack.getPlayers()) {
            if("Sam".equals(player.getName())) {
                sam = player;
            }
        }

        gameOfBlackjack.dealCardToPlayer(sam);
    }

    @Test(expected = IllegalStateException.class)
    public void samCannotDrawIfHisHandScoreIsExactly17() {
        GameOfBlackjack gameOfBlackjack = new GameOfBlackjack();
        List<PlayingCard> tens = new ArrayList<>();
        PlayingCard seven = null;
        for(PlayingCard playingCard : gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck()) {
            if(playingCard.getPointValue() == 10) {
                tens.add(playingCard);
            } else if(playingCard.getPointValue() == 7) {
                seven = playingCard;
            }
        }
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().removeAll(tens);

        //all tens are put first in the shuffled deck to ensure no one has blackjack, and sam has 17
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().addAll(0, tens);
        gameOfBlackjack.getDeckOfPlayingCards().getPlayingCardsInDeck().add(0, seven);
        gameOfBlackjack.deal();

        Player sam = null;
        for(Player player : gameOfBlackjack.getPlayers()) {
            if("Sam".equals(player.getName())) {
                sam = player;
            }
        }

        gameOfBlackjack.dealCardToPlayer(sam);
    }
}
