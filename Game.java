/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chessPairingSoftware;

/**
 *
 * @author thoma
 */
public class Game {
    
    private final int round;
    
    private final Player whitePlayer;
    private final Player blackPlayer;
    private int result;
    // 0 = unset, 1 = white, 2 = black, 3 - draw
    
    
    Game(int round, Player white, Player black) {
        this.round = round;
        whitePlayer = white;
        blackPlayer = black;
        result = 0;
    }
    
    public Player getWhitePlayer() {
        return whitePlayer;
    }
    
    public Player getBlackPlayer() {
        return blackPlayer;
    }
    
    public int getResult() {
        return result;
    }
    
    public void setResult(int n) {
        result = n;
    }
    
    public boolean hasPlayer(Player player) {
        return (player.equals(whitePlayer)) || (player.equals(blackPlayer));
    }

    public boolean hasPlayers(Player player1, Player player2) {
        return ((player1.equals(whitePlayer)) && (player2.equals(blackPlayer))) ||
                ((player1.equals(blackPlayer)) && (player2.equals(whitePlayer)));
    }
    public boolean hasResult() {
        return result != 0;
    }
    
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Game)) {
            return false;
        }
        Game otherMatch = (Game) other;
        return ((otherMatch.round == round) &&
                (otherMatch.whitePlayer.equals(whitePlayer)) &&
                (otherMatch.blackPlayer.equals(blackPlayer)));
    }

    @Override
    public String toString() {
        return "round " + round + " : " + whitePlayer + " - " + blackPlayer;
    }
}
