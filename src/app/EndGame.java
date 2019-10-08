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
	boolean snapped = false;

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
			Pair<Integer, Integer> newMovement = new Pair<Integer, Integer>(newState.getPosition().getValue0() + 1,
					newState.getPosition().getValue1());
			if (allowedMove(newMovement, newState))
				newState.translateX(1);
			else
				return null;
			break;
		}
		case DOWN: {
			Pair<Integer, Integer> newMovement = new Pair<Integer, Integer>(newState.getPosition().getValue0() - 1,
					newState.getPosition().getValue1());
			if (allowedMove(newMovement, newState))
				newState.translateX(-1);
			else
				return null;
			break;
		}
		case LEFT: {
			Pair<Integer, Integer> newMovement = new Pair<Integer, Integer>(newState.getPosition().getValue0(),
					newState.getPosition().getValue1() + 1);
			if (allowedMove(newMovement, newState))
				newState.translateY(1);
			else
				return null;
			break;
		}
		case RIGHT: {
			Pair<Integer, Integer> newMovement = new Pair<Integer, Integer>(newState.getPosition().getValue0(),
					newState.getPosition().getValue1() - 1);
			if (allowedMove(newMovement, newState))
				newState.translateY(-1);
			else
				return null;
			break;
		}
		case COLLECT: {
			ArrayList<Pair<Integer, Integer>> stones = newState.getRemainingStones();
			for (int i = 0; i < stones.size(); i++) {
				if (newState.getPosition().equals(stones.get(i))) {
					newState.getRemainingStones().remove(i);
					newState.decrementHealth(3);
				}
			}
			break;
		}
		case KILL: {

			break;
		}
		case SNAP: {

			break;
		}
		default:
			break;
		}
		int newStateRemainingHealth = newState.getRemainingHealth();
		if (newStateRemainingHealth == 0)
			return null;
		return newState;
	}

	@Override
	public int pathCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean allowedMove(Pair<Integer, Integer> movement, State state) {
		boolean xAllowed = movement.getValue0() != -1 && movement.getValue0() != gridSize.getValue0();
		boolean yAllowed = movement.getValue1() != -1 && movement.getValue1() != gridSize.getValue1();
		if (xAllowed && yAllowed) {
			boolean thanosOverlapAllowed = thanosPosition.equals(movement) && state.getRemainingStones().size() == 0;
			boolean warriorOverlapAllowed = !warriorsLocations.contains(movement);
			return warriorOverlapAllowed && thanosOverlapAllowed;
		}
		return false;
	}
	


}