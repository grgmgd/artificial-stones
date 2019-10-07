package searching.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;

import app.SearchTreeNode;

public interface SearchingAlgorithm {
	public void enqueue(LinkedList<SearchTreeNode> nodes, SearchTreeNode node, ArrayList<SearchTreeNode> expansionList);

}
