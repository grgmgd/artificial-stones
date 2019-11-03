package app;

import java.util.ArrayList;
import org.javatuples.Pair;

import searching.algorithms.SearchingAlgorithms;

/***
 * EndGame is the SearchProblem instance in which our problem is defined. This
 * class contains all the main tools that will contribute in the problem
 * solution.
 * 
 * @param gridSize       contains the <A, B> indicating the grid size of A x B.
 * @param thanosPosition <X, Y> indicating where Thanos is located in the grid.
 * @param problem        the actual problem string; semi-colon seperated string
 *                       indicating at <br>
 *                       [0]: grid size,<br>
 *                       [1]: Iron Man position,<br>
 *                       [2]: Thanos position,<br>
 *                       [3]: stones locations,<br>
 *                       [4]: warriors locations
 *
 */

public class EndGame implements SearchProblem {

	Pair<Integer, Integer> gridSize;
	Pair<Integer, Integer> thanosPosition;
	String problem;
	public static SearchingAlgorithms strategy;
	public static int scoreRemainingStones = 7;
	public static Pair<Integer, Integer> furtherPlace = new Pair<Integer, Integer>(0, 0);

	public EndGame(String problem, SearchingAlgorithms strategy) {
		this.problem = problem;
		EndGame.strategy = strategy;
	}

	/***
	 * function responsible for generating the very first state that will be used in
	 * the only enqueued node once the searching algorithm starts.
	 * 
	 * @return the initial state
	 */
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

	/***
	 * function responsible for declaring whether a certain state is a goal state or
	 * not.
	 * 
	 * @return boolean
	 */

	public Boolean goalTest(State state) {
		Boolean condition = state.isSnapped() && state.remainingHealth > 0;
		return condition;
	}

	/***
	 * function responsible for computing the new state from an older state given a
	 * certain operator.<br>
	 * <blockquote> For each operator, we are defining several constraints in order
	 * for our agent to be a rational one.<blockquote>
	 * 
	 * @param state    the old state
	 * @param operator the operator taking place and acting upon the old state
	 * @return null for invalid transition function over the given state.<br>
	 *         state the new generated state
	 */

