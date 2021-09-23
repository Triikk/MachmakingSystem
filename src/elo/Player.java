package elo;

import java.sql.SQLException;
import java.util.UUID;
import database.Connection;

public class Player {

    static int DEFAULT_RATING = 400, DEFAULT_MATCHES = 0, DEFAULT_WINS = 0;

    private String id;
    private int rating, matches, wins;
    private boolean isInQueue = false;

    /**
     * Generates a player from scratch
     */
    public Player() throws SQLException {
        this.id = UUID.randomUUID().toString();
        this.rating = Player.DEFAULT_RATING;
        this.matches = Player.DEFAULT_MATCHES;
        this.wins = Player.DEFAULT_WINS;
        Connection.addPlayer(this);
    }

    /**
     * Generates a player from a template
     *
     * @param id the UUID obj generated and converted to string
     * @param rating the rating
     * @param matches the matches
     * @param wins the wins
     */
    public Player(String id, int rating, int matches, int wins){
        this.id = id;
        this.rating = rating;
        this.matches = matches;
        this.wins = wins;
    }

    /**
     * Generates a player from a given rating
     */
    public Player(int rating) throws SQLException {
        this.id = UUID.randomUUID().toString();
        this.rating = rating;
        this.matches = Player.DEFAULT_MATCHES;
        this.wins = Player.DEFAULT_WINS;
        Connection.addPlayer(this);
    }

    public String getId(){
        return this.id;
    }

    public int getRating(){
        return this.rating;
    }

    public int getMatches(){
        return this.matches;
    }

    public int getWins(){
        return this.wins;
    }

    public boolean isInQueue(){
        return this.isInQueue;
    }

    public void setId(String newId){
        this.id = newId;
    }

    public void setRating(int newRating){
        this.rating = newRating;
    }

    public void setMatches(int newMatches){
        this.matches = newMatches;
    }

    public void setWins(int newWins){
        this.wins = newWins;
    }

    /**
     * Queue a player
     */
    public void queue(){
        this.isInQueue = true;
    }

    /**
     * Dequeue a player
     */
    public void dequeue(){
        this.isInQueue = false;
    }

    /**
     * Update the player's object information both in the class and database
     *
     * @param newRating the new rating the user got
     * @throws SQLException
     */
    public void hasWon(int newRating) throws SQLException {
        this.rating = newRating;
        this.matches += 1;
        this.wins += 1;
        Connection.updatePlayer(this);
    }

    /**
     * Update the player's object information both in the class and database
     *
     * @param newRating the new rating the user got
     * @throws SQLException
     */
    public void hasLost(int newRating) throws SQLException {
        this.rating = newRating;
        this.matches += 1;
        Connection.updatePlayer(this);
    }

    @Override
    public String toString(){
        return "Id: " + this.id + "\nRating: " + this.rating + "\nMatches: " + this.matches + "\nWins: " + this.wins + "\n";
    }

    /**
     * Determine if a player is equals to another
     *
     * @param player the player you want to confront
     * @return true if the object id is equal to the player id
     */
    public boolean equals(Player player){
        return this.id == player.id;
    }
}
