package com.finn.blackjack;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.mockito.Mockito.*;

public class BlackjackSimulatorTest {

    @Test
    public void passingArgumentPathInitiatesGameWithParsedDeck() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("decks/example_deck").getFile());
        String pathToFile = file.getAbsolutePath();

        GameOfBlackjackBuilder mockedGameOfBlackjackBuilder = mock(GameOfBlackjackBuilder.class);

        BlackjackSimulator blackjackSimulator = new BlackjackSimulator();
        blackjackSimulator.setGameOfBlackjackBuilder(mockedGameOfBlackjackBuilder);
        blackjackSimulator.initializeGame(pathToFile);

        verify(mockedGameOfBlackjackBuilder, times(1)).createGameWithParsedDeck(pathToFile);
    }

    @Test
    public void passingNoArgumentsInitiatesGameWithRandomDeck() throws IOException {
        GameOfBlackjackBuilder mockedGameOfBlackjackBuilder = mock(GameOfBlackjackBuilder.class);

        BlackjackSimulator blackjackSimulator = new BlackjackSimulator();
        blackjackSimulator.setGameOfBlackjackBuilder(mockedGameOfBlackjackBuilder);
        blackjackSimulator.initializeGame();

        verify(mockedGameOfBlackjackBuilder, times(1)).createGameWithRandomDeck();
    }

    @Test
    public void afterInitiatingGameCommences() throws IOException {
        BlackjackSimulationRunner blackjackSimulationRunner = new BlackjackSimulationRunner();
        BlackjackSimulator mockedBlackjackSimulator = mock(BlackjackSimulator.class);
        blackjackSimulationRunner.setBlackjackSimulator(mockedBlackjackSimulator);
        blackjackSimulationRunner.simulateBlackjackGame();

        verify(mockedBlackjackSimulator, times(1)).playGame();
    }

    @Test
    public void gameIsPlayedUntilDealerBreaks() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("decks/dealer_breaks").getFile());
        String pathToFile = file.getAbsolutePath();
        BlackjackSimulationRunner blackjackSimulationRunner = new BlackjackSimulationRunner();
        blackjackSimulationRunner.simulateBlackjackGame(pathToFile);
        Player winner = blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getWinner();
        Assert.assertEquals(winner, blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getSam());
        Assert.assertTrue(blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getTheDealer().getScoreOfHand()>21);
    }

    @Test
    public void gameIsPlayedUntilDealerWinsByDrawing() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("decks/dealer_wins_by_drawing").getFile());
        String pathToFile = file.getAbsolutePath();
        BlackjackSimulationRunner blackjackSimulationRunner = new BlackjackSimulationRunner();
        blackjackSimulationRunner.simulateBlackjackGame(pathToFile);
        Player winner = blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getWinner();
        Assert.assertEquals(winner, blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getTheDealer());
        Assert.assertTrue(blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getTheDealer().getScoreOfHand()<=21);
        Assert.assertTrue(blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getTheDealer().getScoreOfHand()>
            blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getSam().getScoreOfHand());
    }

    @Test
    public void gameIsPlayedUntilSamBreaks() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("decks/sam_breaks_by_drawing").getFile());
        String pathToFile = file.getAbsolutePath();
        BlackjackSimulationRunner blackjackSimulationRunner = new BlackjackSimulationRunner();
        blackjackSimulationRunner.simulateBlackjackGame(pathToFile);
        Player winner = blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getWinner();
        Assert.assertEquals(winner, blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getTheDealer());
        Assert.assertTrue(blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getTheDealer().getScoreOfHand()<21);
        Assert.assertTrue(blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getSam().getScoreOfHand()>21);
    }

    @Test
    public void whenGameIsFinishedTheNameOfTheWinnerIsPrinted() throws IOException {
        BlackjackSimulationRunner blackjackSimulationRunner = new BlackjackSimulationRunner();
        Logger mockedLogger = mock(Logger.class);
        blackjackSimulationRunner.getBlackjackSimulator().setLogger(mockedLogger);
        blackjackSimulationRunner.simulateBlackjackGame();
        Player winner = blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getWinner();
        verify(mockedLogger, times(1)).print(winner.getName());
    }

    @Test
    public void whenGameIsFinishedSamsHandIsPrinted() throws IOException {
        BlackjackSimulationRunner blackjackSimulationRunner = new BlackjackSimulationRunner();
        Logger mockedLogger = mock(Logger.class);
        blackjackSimulationRunner.getBlackjackSimulator().setLogger(mockedLogger);
        blackjackSimulationRunner.simulateBlackjackGame();
        Player sam = blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getSam();
        String samsHand = sam.getHandString();
        String messageWithPlayerName = sam.getName() + ": " + samsHand;
        verify(mockedLogger, times(1)).print(messageWithPlayerName);
    }

    @Test
    public void whenGameIsFinishedTheDealersHandIsPrinted() throws IOException {
        BlackjackSimulationRunner blackjackSimulationRunner = new BlackjackSimulationRunner();
        Logger mockedLogger = mock(Logger.class);
        blackjackSimulationRunner.getBlackjackSimulator().setLogger(mockedLogger);
        blackjackSimulationRunner.simulateBlackjackGame();
        Player theDealer = blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getTheDealer();
        String theDealersHand = theDealer.getHandString();
        String messageWithPlayerName = theDealer.getName() + ": " + theDealersHand;
        verify(mockedLogger, times(1)).print(messageWithPlayerName);
    }

    @Test
    public void nameOfWinnerIsPrintedBeforeSamsHand() throws IOException {
        BlackjackSimulationRunner blackjackSimulationRunner = new BlackjackSimulationRunner();
        Logger mockedLogger = mock(Logger.class);
        blackjackSimulationRunner.getBlackjackSimulator().setLogger(mockedLogger);
        blackjackSimulationRunner.simulateBlackjackGame();
        Player sam = blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getSam();
        String samsHand = sam.getHandString();
        String messageWithPlayerName = sam.getName() + ": " + samsHand;

        InOrder inOrder = inOrder(mockedLogger);
        inOrder.verify(mockedLogger).print(blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getWinner().getName());
        inOrder.verify(mockedLogger).print(messageWithPlayerName);
    }

    @Test
    public void samsHandIsPrintedBeforeDealersHand() throws IOException {
        BlackjackSimulationRunner blackjackSimulationRunner = new BlackjackSimulationRunner();
        Logger mockedLogger = mock(Logger.class);
        blackjackSimulationRunner.getBlackjackSimulator().setLogger(mockedLogger);
        blackjackSimulationRunner.simulateBlackjackGame();
        Player sam = blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getSam();
        String samsHand = sam.getHandString();
        String messageWithSamsName = sam.getName() + ": " + samsHand;

        Player theDealer = blackjackSimulationRunner.getBlackjackSimulator().getGameOfBlackjack().getTheDealer();
        String theDealersHand = theDealer.getHandString();
        String messageWithDealersName = theDealer.getName() + ": " + theDealersHand;

        InOrder inOrder = inOrder(mockedLogger);
        inOrder.verify(mockedLogger).print(messageWithSamsName);
        inOrder.verify(mockedLogger).print(messageWithDealersName);
    }
}
