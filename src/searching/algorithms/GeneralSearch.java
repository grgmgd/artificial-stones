package searching.algorithms;

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
			SearchTreeNode node = nodes.remove(0);
			int nodeDepth = node.getDepth();
			if (nodeDepth != currentlyHandlingDepth) {
				currentlyHandlingDepth = nodeDepth;
				// System.out.println("Nodes count:" + nodes.size());
				// System.out.println("Reached depth " + currentlyHandlingDepth);
			}
			if (problem.goalTest(node.state))
				return node.backtrack() + ";" + node.backtrackCost();
			SearchTreeNode[] expansionList = expand(node);
			quing(nodes, node, expansionList);
		}

		return null;
	}

	public SearchTreeNode[] expand(SearchTreeNode node) {
		Operators[] operators = Operators.values();
		SearchTreeNode[] expansionList = new SearchTreeNode[operators.length];
		int index = 0;
		for (Operators opr : operators) {
			SearchTreeNode expansionNode = problem.transitionFunction(node, opr);
			if (expansionNode != null && !isRepeated(expansionNode)) {
				expansionList[index] = expansionNode;
				index++;
			}
		}
		return expansionList;
	}

	public void quing(LinkedList<SearchTreeNode> n, SearchTreeNode node, SearchTreeNode[] expansionList) {
		switch (strategy) {
		case DF: {
			for (int i = expansionList.length - 1; i >= 0; i--) {
				SearchTreeNode searchTreeNode = expansionList[i];
				if (searchTreeNode != null)
					n.addFirst(searchTreeNode);
			}
			break;
		}
		case BF: {
			for (SearchTreeNode searchTreeNode : expansionList)
				if (searchTreeNode != null)
					n.addLast(searchTreeNode);
			break;
		}
		case ID: {
			break;
		}
		case UC: {
			break;
		}
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
