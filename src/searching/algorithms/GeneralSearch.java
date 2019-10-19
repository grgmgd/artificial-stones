package searching.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import app.SearchProblem;
import app.SearchTreeNode;
import app.Operators;

public class GeneralSearch {
	private Queue<SearchTreeNode> nodes;
	private HashMap<String, Boolean> uniqueStates;
	SearchingAlgorithms strategy;
	SearchProblem problem;
	static int currentlyHandlingDepth = 0;
	private SearchingAlgorithms[] notOrderedStrategies = { SearchingAlgorithms.BF, SearchingAlgorithms.DF,
			SearchingAlgorithms.ID };

	public GeneralSearch(SearchProblem problem, SearchingAlgorithms strategy) {
		if (strategy.oneOf(notOrderedStrategies))
			nodes = new LinkedList<SearchTreeNode>();
		else
			nodes = new PriorityQueue<SearchTreeNode>();
		SearchTreeNode node = new SearchTreeNode(problem.initialState(), null, null, 0, 0);
		nodes.add(node);
		this.problem = problem;
		this.strategy = strategy;
		this.uniqueStates = new HashMap<String, Boolean>();
	};

	public String figure() {
		while (!nodes.isEmpty()) {
			SearchTreeNode node = nodes.remove();
			int nodeDepth = node.getDepth();
			if (nodeDepth != currentlyHandlingDepth) {
				currentlyHandlingDepth = nodeDepth;
				// System.out.println("Nodes count:" + nodes.size());
				// System.out.println("Reached depth " + currentlyHandlingDepth);
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
			((LinkedList<SearchTreeNode>) nodes).addAll(0, expansionList);
		}
			break;
		case BF: {
			((LinkedList<SearchTreeNode>) nodes).addAll(expansionList);
		}
			break;
		case ID: {
			break;
		}
		case UC: {
			nodes.addAll(expansionList);
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
}
