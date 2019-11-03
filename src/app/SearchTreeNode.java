package app;

import java.util.ArrayList;

/**
 * SearchTreeNode
 */

public class SearchTreeNode implements Comparable<SearchTreeNode> {
	public State state;
	public SearchTreeNode parent;
	public Operators leadingOperator;
	public int cost;
	public double heuristicCost;
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

	public int getNormalCost() {
		return cost;
	}

	public int getCost() {
		return cost;

	}

	public int getDepth() {
		return depth;
	}

	public double getHeuristicCost() {
		return heuristicCost;
	}

	public void setHeuristicCost(int cost) {
		heuristicCost = cost;
	}

	public ArrayList<SearchTreeNode> pathFromRoot() {
		ArrayList<SearchTreeNode> path = new ArrayList<SearchTreeNode>();
		SearchTreeNode node = this;

		while(node.getParent() != null) {
			path.add(0, node);
			node = node.getParent();
		}

		return path;
	}
	
	public void setHeuristicCost(double cost) {
		heuristicCost = cost;
	}

	public String backtrack() {
		if (this.getParent().getParent() == null)
			return this.getLeadingOperator().toString().toLowerCase() + "";
		return parent.backtrack() + "," + this.getLeadingOperator().toString().toLowerCase();
	}

	@Override
	public String toString() {
		return "(" + leadingOperator + ") at depth: " + depth + "\nState: " + state;
	}

	@Override
	public int compareTo(SearchTreeNode node) {
		switch (EndGame.strategy) {
		case GR1:
		case GR2:
			return (int) (this.getHeuristicCost() - node.getHeuristicCost());
		case AS1:
		case AS2:
			return (int) ((this.getHeuristicCost() + this.getCost()) - (node.getHeuristicCost() + node.getCost()));
		default:
			return this.getCost() - node.getCost();
		}

	}
}