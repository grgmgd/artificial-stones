package searching.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import app.Operators;
import app.SearchProblem;
import app.SearchTreeNode;

public class GeneralSearch {
	private SearchTreeNode goal;
	private Queue<SearchTreeNode> nodes;
	private HashMap<String, Boolean> uniqueStates;
	SearchingAlgorithms strategy;
	SearchProblem problem;
	static int currentDepth = 0;
	int expanded = 0;
	private SearchingAlgorithms[] notOrderedStrategies = { SearchingAlgorithms.BF, SearchingAlgorithms.DF,
			SearchingAlgorithms.ID };

	public GeneralSearch(SearchProblem problem, SearchingAlgorithms strategy) {
		this.problem = problem;
		this.strategy = strategy;
		initQueue();
	};

	public void initQueue() {
		if (this.strategy.oneOf(notOrderedStrategies))
			nodes = new LinkedList<SearchTreeNode>();
		else
			nodes = new PriorityQueue<SearchTreeNode>();

		uniqueStates = new HashMap<String, Boolean>();
		SearchTreeNode node = new SearchTreeNode(problem.initialState(), null, null, 0, 0);
		nodes.add(node);
	}

	public String search() {
		if (this.strategy == SearchingAlgorithms.ID) {
			int depth = 0;
			while (true) {
				String plan = figure(depth);
				if (plan != null)
					return plan;
				depth++;
				initQueue();
			}
		}
		return figure(-1);
	}

	public String figure(int depth) {
		while (!nodes.isEmpty()) {
			SearchTreeNode node = nodes.remove();
			int nodeDepth = node.getDepth();
			if (problem.goalTest(node.state)){
				goal = node;
				return node.backtrack() + ";" + node.getCost() + ";" + this.expanded;
			}
			if (this.strategy == SearchingAlgorithms.ID && nodeDepth == depth)
				continue;
			quing(expand(node));
		}
		return null;
	}

	public SearchTreeNode getGoalNode() {
		return goal;
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

		this.expanded++;
		return expansionList;
	}

	public void quing(ArrayList<SearchTreeNode> expansionList) {
		switch (strategy) {
		case DF:
		case ID: {
			Collections.reverse(expansionList);
			((LinkedList<SearchTreeNode>) nodes).addAll(0, expansionList);
		}
			break;
		case BF:
		case UC:
		case AS1:
		case AS2:
		case GR1:
		case GR2:
			nodes.addAll(expansionList);
			break;
		default:
		}
	}

	public boolean isRepeated(SearchTreeNode newNode) {
		String state = newNode.getState().toString();
		if (uniqueStates.get(state) != null)
			return true;
		uniqueStates.put(state, true);
		return false;
	}

}
