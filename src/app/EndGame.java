package app;

import java.util.ArrayList;
import org.javatuples.Pair;

/**
 * EndGame
 */

public class EndGame implements SearchProblem {

	Pair<Integer, Integer> gridSize;
	Pair<Integer, Integer> thanosPosition;
	ArrayList<Pair<Integer, Integer>> warriorsLocations;
	String problem;

	public EndGame(String problem) {
		this.problem = problem;
	}

	@Override
	public State initialState() {
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
		warriorsLocations = new ArrayList<>();

		for (int i = 0; i < warriorsIndicies.length - 1; i += 2) {
			Pair<Integer, Integer> warrior = new Pair<Integer, Integer>(Integer.parseInt(warriorsIndicies[i]),
					Integer.parseInt(warriorsIndicies[i + 1]));
			warriorsLocations.add(warrior);
		}

		thanosPosition = new Pair<Integer, Integer>(Integer.parseInt(thanosLocationString[0]),
				Integer.parseInt(thanosLocationString[1]));

		Pair<Integer, Integer> position = new Pair<Integer, Integer>(Integer.parseInt(ironManLocationString[0]),
				Integer.parseInt(ironManLocationString[1]));

		State state = new State(position, remainingStones, 100);

		return state;
	}

	public Boolean goalTest(State state) {
		Boolean condition = state.remainingHealth > 0 && state.position.getValue0() == thanosPosition.getValue0()
				&& state.position.getValue1() == thanosPosition.getValue1() && state.remainingStones.size() == 0;
		return condition;
	}

	public State transitionFunction(State state, Operators operator) {
		State newState = new State(state.getPosition(), state.getRemainingStones(), state.getRemainingHealth());
		switch (operator) {
		case UP: {
			if (newState.getPosition().getValue0() + 1 != gridSize.getValue0())
				newState.translateX(1);
		}
			break;
		case DOWN: {
			if (newState.getPosition().getValue0() - 1 != 0)
				newState.translateX(-1);
		}
			break;
		case LEFT: {
			if (newState.getPosition().getValue1() + 1 != gridSize.getValue1())
				newState.translateY(1);
		}
			break;
		case RIGHT: {
			if (newState.getPosition().getValue1() - 1 != 0)
				newState.translateY(-1);
		}
			break;
		case COLLECT: {
			ArrayList<Pair<Integer, Integer>> stones = newState.getRemainingStones();
			for (int i = 0; i < stones.size(); i++) {
				if (newState.getPosition().equals(stones.get(i))) {
					newState.getRemainingStones().remove(i);
					newState.decrementHealth(3);
				}
			}
		}
			break;
		case KILL: {

		}
			break;
		case SNAP: {

		}
			break;
		default:
			break;
		}
		return newState;
	}

	@Override
	public int pathCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	// public SearchTreeNode[] expand(SearchTreeNode node) {
	// State currentState = node.getState();
	// int currentCost = node.getCost();
	// int currentDepth = node.getDepth();
	// Operators[] operators = Operators.values();
	// SearchTreeNode[] expansionList = new SearchTreeNode[operators.length];
	// int index = 0;
	// for (Operators opr : operators) {
	// State state = transitionFunction(currentState, opr);
	// if (state == null)
	// continue;
	// expansionList[index] = new SearchTreeNode(state, node, opr, currentCost,
	// currentDepth + 1);
	// }
	// return expansionList;
	// }

	// public SearchTreeNode search(SearchingAlgorithm searchAlgorithm) {
	// while (!nodes.isEmpty()) {
	// SearchTreeNode node = nodes.removeFirst();
	// if (goalTest(node.state))
	// return node;
	// SearchTreeNode[] expansionList = expand(node);
	// searchAlgorithm.enqueue(nodes, node, expansionList);
	// }

	// return null;
	// }

	// public SearchTreeNode figure() {
	// SearchingAlgorithm searchAlgorithm = null;
	// switch (strategy) {
	// case BF:
	// searchAlgorithm = new BreadthFirst();
	// break;
	// case DF:
	// searchAlgorithm = new DepthFirst();
	// break;
	// case ID:
	// // TODO
	// break;
	// case UC:
	// // TODO
	// break;
	// case ASi:
	// // TODO
	// break;
	// case GRi:
	// // TODO
	// break;
	// default:
	// searchAlgorithm = new BreadthFirst();
	// }
	// return search(searchAlgorithm);
	// }

}