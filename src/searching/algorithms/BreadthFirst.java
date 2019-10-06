package searching.algorithms;

import java.util.LinkedList;

import app.SearchTreeNode;
import app.State;

public class BreadthFirst implements SearchingAlgorithm {

	public LinkedList<SearchTreeNode> enqueue(SearchTreeNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchTreeNode search(SearchTreeNode node) {
		State nodeState = node.getState();
		return node;

	}

}
