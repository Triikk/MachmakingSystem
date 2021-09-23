package elo;

import java.lang.Math;

public class Elo {

    private final static int k = 32;
    private final static double treshold = 0.40;

    /**
     * Calculates the win probability of a player based on his rating and his opponent's rating
     *
     * @param player the player
     * @param opponent the oppponent
     * @return the win probability in a 0 to 1 format (win% / 100)
     */
    static public double getWinProbability(Player player, Player opponent){
        return 1.0 / (1.0 + Math.pow(10, ((opponent.getRating()) - player.getRating()) / 400.0));
    }

    /**
     * Decide if two players are matchable based on the treshold value
     *
     * @param player the first player
     * @param opponent the second player
     * @return true if the lowest win probability is greater than treshold
     */
    static public boolean areMatchable(Player player, Player opponent){
        double playerWinProbability = getWinProbability(player, opponent);
        double opponentWinProbability = 1.0 - playerWinProbability;

        if(playerWinProbability>Elo.treshold && opponentWinProbability>Elo.treshold){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Calculates the new rating of the player after a match
     *
     * @param player the player
     * @param winProbability the win probability the player had in the match
     * @param hasWonBool true if the player has won, false if he has loss
     * @return the new player rating
     */
    static public int getNewRating(Player player, double winProbability, boolean hasWonBool){
        double hasWon = hasWonBool ? 1.0 : 0.0;
        return (int)(Math.round((player.getRating() + (k * (hasWon - winProbability)))));
    }
}
