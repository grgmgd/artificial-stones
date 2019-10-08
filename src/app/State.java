package app;

import java.util.ArrayList;
import org.javatuples.Pair;;

/**
 * State
 */
public class State {
	public Pair<Integer, Integer> position;
	public ArrayList<Pair<Integer, Integer>> remainingStones;
	ArrayList<Pair<Integer, Integer>> warriorsLocations;
	public int remainingHealth;
	public boolean snapped;

	/**
	 * The method uses search to try to formulate a winning plan with the
	 * corresponding cost and number of nodes explored
	 * 
	 * @param position          iron man position on map
	 * @param remainingStones   locations of the remaining not collected infinity
	 *                          stones
	 * @param remainingStones   Integer value representing the remaining health iron
	 *                          man has
	 * @param warriorsLocations
	 * @return State tuple containing all the inputs
	 */

	public State(Pair<Integer, Integer> position, ArrayList<Pair<Integer, Integer>> remainingStones,
			ArrayList<Pair<Integer, Integer>> warriorsLocations, int remainingHealth) {
		this.position = position;
		this.remainingStones = remainingStones;
		this.warriorsLocations = warriorsLocations;
		this.remainingHealth = remainingHealth;
		this.snapped = false;
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

	public ArrayList<Pair<Integer, Integer>> getWarriorsLocations() {
		return this.warriorsLocations;
	}

	public void translateX(int value) {
		position = position.setAt0(position.getValue0() + value);
	}

	public void translateY(int value) {
		position = position.setAt1(position.getValue1() + value);
	}

	public void decrementHealth(int value) {
		remainingHealth -= value;
	}

	public void setPosition(Pair<Integer, Integer> position) {
		this.position = position;
	}

	public void moveUp() {
		translateX(-1);
	}

	public void moveDown() {
		translateX(1);
	}

	public void moveRight() {
		translateY(-1);
	}

	public void moveLeft() {
		translateY(1);
	}

	public boolean isSnapped() {
		return snapped;
	}

	public void setSnapped(boolean snapped) {
		this.snapped = snapped;
	}

	@Override
	public boolean equals(Object obj) {
		State otherState = (State) obj;
		return position.equals(otherState.getPosition()) && remainingHealth == otherState.remainingHealth
				&& remainingStones.equals(otherState.getRemainingStones())
				&& warriorsLocations.equals(otherState.getWarriorsLocations());
	}

}