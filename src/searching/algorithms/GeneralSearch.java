package searching.algorithms;

import java.util.ArrayList;

import app.SearchProblem;
import app.SearchTreeNode;

public class GeneralSearch {
	ArrayList<SearchTreeNode> nodes;
	SearchingAlgorithms strategy;

	public GeneralSearch(SearchProblem problem, SearchingAlgorithms strategy) {
		nodes = new ArrayList<SearchTreeNode>();
		SearchTreeNode node = new SearchTreeNode(problem.initialState(), null, null, 0, 0);
		nodes.add(node);
		this.strategy = strategy;
	};
}
