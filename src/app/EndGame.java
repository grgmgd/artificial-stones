package app;

import java.util.ArrayList;
import org.javatuples.Pair;

/**
 * EndGame
 */

public class EndGame implements SearchProblem {

	Pair<Integer, Integer> gridSize;
	Pair<Integer, Integer> thanosPosition;
	String problem;

	public EndGame(String problem) {
		this.problem = problem;
	}

	@Override
	public State initialState() {
		ArrayList<Pair<Integer, Integer>> warriorsLocations;
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

		State state = new State(position, remainingStones, warriorsLocations, 100);

		return state;
	}

	public Boolean goalTest(State state) {
		Boolean condition = state.isSnapped() && state.remainingHealth > 0
				&& state.position.getValue0() == thanosPosition.getValue0()
				&& state.position.getValue1() == thanosPosition.getValue1() && state.remainingStones.size() == 0;
		return condition;
	}

	public State transitionFunction(State state, Operators operator) {
		State newState = null;
		try {
			newState = state.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		switch (operator) {
		case UP: {
			newState.moveUp();
			Pair<Integer, Integer> newMovement = newState.getPosition();
			if (allowedMove(newMovement, newState)) {
				HealthReport healthReport = getHealthDecreasingAmount(newState);
				int healthDecreased = healthReport.computeDamage(false);
				newState.decrementHealth(healthDecreased);
			} else
				return null;
			break;
		}
		case DOWN: {
			newState.moveDown();
			Pair<Integer, Integer> newMovement = newState.getPosition();
			if (allowedMove(newMovement, newState)) {
				HealthReport healthReport = getHealthDecreasingAmount(newState);
				int healthDecreased = healthReport.computeDamage(false);
				newState.decrementHealth(healthDecreased);
			} else
				return null;
			break;
		}
		case LEFT: {
			newState.moveLeft();
			Pair<Integer, Integer> newMovement = newState.getPosition();
			if (allowedMove(newMovement, newState)) {
				HealthReport healthReport = getHealthDecreasingAmount(newState);
				int healthDecreased = healthReport.computeDamage(false);
				newState.decrementHealth(healthDecreased);
			} else
				return null;
			break;
		}
		case RIGHT: {
			newState.moveRight();
			Pair<Integer, Integer> newMovement = newState.getPosition();
			if (allowedMove(newMovement, newState)) {
				HealthReport healthReport = getHealthDecreasingAmount(newState);
				int healthDecreased = healthReport.computeDamage(false);
				newState.decrementHealth(healthDecreased);
			} else
				return null;
			break;
		}
		case COLLECT: {
			HealthReport healthReport = getHealthDecreasingAmount(newState);
			int healthDecreased = healthReport.computeDamage(false);
			newState.decrementHealth(healthDecreased);
			ArrayList<Pair<Integer, Integer>> stones = newState.getRemainingStones();
			boolean validAction = false;
			for (int i = 0; i < stones.size();) {
				if (newState.getPosition().equals(stones.get(i))) {
					validAction |= true;
					newState.getRemainingStones().remove(i);
					newState.decrementHealth(3);
				} else
					i++;
			}
			if (!validAction)
				return null;
			break;
		}
		case KILL: {
			HealthReport healthReport = getHealthDecreasingAmount(newState);
			int healthDecreased = healthReport.computeDamage(true);
			newState.decrementHealth(healthDecreased);
			ArrayList<Pair<Integer, Integer>> warriorsLocations = newState.getWarriorsLocations();
			Pair<Integer, Integer> self = newState.getPosition();
			boolean validAction = false;
			for (int i = 0; i < warriorsLocations.size();) {
				Pair<Integer, Integer> warriorLocation = warriorsLocations.get(i);
				if (isAdjacent(self, warriorLocation)) {
					warriorsLocations.remove(i);
					validAction |= true;
				} else
					i++;
			}
			if (!validAction)
				return null;
			break;
		}
		case SNAP: {
			Pair<Integer, Integer> position = newState.getPosition();
			ArrayList<Pair<Integer, Integer>> remainingStones = newState.getRemainingStones();
			int remainingHealth = newState.getRemainingHealth();
			if (position.equals(thanosPosition) && remainingHealth > 0 && remainingStones.size() == 0)
				newState.setSnapped(true);
			else
				return null;
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
		ArrayList<Pair<Integer, Integer>> warriorsLocations = state.getWarriorsLocations();
		boolean xAllowed = movement.getValue0() != -1 && movement.getValue0() != gridSize.getValue0();
		boolean yAllowed = movement.getValue1() != -1 && movement.getValue1() != gridSize.getValue1();
		if (xAllowed && yAllowed) {
			boolean thanosOverlapAllowed = !thanosPosition.equals(movement) || state.getRemainingStones().size() == 0;
			boolean warriorOverlapAllowed = !warriorsLocations.contains(movement);
			return warriorOverlapAllowed && thanosOverlapAllowed;
		}
		return false;
	}

	public HealthReport getHealthDecreasingAmount(State state) {
		int warriorsHit = 0;
		boolean thanosHit = false;
		ArrayList<Pair<Integer, Integer>> warriorsLocations = state.getWarriorsLocations();
		Pair<Integer, Integer> position = state.getPosition();
		if (isAdjacent(position, thanosPosition))
			thanosHit = true;

		for (Pair<Integer, Integer> warriorPosition : warriorsLocations)
			if (isAdjacent(position, warriorPosition))
				warriorsHit += 1;
		return new HealthReport(thanosHit, warriorsHit);
	}

	public boolean isAdjacent(Pair<Integer, Integer> self, Pair<Integer, Integer> other) {
		int xSelfLoc = self.getValue0();
		int ySelfLoc = self.getValue1();
		int xOtherLoc = other.getValue0();
		int yOtherLoc = other.getValue1();
		boolean right = xSelfLoc == xOtherLoc - 1 && ySelfLoc == yOtherLoc;
		boolean left = xSelfLoc == xOtherLoc + 1 && ySelfLoc == yOtherLoc;
		boolean up = xSelfLoc == xOtherLoc && ySelfLoc == yOtherLoc + 1;
		boolean down = xSelfLoc == xOtherLoc && ySelfLoc == yOtherLoc - 1;
		return right || left || up || down;
	}

}