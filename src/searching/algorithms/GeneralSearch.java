package searching.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import app.SearchProblem;
import app.SearchTreeNode;
import app.Operators;

public class GeneralSearch {
	private LinkedList<SearchTreeNode> nodes;
	private HashMap<String, Boolean> uniqueStates;
	SearchingAlgorithms strategy;
	SearchProblem problem;
	static int currentlyHandlingDepth = 0;

	public GeneralSearch(SearchProblem problem, SearchingAlgorithms strategy) {
		nodes = new LinkedList<SearchTreeNode>();
		SearchTreeNode node = new SearchTreeNode(problem.initialState(), null, null, 0, 0);
		nodes.add(node);
		this.problem = problem;
		this.strategy = strategy;
		this.uniqueStates = new HashMap<String, Boolean>();
	};

	public String figure() {
		while (!nodes.isEmpty()) {
			SearchTreeNode node = nodes.removeFirst();
			int nodeDepth = node.getDepth();
			if (nodeDepth != currentlyHandlingDepth) {
				currentlyHandlingDepth = nodeDepth;
				System.out.println("Nodes count:" + nodes.size());
				System.out.println("Reached depth " + currentlyHandlingDepth);
			}
			if (problem.goalTest(node.state))
				return node.backtrack() + ";" + node.getCost();
			quing(expand(node));
		}

		return null;
	}

	public ArrayList<SearchTreeNode> expand(SearchTreeNode node) {
		Operators[] operators = Operators.values();
		ArrayList<SearchTreeNode> expansionList = new ArrayList<SearchTreeNode>();
		for (Operators opr : operators) {
			SearchTreeNode expansionNode = problem.transitionFunction(node, opr);
			if (expansionNode != null && !isRepeated(expansionNode)) {
				expansionList.add(expansionNode);
			}
		}
		return expansionList;
	}

	public void quing(ArrayList<SearchTreeNode> expansionList) {
		switch (strategy) {
		case DF: {
			nodes.addAll(0, expansionList);
		}
			break;
		case BF: {
			nodes.addAll(expansionList);
		}
			break;
		case ID: {
			break;
		}
		case UC: {
			nodes.addAll(expansionList);
			Collections.sort(nodes);
			// favourCheapest();
		}
			break;
		case ASi: {
			break;
		}
		case GRi: {
			break;
		}
		default:
		}
	}

	public int getNodeCount() {
		return uniqueStates.size();
	}

	public boolean isRepeated(SearchTreeNode newNode) {
		String state = newNode.getState().toString();
		if (uniqueStates.get(state) != null)
			return true;
		uniqueStates.put(state, true);
		return false;
	}

	// Better time complexity of O(n) than resorting each time O(n*log(n))
	public void favourCheapest() {
		int min = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < nodes.size(); i++) {
			int cost = nodes.get(i).getCost();
			if (cost <= min) {
				min = cost;
				index = i;
			}
		}

		SearchTreeNode cheapest = nodes.remove(index);
		nodes.addFirst(cheapest);
	}
}
