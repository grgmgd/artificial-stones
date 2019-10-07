package app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.javatuples.Pair;

import searching.algorithms.BreadthFirst;
import searching.algorithms.DepthFirst;
import searching.algorithms.SearchingAlgorithm;
import searching.algorithms.SearchingAlgorithms;

/**
 * EndGame
 */

public class EndGame extends GenericSearchProblem {

	Pair<Integer, Integer> gridSize;
	Pair<Integer, Integer> thanosPosition;
	SearchingAlgorithms strategy;

	public EndGame(String problem, SearchingAlgorithms strategy) {
		super();
		State state = initialState(problem);
		SearchTreeNode node = new SearchTreeNode(state, null, null, 0, 0);
		nodes.add(node);
		this.strategy = strategy;
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

	public Boolean goalTest(State state) {
		Boolean condition = state.remainingHealth > 0 && state.position.getValue0() == thanosPosition.getValue0()
				&& state.position.getValue1() == thanosPosition.getValue1() && state.remainingStones.size() == 0;
		return condition;
	}

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
		case COLLECT:
		case KILL:
		case SNAP:
		default:
			break;
		}
		return newState;
	}

	public SearchTreeNode[] expand(SearchTreeNode node) {
		State currentState = node.getState();
		int currentCost = node.getCost();
		int currentDepth = node.getDepth();
		Operators[] operators = Operators.values();
		SearchTreeNode[] expansionList = new SearchTreeNode[operators.length];
		int index = 0;
		for (Operators opr : operators) {
			State state = transitionFunction(currentState, opr);
			if (state == null)
				continue;
			expansionList[index] = new SearchTreeNode(state, node, opr, currentCost, currentDepth + 1);
		}
		return expansionList;
	}

	public SearchTreeNode search(SearchingAlgorithm searchAlgorithm) {
		while (!nodes.isEmpty()) {
			SearchTreeNode node = nodes.removeFirst();
			if (goalTest(node.state))
				return node;
			SearchTreeNode[] expansionList = expand(node);
			searchAlgorithm.enqueue(nodes, node, expansionList);
		}

		return null;
	}

	public SearchTreeNode figure() {
		SearchingAlgorithm searchAlgorithm = null;
		switch (strategy) {
		case BF:
			searchAlgorithm = new BreadthFirst();
			break;
		case DF:
			searchAlgorithm = new DepthFirst();
			break;
		case ID:
			// TODO
			break;
		case UC:
			// TODO
			break;
		case ASi:
			// TODO
			break;
		case GRi:
			// TODO
			break;
		default:
			searchAlgorithm = new BreadthFirst();
		}
		return search(searchAlgorithm);
	}

}