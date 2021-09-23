package matchmaking;

import elo.Elo;
import elo.Player;

import java.sql.SQLException;
import java.util.Random;

public class Match {

    static public void play(Player player1, Player player2) throws SQLException {

        double Player1WinProbability = Elo.getWinProbability(player1, player2);
        double Player2WinProbability = 1.0 - Player1WinProbability;

        System.out.println(player1.getId() + ": " + String.format("%.4g", (Player1WinProbability * 100)) + "%" + " (" + player1.getRating() + ")" + " vs " + player2.getId() + ": " + String.format("%.4g", (Player2WinProbability * 100)) + "%" + " (" + player2.getRating() + ")");

        Random random = new Random();
        boolean Player1Wins = random.nextBoolean();

        int Player1NewRating = Elo.getNewRating(player1, Player1WinProbability, Player1Wins);
        int Player2NewRating = Elo.getNewRating(player2, Player2WinProbability, !Player1Wins);

        if(Player1Wins){
            player1.hasWon(Player1NewRating);
            player2.hasLost(Player2NewRating);
        }
        else{
            player1.hasLost(Player1NewRating);
            player2.hasWon(Player2NewRating);
        }
    }
}
