package searching.algorithms;

import java.util.LinkedList;

import app.SearchTreeNode;

public interface SearchingAlgorithm {
	public SearchTreeNode search(SearchTreeNode node);

	public LinkedList<SearchTreeNode> enqueue(SearchTreeNode node);
}
