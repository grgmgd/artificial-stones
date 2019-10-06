package app;

import java.util.ArrayList;
import org.javatuples.Pair;;

/**
 * State
 */
public class State {
    public Pair<Integer, Integer> position;
    public Pair<Integer, Integer> thanosPosition;
    public ArrayList<Pair<Integer, Integer>> remainingStones;
    public int remainingHealth;

    public State() {

    }

    /**
     * The method uses search to try to formulate a winning plan with the
     * corresponding cost and number of nodes explored
     * 
     * @param position        iron man position on map
     * @param thanosPosition  thanos position on the map
     * @param remainingStones locations of the remaining not collected infinity
     *                        stones
     * @param remainingStones Integer value representing the remaining health iron
     *                        man has
     * @return State tuple containing all the inputs
     */

    public State(Pair<Integer, Integer> position, Pair<Integer, Integer> thanosPosition,
            ArrayList<Pair<Integer, Integer>> remainingStones, int remainingHealth) {
        this.position = position;
        this.thanosPosition = thanosPosition;
        this.remainingStones = remainingStones;
        this.remainingHealth = remainingHealth;
    }
}