package app;

import java.util.ArrayList;

public class Main {

	/**
	 * The method uses search to try to formulate a winning plan with the
	 * corresponding cost and number of nodes explored
	 * 
	 * @param grid      is a string representing the grid to perform the search on
	 * @param strategy  is a string indicating the search strategy to be applied
	 *                  [BF, DF, ID, UC, GRi, ASi]
	 * @param visualize is a boolean parameter which, when set to true, results in
	 *                  your program's side-effecting a visual presentation of the
	 *                  grid as it undergoes the different steps of the discovered
	 *                  solution
	 * @return String representing the formulated plan with a cost and number of
	 *         nodes in format: plan;cost;nodes
	 */
	public static String solve(String grid, String strategy, Boolean visualize) {

		String plan = "";
		String cost = "";
		String nodes = "";

		if (plan == null)
			return "There is no solution";

		if (visualize) {
			// TODO: run the visualization module
		}

		String formulatedPlan = plan + ';' + cost + ';' + nodes;
		return formulatedPlan;
	}

	public ArrayList<SearchTreeNode> queueing(SearchTreeNode node) {
		return null;
	}

	public void searchBreadthFirst(SearchTreeNode node) {
		
	}
	
	public void searchDepthFirst(SearchTreeNode node) {
		
	}
	
public void searchUniFormCost(SearchTreeNode node) {
		
	}

	public static void main(String[] args) throws Exception {
		String grid = "5,5;1,2;3,1;0,2,1,1,2,1,2,2,4,0,4,1;0,3,3,0,3,2,3,4,4,3";
		String strategy = "BF";
		solve(grid, strategy, true);
	}
}