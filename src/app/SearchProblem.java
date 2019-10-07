package app;

/**
 * GenericSearch
 */

public interface SearchProblem {
	public State initialState();

	public State transitionFunction(State state, Operators operator);

	public Boolean goalTest(State state);

	public int pathCost();
}