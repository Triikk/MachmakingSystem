import database.Connection;
import elo.*;
import matchmaking.Queue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Program {

    // Test the program with 10 players with a random rating
    static public void main(String[] args) throws SQLException {
        Connection connection = new Connection();
        connection.clearTable();
        Random random = new Random();
        ArrayList<Integer> randomRatings = new ArrayList<Integer>();
        ArrayList<Player> players = new ArrayList<Player>();

        for(int i=0; i<10; i++){
            // generates a random integer between 300 and 600
            int randomInt = random.nextInt(600-300) + 300;
            randomRatings.add((Integer)randomInt);
            Player player = new Player(randomInt);
            players.add(player);
        }

        Queue queue = new Queue(players);
    }
}
