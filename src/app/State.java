package app.Tree;

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
}