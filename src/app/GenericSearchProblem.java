package app;

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

    public State initialState(String problem) {
        return new State();
    }
}