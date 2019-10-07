package searching.algorithms;

import java.util.LinkedList;

import app.SearchTreeNode;

public class BreadthFirst implements GeneralSearch {

	@Override
	public void enqueue(LinkedList<SearchTreeNode> nodes, SearchTreeNode node, SearchTreeNode[] expansionList) {
		for (SearchTreeNode searchTreeNode : expansionList) {
			if (searchTreeNode != null)
				nodes.addLast(searchTreeNode);
		}
	}

}
