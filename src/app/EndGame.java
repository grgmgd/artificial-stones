package app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.javatuples.Pair;

/**
 * EndGame
 */

public class EndGame extends GenericSearchProblem {

    Pair<Integer, Integer> gridSize;
    Pair<Integer, Integer> thanosPosition;
    Queue<SearchTreeNode> nodes;

    public EndGame(String problem) {
        State state = initialState(problem);
        SearchTreeNode node = new SearchTreeNode(state, null, null, 0, 0);
        nodes = new LinkedList<SearchTreeNode>();
        nodes.add(node);
    }

    @Override
    public State initialState(String problem) {
        String[] parts = problem.split(";");

        String[] gridSizeString = parts[0].split(",");
        gridSize = new Pair<Integer, Integer>(Integer.parseInt(gridSizeString[0]), Integer.parseInt(gridSizeString[1]));

        String[] ironManLocationString = parts[1].split(",");
        String[] thanosLocationString = parts[2].split(",");

        String[] stonesIndicies = parts[3].split(",");
        ArrayList<Pair<Integer, Integer>> remainingStones = new ArrayList<>();

        for (int i = 0; i < stonesIndicies.length - 1; i += 2) {
            Pair<Integer, Integer> stone = new Pair<Integer, Integer>(Integer.parseInt(stonesIndicies[i]),
                    Integer.parseInt(stonesIndicies[i + 1]));
            remainingStones.add(stone);
        }

        String[] warriorsIndicies = parts[4].split(",");
        ArrayList<Pair<Integer, Integer>> warriorsLocations = new ArrayList<>();

        for (int i = 0; i < warriorsIndicies.length - 1; i += 2) {
            Pair<Integer, Integer> warrior = new Pair<Integer, Integer>(Integer.parseInt(warriorsIndicies[i]),
                    Integer.parseInt(warriorsIndicies[i + 1]));
            warriorsLocations.add(warrior);
        }

        thanosPosition = new Pair<Integer, Integer>(Integer.parseInt(thanosLocationString[0]),
                Integer.parseInt(thanosLocationString[1]));

        Pair<Integer, Integer> position = new Pair<Integer, Integer>(Integer.parseInt(ironManLocationString[0]),
                Integer.parseInt(ironManLocationString[1]));

        State state = new State(position, thanosPosition, remainingStones, 100);

        return state;
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