package searching.algorithms;

import java.util.LinkedList;

import app.SearchProblem;
import app.SearchTreeNode;
import app.State;
import app.Operators;

public class GeneralSearch {
	LinkedList<SearchTreeNode> nodes;
	SearchingAlgorithms strategy;
	SearchProblem problem;
	static int currentlyHandlingDepth = 0;

	public GeneralSearch(SearchProblem problem, SearchingAlgorithms strategy) {
		nodes = new LinkedList<SearchTreeNode>();
		SearchTreeNode node = new SearchTreeNode(problem.initialState(), null, null, 0, 0);
		nodes.add(node);
		this.problem = problem;
		this.strategy = strategy;
	};

	public SearchTreeNode figure() {
		do {
			SearchTreeNode node = nodes.remove(0);
			int nodeDepth = node.getDepth();
			if (nodeDepth != currentlyHandlingDepth) {
				currentlyHandlingDepth = nodeDepth;
				System.out.println("Reached depth " + currentlyHandlingDepth);
			}
			if (problem.goalTest(node.state))
				return node;
			SearchTreeNode[] expansionList = expand(node);
			quing(nodes, node, expansionList);
		} while (!nodes.isEmpty());
		return null;
	}

	public SearchTreeNode[] expand(SearchTreeNode node) {
		State currentState = node.getState();
		int currentCost = node.getCost();
		int currentDepth = node.getDepth();
		Operators[] operators = Operators.values();
		SearchTreeNode[] expansionList = new SearchTreeNode[operators.length];
		int index = 0;
		for (Operators opr : operators) {
			State state = problem.transitionFunction(currentState, opr);
			SearchTreeNode expansionNode;
			if (state == null)
				continue;
			expansionNode = new SearchTreeNode(state, node, opr, currentCost, currentDepth + 1);
			if (!isARepeatedNode(expansionNode)) {
				expansionList[index] = new SearchTreeNode(state, node, opr, currentCost, currentDepth + 1);
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

	public boolean isARepeatedNode(SearchTreeNode newNode) {
		State newNodeState = newNode.getState();
		SearchTreeNode newNodeParent = newNode.getParent();
		if (newNodeState != null && newNodeParent != null)
			return reccursiveRepeatedStatesTest(newNodeState, newNodeParent);
		else
			return false;
	}

	public boolean reccursiveRepeatedStatesTest(State state, SearchTreeNode node) {
		State nodeState = node.getState();
		SearchTreeNode nodeParent = node.getParent();
		return nodeState != null ? state.equals(nodeState)
				: false || nodeParent != null ? reccursiveRepeatedStatesTest(state, nodeParent) : false;
	}

}
