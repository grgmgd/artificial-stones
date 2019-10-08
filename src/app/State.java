package app;

import java.util.ArrayList;
import org.javatuples.Pair;;

/**
 * State
 */
public class State {
	public Pair<Integer, Integer> position;
	public ArrayList<Pair<Integer, Integer>> remainingStones;
	public int remainingHealth;

	/**
	 * The method uses search to try to formulate a winning plan with the
	 * corresponding cost and number of nodes explored
	 * 
	 * @param position        iron man position on map
	 * @param remainingStones locations of the remaining not collected infinity
	 *                        stones
	 * @param remainingStones Integer value representing the remaining health iron
	 *                        man has
	 * @return State tuple containing all the inputs
	 */

	public State(Pair<Integer, Integer> position, ArrayList<Pair<Integer, Integer>> remainingStones,
			int remainingHealth) {
		this.position = position;
		this.remainingStones = remainingStones;
		this.remainingHealth = remainingHealth;
	}

	public Pair<Integer, Integer> getPosition() {
		return this.position;
	}

	public ArrayList<Pair<Integer, Integer>> getRemainingStones() {
		return this.remainingStones;
	}

	public int getRemainingHealth() {
		return this.remainingHealth;
	}

	public void translateX(int value) {
		position.setAt0(position.getValue0() + value);
	}

	public void translateY(int value) {
		position.setAt1(position.getValue1() + value);
	}

	public void decrementHealth(int value) {
		remainingHealth -= value;
	}

	@Override
	public boolean equals(Object obj) {
		State otherState = (State) obj;
		return position.equals(otherState.getPosition()) && remainingHealth == otherState.remainingHealth
				&& remainingStones.equals(otherState.getRemainingStones());
	}
}