	public SearchTreeNode transitionFunction(SearchTreeNode node, Operators operator) {
		State newState = null;

		try {
			newState = node.getState().clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		switch (operator) {
		case UP: {
			newState.moveUp();
			Pair<Integer, Integer> newMovement = newState.getPosition();
			if (!allowedMove(newMovement, newState))
				return null;
		}
			break;
		case DOWN: {
			newState.moveDown();
			Pair<Integer, Integer> newMovement = newState.getPosition();
			if (!allowedMove(newMovement, newState))
				return null;
		}
			break;
		case LEFT: {
			newState.moveLeft();
			Pair<Integer, Integer> newMovement = newState.getPosition();
			if (!allowedMove(newMovement, newState))
				return null;

		}
			break;
		case RIGHT: {
			newState.moveRight();
			Pair<Integer, Integer> newMovement = newState.getPosition();
			if (!allowedMove(newMovement, newState))
				return null;

		}
			break;
		case COLLECT: {
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
		}
			break;
		case KILL: {
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
		}
			break;
		case SNAP: {
			Pair<Integer, Integer> position = newState.getPosition();
			ArrayList<Pair<Integer, Integer>> remainingStones = newState.getRemainingStones();
			int remainingHealth = newState.getRemainingHealth();
			if (position.equals(thanosPosition) && remainingHealth > 0 && remainingStones.size() == 0)
				newState.setSnapped(true);
			else
				return null;
		}
			break;

		default:
		}

		int newStateRemainingHealth = newState.getRemainingHealth();
		if (newStateRemainingHealth <= 0)
			return null;

		// monitorState(newState);
		int newCost = pathCost(node, operator);
		SearchTreeNode newNode = new SearchTreeNode(newState, node, operator, newCost, node.getDepth() + 1);
		switch (strategy) {
		case GR1:
		case GR2:
		case AS1:
		case AS2:
			computeHeuristicCost(newNode);
			break;
		default:
		}
		return newNode;
	}

	public int pathCost(SearchTreeNode node, Operators operator) {
		State state = node.getState();
		HealthReport healthReport = getHealthDecreasingAmount(state, operator);
		int healthDecreased = healthReport.computeDamage(operator);
		state.decrementHealth(healthDecreased);
		return healthDecreased + node.getCost();
	}

	/***
	 * function responsible for computing the heuristic cost for each new structured
	 * node according to the used strategy.
	 * 
	 * @param node the new node for which we want to store the heuristic cost.
	 */
	public void computeHeuristicCost(SearchTreeNode node) {
		switch (strategy) {
		case GR1:
		case AS1:
			node.setHeuristicCost(node.getState().getRemainingStones().size() * 3);
			break;
		case GR2:
		case AS2:
			double minimum = 0;
			double heuristicCost;
			ArrayList<Pair<Integer, Integer>> stones = node.getState().getRemainingStones();
			Pair<Integer, Integer> ironMan = node.getState().getPosition();
			if (stones.isEmpty()) {
				double maxDistance = getDistance(new Pair<Integer, Integer>(0, 0),
						new Pair<Integer, Integer>(gridSize.getValue0() - 1, gridSize.getValue1() - 1));
				for (int i = 0; i < stones.size(); i++) {
					Pair<Integer, Integer> stone = stones.get(i);
					double distance = getDistance(ironMan, stone);
					if (i == 0)
						minimum = distance;
					else
						minimum = distance < minimum ? distance : minimum;
				}
				heuristicCost = minimum / maxDistance * 3;
			}

			else {
				heuristicCost = 0;
			}
			node.setHeuristicCost(heuristicCost);
			break;
		default:
			node.setHeuristicCost(node.getNormalCost());
		}
	}

	/***
	 * helper function responsible for approving upon a certain made move.
	 * 
	 * @param movement the new location of Iron Man
	 * @param state    the new state of the current node to explore it
	 * @return approving or disapproving boolean
	 */

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

	/***
	 * function responsible for retrieving knowledge about Iron Man's health state
	 * regarding the surrounding danger; Thanos, Warriors, such as the warriors
	 * count and whether he is adjacent to Thanos or not.
	 * 
	 * @param state the current state to explore
	 * @return the corresponding health report related to Iron Man's position
	 */

	public HealthReport getHealthDecreasingAmount(State state, Operators operator) {
		int warriorsHit = 0;
		boolean thanosHit = false;
		ArrayList<Pair<Integer, Integer>> warriorsLocations = state.getWarriorsLocations();
		Pair<Integer, Integer> position = state.getPosition();
		if (isAdjacent(position, thanosPosition)
				|| (position.equals(thanosPosition) && !operator.equals(Operators.SNAP)))
			thanosHit = true;

		for (Pair<Integer, Integer> warriorPosition : warriorsLocations)
			if (isAdjacent(position, warriorPosition))
				warriorsHit++;
		return new HealthReport(thanosHit, warriorsHit);
	}

	/***
	 * helper function responsible for indicating if two given position pairs are
	 * adjacent to one another or not.
	 * 
	 * @param self  first pair
	 * @param other second pair
	 * @return boolean
	 */

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

	/***
	 * helper function responsible for computing the distance between any two given
	 * pairs using the Euclidean distance.
	 * 
	 * @param x first location
	 * @param y second location
	 * @return double representing the distance between the two nodes
	 */
	public double getDistance(Pair<Integer, Integer> x, Pair<Integer, Integer> y) {
		int x0 = x.getValue0();
		int x1 = x.getValue1();
		int y0 = y.getValue0();
		int y1 = y.getValue1();
		return Math.sqrt(Math.pow(x0 - y0, 2) + Math.pow(x1 - y1, 2));
	}

	/***
	 * debugging function used to monitor each state's parameter progress.
	 * 
	 * @param state
	 */
	public void monitorState(State state) {
		if (state.getRemainingStones().size() < scoreRemainingStones) {
			scoreRemainingStones = state.getRemainingStones().size();
			System.out.println("\nNew score for remaining stones: " + state);
		}
		if (state.getPosition().getValue0() > furtherPlace.getValue0()) {
			furtherPlace = furtherPlace.setAt0(state.getPosition().getValue0());
			System.out.println("\nNew further X value: " + state);
		}
		if (state.getPosition().getValue1() > furtherPlace.getValue1()) {
			furtherPlace = furtherPlace.setAt1(state.getPosition().getValue1());
			System.out.println("\nNew further Y value: " + state);
		}
	}
}