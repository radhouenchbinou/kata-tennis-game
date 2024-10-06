package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the TennisGame class.
 */
public class TennisGameTest {

    private TennisGame tennisGame;
    private final String lineSeparator = System.lineSeparator();
    private ByteArrayOutputStream outContent;

    /**
     *  The testCases map contains different game sequences as keys and the expected output as values.
     *  The expected output is the score of the game after each point in the game sequence.
     *  The test cases cover different scenarios, such as a game with multiple deuces and a game with a player winning.
     */
    private final Map<String, String> testCases = Map.of(
            "ABABABBAABBB", "Player A: 15 - Player B: 0" + lineSeparator +
                    "Player A: 15 - Player B: 15" + lineSeparator +
                    "Player A: 30 - Player B: 15" + lineSeparator +
                    "Player A: 30 - Player B: 30" + lineSeparator +
                    "Player A: 40 - Player B: 30" + lineSeparator +
                    "Player A: 40 - Player B: 40" + lineSeparator +
                    "Deuce" + lineSeparator +
                    "Player A: 40 - Player B: 40" + lineSeparator +
                    "Advantage player: B" + lineSeparator +
                    "Player A: 40 - Player B: 40" + lineSeparator +
                    "Deuce" + lineSeparator +
                    "Player A: 40 - Player B: 40" + lineSeparator +
                    "Advantage player: A" + lineSeparator +
                    "Player A: 40 - Player B: 40" + lineSeparator +
                    "Deuce" + lineSeparator +
                    "Player A: 40 - Player B: 40" + lineSeparator +
                    "Advantage player: B" + lineSeparator +
                    "Player B wins the game" + lineSeparator,
            "ABABAA", "Player A: 15 - Player B: 0" + lineSeparator +
                    "Player A: 15 - Player B: 15" + lineSeparator +
                    "Player A: 30 - Player B: 15" + lineSeparator +
                    "Player A: 30 - Player B: 30" + lineSeparator +
                    "Player A: 40 - Player B: 30" + lineSeparator +
                    "Player A wins the game" + lineSeparator
    );

    /**
     * Sets up the test environment before each test.
     * Initializes a new instance of TennisGame.
     */
    @Before
    public void setUp() {
        tennisGame = new TennisGame();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Resets the standard output after each test.
     */
    @After
    public void tearDown() {
        System.setOut(System.out);
    }

    /**
     * Tests the computeScore method of the TennisGame class.
     * Verifies the output for different sequences of points.
     */
    @Test
    public void testComputeScore() {
        testCases.forEach((gameSequence, expectedOutput) -> {
            tennisGame.computeScore(gameSequence);
            assertEquals(expectedOutput, outContent.toString());
            tennisGame.resetGame();
            outContent.reset();
        });
    }
}
