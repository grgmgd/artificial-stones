package searching.algorithms;

import java.util.LinkedList;

import app.SearchTreeNode;

public class DepthFirst implements SearchingAlgorithm {

	@Override
	public void enqueue(LinkedList<SearchTreeNode> nodes, SearchTreeNode node, SearchTreeNode[] expansionList) {
		for (int i = expansionList.length - 1; i >= 0; i--) {
			SearchTreeNode searchTreeNode = expansionList[i];
			if (searchTreeNode != null)
				nodes.addFirst(searchTreeNode);
		}
	}

}
