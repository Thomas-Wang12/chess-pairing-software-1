/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chessPairingSoftware;
import chessPairingSoftware.Player.Result;
import java.util.*;

/**
 *
 * @author Thomas Wang
 */
public class Tournament {
    private int numberOfRounds;
    
    private int currentRound;
    
    private int numberOfPlayers;
    
    public Round[] rounds;
    
    public ArrayList<Player> players;
    
    private HashMap<Player, Integer> standings = new HashMap<Player, Integer>();
    
    private int minimumScoreDistance = Integer.MAX_VALUE;
    
    private ArrayList<Player> pairing = new ArrayList<Player>();

    /**
     *
     * @param numberOfRounds
     * @param numberOfPlayers
     */
    Tournament(int numberOfRounds, int numberOfPlayers) {
        this.numberOfRounds = numberOfRounds;
        
        rounds = new Round[numberOfRounds];
        
        players = new ArrayList<Player>();        
        
        this.numberOfPlayers = numberOfPlayers;
        
        currentRound = 0;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    
    public int getNumberOfRounds() {
        return numberOfRounds;
    }
    
    public void setPlayer(int index, Player playerToSet) {
        players.set(index, playerToSet);
    }
    
    public int getCurrentRound() {
        return currentRound;
    }
    
    public Round getCurrentRoundData() {
        return rounds[currentRound - 1];
    }
    
    public void restart() {
        minimumScoreDistance = Integer.MAX_VALUE;
        pairing.clear();
        
    }
    
    public Round pair() {
        currentRound++;
        
        if(currentRound > numberOfRounds) {
            throw new IllegalArgumentException("Tournament ended after " + rounds + " rounds");
        }
        /*
        if(currentRound != 0 && !rounds[currentRound - 1].hasAllResults()) {
            throw new IllegalStateException("Round " + (currentRound - 1) + " results were not set yet");
        }
        */
        Round matching = getNextRoundMatching();
        rounds[currentRound - 1] = matching;
        return matching;
    }
    
    private Round getNextRoundMatching() {

        // sort the players based on their score
        ArrayList<Player> sortedPlayers = new ArrayList<>(players);
        
        //System.out.println("Trigger " + sortedPlayers.get(0).getName());
        Collections.sort(sortedPlayers);
        int byePlayerIndex = -1;
        Round newMatching = new Round(currentRound);
        
        Random rand = new Random();

        if ((numberOfPlayers % 2) == 1) {
            // choose a bye player
            int index = numberOfPlayers - 1;
            while (index >= 0) {
                Player player = sortedPlayers.get(index);
                if (player.getByeRound() == 0) {
                    player.setByeRound(currentRound);
                    byePlayerIndex = index;
                    break;
                }
                index--;
            }
        }
        
        recursivelyGenerateAllPairings(sortedPlayers, new ArrayList());
        
        for(int i = 0; i < players.size(); i+= 2) {
            newMatching.addMatch(pairing.get(i), pairing.get(i+1));
        }
        
        return newMatching;
    }
    
    public int evaluate(ArrayList<Player> pairing) {
        int sum = 0;
        
        for(int i = 0; i < pairing.size(); i += 2) {
            Player playerOne = pairing.get(i);
            Player playerTwo = pairing.get(i+1);
            int idOne = playerOne.getID();
            int idTwo = playerTwo.getID();
            
            for(Result j : playerOne.results) {
                if(j.opponentID == idTwo) {
                    //Invalid Pairing
                    
                    sum += 1000000;
                    
                    //If there are no valid pairings, it will pick one that fits best
                }
            }
            
            sum += 1000 * (int)Math.abs((playerOne.currentScore - playerTwo.currentScore) * 2);
            
            sum -= (int)Math.abs(playerOne.getRating() - playerTwo.getRating()) / 100;
        }
        
        return sum;
    }
    
    public void recursivelyGenerateAllPairings(ArrayList<Player> sortedPlayers, ArrayList<Player> currentPlayers) {
        if(currentPlayers.size() == sortedPlayers.size()) {
            int c = evaluate(currentPlayers);
            
            if(c < minimumScoreDistance) {
                minimumScoreDistance = c;
                pairing = currentPlayers;
            }
            return;
        }
        
        for(int i = 0; i < currentPlayers.size(); i++) {
            
            ArrayList<Player> newArrayList = (ArrayList<Player>) currentPlayers.clone();
            
            newArrayList.add(i, sortedPlayers.get(currentPlayers.size()));
            
            recursivelyGenerateAllPairings(sortedPlayers, newArrayList);
                    
        }
        
        
        ArrayList<Player> newArrayList2 = (ArrayList<Player>) currentPlayers.clone();
        
        newArrayList2.add(sortedPlayers.get(currentPlayers.size()));
        
        recursivelyGenerateAllPairings(sortedPlayers, newArrayList2);
    }
}
