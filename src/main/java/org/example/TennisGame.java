package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * The TennisGame class simulates a tennis game, tracking the scores of two players (A and B).
 * It provides methods to compute the score based on a sequence of points, reset the game,
 * and print the current score.
 */
public class TennisGame {
    private final int[] tennisScores = {0, 15, 30, 40};
    private final Map<Character, Integer> playersScores = new HashMap<>(Map.of('A', 0, 'B', 0));
    private char gameAdvantage = 'D';

    /**
     * Computes the score based on the given game sequence.
     *
     * @param gameSequence the sequence of points (e.g., "ABABAA")
     */
    public void computeScore(String gameSequence) {
        for (char player : gameSequence.toCharArray()) {
            updatePlayerScore(player);
            if (checkWinCondition()) {
                break;
            }
            printCurrentScore();
        }
    }

    /**
     * Resets the game to the initial state.
     */
    public void resetGame() {
        playersScores.put('A', 0);
        playersScores.put('B', 0);
        gameAdvantage = 'D';
    }

    /**
     * Updates the score for a given player.
     *
     * @param player the player ('A' or 'B')
     */
    private void updatePlayerScore(char player) {
        int pScore = playersScores.get(player) + 1;
        if (isDeuce()) {
            handleDeuce(player, pScore);
        } else {
            playersScores.put(player, pScore);
        }
    }

    /**
     * Checks if the current score is deuce.
     *
     * @return true if the score is deuce, false otherwise
     */
    private boolean isDeuce() {
        return tennisScores[playersScores.get('A')] == 40 && tennisScores[playersScores.get('B')] == 40;
    }

    /**
     * Handles the deuce situation.
     *
     * @param player the player ('A' or 'B')
     * @param pScore the player's score
     */
    private void handleDeuce(char player, int pScore) {
        if (gameAdvantage == 'D') {
            gameAdvantage = player;
        } else if (gameAdvantage == player) {
            playersScores.put(player, pScore);
        } else {
            gameAdvantage = 'D';
        }
    }

    /**
     * Checks if any player has won the game.
     *
     * @return true if a player has won, false otherwise
     */
    private boolean checkWinCondition() {
        if (playersScores.get('A') == 4) {
            System.out.println("Player A wins the game");
            return true;
        } else if (playersScores.get('B') == 4) {
            System.out.println("Player B wins the game");
            return true;
        }
        return false;
    }

    /**
     * Prints the current score of the game.
     */
    private void printCurrentScore() {
        System.out.println("Player A: " + tennisScores[playersScores.get('A')] + " - Player B: " + tennisScores[playersScores.get('B')]);
        if (isDeuce()) {
            if (gameAdvantage == 'D') {
                System.out.println("Deuce");
            } else {
                System.out.println("Advantage player: " + gameAdvantage);
            }
        }
    }
}
