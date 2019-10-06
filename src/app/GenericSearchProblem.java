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

	public State initialState(String problem) {
		return new State();
	}
}