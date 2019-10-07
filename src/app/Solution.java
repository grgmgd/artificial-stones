package app;

import java.util.ArrayList;

public class Solution {
	String plan;
	int cost;
	ArrayList<SearchTreeNode> nodes;

	public Solution(String p, int c, ArrayList<SearchTreeNode> n) {
		plan = p;
		cost = c;
		nodes = n;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public ArrayList<SearchTreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<SearchTreeNode> nodes) {
		this.nodes = nodes;
	}

	public void addPlan(Operators leadingOperator) {
		plan += plan.length() > 0 ? ", " + leadingOperator : leadingOperator;
	}

	public void addCost(int c) {
		cost += c;

	}

	@Override
	public String toString() {
		return plan + ";" + cost + ";" + nodes;
	}
}
