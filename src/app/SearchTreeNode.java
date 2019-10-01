package app.Tree;

/**
 * SearchTreeNode
 */

public class SearchTreeNode {
    public State state;
    public SearchTreeNode parent;
    public String leadingOperator;
    public int cost;
    public int depth;

    public SearchTreeNode(State state, SearchTreeNode parent, String leadingOperator, int cost, int depth) {
        this.state = state;
        this.parent = parent;
        this.leadingOperator = leadingOperator;
        this.cost = cost;
        this.depth = depth;
    }
}