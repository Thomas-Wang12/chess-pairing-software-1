/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package chessPairingSoftware;

import java.util.*;

/**
 *
 * @author thoma
 */
public class Round {
    private int roundNumber;
    
    private int currentRoundNumber;
    
    private ArrayList<Game> games = new ArrayList<Game>();    
    
    Round(int round) {
        roundNumber = round;
    }
    
    public ArrayList<Game> getMatches() {
        return games;
    }
    
    Game addMatch(Player player1, Player player2) {
        for (Game match : games) {
            if (match.getWhitePlayer().equals(player1)) {
                throw new IllegalArgumentException("Could not add match " + player1 + " - " + player2 + " : player 1 already matches");
            }
            if (match.getBlackPlayer().equals(player1)) {
                throw new IllegalArgumentException("Could not add match " + player1 + " - " + player2 + " : player 2 already matches");
            }
        }
        Game match = new Game(roundNumber, player1, player2);
        games.add(match);
        return match;
    }

    boolean hasMatchForPlayer(Player player) {
        for (Game match : games) {
            if (match.hasPlayer(player)) {
                return true;
            }
        }
        return false;
    }

    boolean removeMatchWithPlayer(Player player) {
        Game foundMatch = null;
        for (Game match : games) {
            if (match.hasPlayer(player)) {
                foundMatch = match;
                break;
            }
        }
        if (foundMatch != null) {
            return games.remove(foundMatch);
        }
        return false;
    }

    /**
     * @return true if all the round matches have assigned results
     */
    public boolean hasAllResults() {
        for (Game match : games) {
            if (!match.hasResult()) {
                return false;
            }
        }
        return true;
    }
    
}
