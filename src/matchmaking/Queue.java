package matchmaking;

import elo.Elo;
import elo.Player;

import java.sql.SQLException;
import java.util.ArrayList;

public class Queue {

    ArrayList<Player> queue;

    /**
     * Create a queue from a given arraylist of players and automatically find a match for them
     *
     * @param players the list of players
     * @throws SQLException
     */
    public Queue(ArrayList<Player> players) throws SQLException {
        this.queue = new ArrayList<Player>();
        for(Player player: players){
            this.queuePlayer(player);
        }
        this.findMatch();
    }

    /**
     * Create a queue object from scratch
     */
    public Queue(){
        this.queue = new ArrayList<Player>();
    }

    /**
     * Get all the players in queue
     *
     * @return an arraylist with the players in queue
     */
    public ArrayList<Player> getPlayersInQueue(){
        return this.queue;
    }

    /**
     * Add a player to the queue
     *
     * @param player the player to add
     */
    public void queuePlayer(Player player){
        this.queue.add(player);
        player.queue();
    }

    /**
     * Remove a player from the queue
     *
     * @param player the player to remove
     */
    public void dequeuePlayer(Player player){
        if(this.queue.contains(player)){
            this.queue.remove(player);
            player.dequeue();
        }
    }

    /**
     * Find a match between the players in the queue which has
     * a predefined treshould
     *
     * @throws SQLException
     */
    public void findMatch() throws SQLException {
        boolean areMatchable;
        for(Player player: this.queue){
            try{
                Player opponent = queue.get(queue.indexOf(player)+1);
                if(!player.equals(opponent)){
                    areMatchable = Elo.areMatchable(player, opponent);
                    if(areMatchable && player.isInQueue() && opponent.isInQueue()){
                        player.dequeue();
                        opponent.dequeue();
                        Match.play(player, opponent);
                    }
                }
            }
            catch(IndexOutOfBoundsException e){
                continue;
            }
        }
    }
}
