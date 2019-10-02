package app;

import java.util.LinkedList;
import java.util.Queue;

/**
 * GenericSearchProblem
 */

public abstract class GenericSearchProblem {

    Queue<SearchTreeNode> nodes;

    public Boolean goalTest(State state) {
        return false;
    }

    public State transitionFunction(State state, Operators operator) {
        return new State();
    }

    public SearchTreeNode initialState() {
        return new SearchTreeNode();
    }

    // public LinkedList<SearchTreeNode> Qing(State state) {

    // }
}