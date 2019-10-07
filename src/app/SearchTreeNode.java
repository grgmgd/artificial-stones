package app;

/**
 * SearchTreeNode
 */

public class SearchTreeNode {
	public State state;
	public SearchTreeNode parent;
	public Operators leadingOperator;
	public int cost;
	public int depth;

	public SearchTreeNode(State state, SearchTreeNode parent, Operators opr, int cost, int depth) {
		this.state = state;
		this.parent = parent;
		this.leadingOperator = opr;
		this.cost = cost;
		this.depth = depth;
	}

	public State getState() {
		return state;
	}

	public SearchTreeNode getParent() {
		return parent;
	}

	public Operators getLeadingOperator() {
		return leadingOperator;
	}

	public int getCost() {
		return cost;
	}

	public int getDepth() {
		return depth;
	}

	public Solution getSolution(Solution solution) {
		solution.addPlan(leadingOperator);
		solution.addCost(cost);
		// TODO solution.addNodes?
		while (getParent() != null)
			return getParent().getSolution(solution);
		return solution;
	}
}