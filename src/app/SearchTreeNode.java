package app;

/**
 * SearchTreeNode
 */

public class SearchTreeNode implements Comparable<SearchTreeNode> {
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

	public String backtrack() {
		if (this.getParent().getParent() == null)
			return this.getLeadingOperator().toString().toLowerCase() + "";
		return parent.backtrack() + "," + this.getLeadingOperator().toString().toLowerCase();
	}

	public int backtrackCost() {
		if (this.getParent() == null)
			return 0;
		return parent.backtrackCost() + this.getCost();
	}

	@Override
	public String toString() {
		return "(" + leadingOperator + ") at depth: " + depth + "\nState: " + state;
	}

	@Override
	public int compareTo(SearchTreeNode node) {
		return this.getCost() - node.getCost();
	}
}