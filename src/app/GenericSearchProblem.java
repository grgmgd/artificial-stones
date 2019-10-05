package app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * GenericSearchProblem
 */

public abstract class GenericSearchProblem {

	LinkedList<SearchTreeNode> nodes;

	public GenericSearchProblem() {
		nodes = new LinkedList<SearchTreeNode>();
	}

	public abstract SearchTreeNode initialState();

	public abstract Boolean goalTest(State state);

	public State transitionFunction(State state, Operators operator) {
		return new State();
	}


}