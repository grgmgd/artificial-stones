package searching.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;

import app.SearchTreeNode;

public class BreadthFirst implements SearchingAlgorithm {

	@Override
	public void enqueue(LinkedList<SearchTreeNode> nodes, SearchTreeNode node,
			ArrayList<SearchTreeNode> expansionList) {
		for (SearchTreeNode searchTreeNode : expansionList) {
			nodes.addLast(searchTreeNode);
		}

	}

}
