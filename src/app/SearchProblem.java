package app;

/**
 * GenericSearch
 */

public interface SearchProblem {
	public State initialState();

	public SearchTreeNode transitionFunction(SearchTreeNode node, Operators operator);

	public Boolean goalTest(State state);

	public int pathCost(SearchTreeNode node, Operators operator);
}