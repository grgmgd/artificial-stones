package app;

import java.util.LinkedList;
import java.util.Queue;

import org.javatuples.Pair;

/**
 * EndGame
 */
public class EndGame extends GenericSearchProblem {

    Pair<Integer, Integer> mapSize;
    Pair<Integer, Integer> thanosPosition;
    Queue<SearchTreeNode> nodes;

    public EndGame() {
        SearchTreeNode node = initialState();
        nodes = new LinkedList<SearchTreeNode>();
        nodes.add(node);
    }

    @Override
    public SearchTreeNode initialState() {
        // TODO: Auto-generated method stub
        return super.initialState();
    }

    @Override
    public Boolean goalTest(State state) {
        Boolean condition = state.remainingHealth > 0 && state.position.getValue0() == thanosPosition.getValue0()
                && state.position.getValue1() == thanosPosition.getValue1() && state.remainingStones.size() == 0;
        return condition;
    }

    @Override
    public State transitionFunction(State state, Operators operator) {
        // TODO: Auto-generated method stub
        return super.transitionFunction(state, operator);
    }

    public SearchTreeNode figure() {
        while (!nodes.isEmpty()) {

        }

        return null;
    }

}