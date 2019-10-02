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

        State newState = new State();

        switch (operator) {
        case UP:
            newState.position.setAt1(state.position.getValue0() + 1);
            break;
        case DOWN:
            newState.position.setAt1(state.position.getValue0() - 1);
            break;
        case LEFT:
            newState.position.setAt1(state.position.getValue1() + 1);
            break;
        case RIGHT:
            newState.position.setAt1(state.position.getValue1() - 1);
            break;
        }
        return newState;
    }

    public SearchTreeNode figure() {
        while (!nodes.isEmpty()) {
            SearchTreeNode node = nodes.remove();
            if (goalTest(node.state))
                return node;
            // TODO: call Qing-function and append the new nodes
        }

        return null;
    }

}