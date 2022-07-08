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
public class Player implements Comparable<Player> {
    
    private int id;
    private String name;
    private int rating;
    //private boolean provisionalRating;
    
    private int byeRound;
    
    public double currentScore;
    
    ArrayList<Result> results = new ArrayList<>();
    
    /**
     * 
     * @param a - the ID of the player
     * @param b - the name of the player
     * @param c - the rating of the player
     */
    Player (int a, String b, int c) {
        id = a;
        name = b;
        rating = c;
        
        
        currentScore = 0.0;
        
        byeRound = -1;
    }
    
    Player (int a, String b, int c, ArrayList<Result> results) {
        this(a, b, c);
        
        this.results = results;
        
        
    }
    
    public int getID() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getRating() {
        return rating;
    }
    
    public int getByeRound() {
        return byeRound;
    }
    
    public void setByeRound(int n) {
        byeRound = n;
    }
    
    @Override
    public int compareTo(Player other) {
        if (currentScore > other.currentScore || (currentScore == other.currentScore && rating > other.rating)) {
            return 1;
        }
        if (currentScore < other.currentScore || (currentScore == other.currentScore && rating < other.rating)) {
            return -1;
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    static class Result {
        int intResult;
        int opponentID;
        int color;

        Result(int a, int b, int c) {
            intResult = a;
            opponentID = b;
            color = c;
        }
        
        @Override
        public String toString() {
            String str = "";
            
            if((intResult == 1 && color == 1) || (intResult == 2 && color == 2)) {
                str += "+";
            } else if((intResult == 2 && color == 1) || (intResult == 1 && color == 2)) {
                str += "-";
            } else {
                str += "=";
            }
            
            return str;
        }
    }
    
    public void updateResult(int result, int opponentID, int color) {
        
        if(!(color == 1 || color == 2)) {
            throw new IllegalArgumentException("Color is wrong");
        }
        if(!(result == 1 || result == 2 || result == 3)) {
            throw new IllegalArgumentException("Result is invalid or has not been filled in yet.");
        }
        results.add(new Result(result, opponentID, color));
        
        switch (result) {
            case 1:
                if(color == 1) currentScore += 1;
                break;
            case 2:
                if(color == 2) currentScore += 1;
                break;
            default:
                currentScore += 0.5;
                break;
        }
    }
}
