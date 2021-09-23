package database;

import elo.Player;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connection {

    static public java.sql.Connection connection;

    /**
     * Represent a connection to the database
     *
     * @throws SQLException
     */
    public Connection() throws SQLException{
        this.connection = DriverManager.getConnection(System.getenv("URL"), System.getenv("USER"),  System.getenv("PASSWORD"));
    }

    /**
     * Clear the table
     */
    public void clearTable() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM players WHERE matches >= 0");
        preparedStatement.executeUpdate();
    }

    /**
     * Know if a player is in the database or not
     *
     * @param player the player to check
     * @return true if the player is present, false otherwise
     * @throws SQLException
     */
    static public boolean playerIsPresent(Player player) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM players WHERE id = ?");
        preparedStatement.setString(1, player.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Add a player
     *
     * @param player the player to add
     * @throws SQLException
     */
    static public void addPlayer(Player player) throws SQLException {
        if(!playerIsPresent(player)){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO players (id, rating, matches, wins) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, player.getId());
            preparedStatement.setInt(2, player.getRating());
            preparedStatement.setInt(3, player.getMatches());
            preparedStatement.setInt(4, player.getWins());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Remove a player
     *
     * @param player the player to remove
     * @throws SQLException
     */
    static public void removePlayer(Player player) throws SQLException {
        if(playerIsPresent(player)){
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM players WHERE id = ?");
            preparedStatement.setString(0, player.getId());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Update a player record
     *
     * @param player the player to update
     * @throws SQLException
     */
    static public void updatePlayer(Player player) throws SQLException {
        if(playerIsPresent(player)){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE players SET rating = ?, matches = ?, wins = ? WHERE id = ?");
            preparedStatement.setInt(1, player.getRating());
            preparedStatement.setInt(2, player.getMatches());
            preparedStatement.setInt(3, player.getWins());
            preparedStatement.setString(4, player.getId());
            preparedStatement.executeUpdate();
        }
    }

}
