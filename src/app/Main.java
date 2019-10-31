package app;

import searching.algorithms.SearchingAlgorithms;
import searching.algorithms.GeneralSearch;

public class Main {

	/**
	 * The method uses search to try to formulate a winning plan with the
	 * corresponding cost and number of nodes explored
	 * 
	 * @param grid      is a string representing the grid to perform the search on
	 * @param strategy  is a string indicating the search strategy to be applied
	 *                  [BF, DF, ID, UC, GRi, ASi]
	 * @param visualize is a boolean parameter which, when set to true, results in
	 *                  your program side-effecting a visual presentation of the
	 *                  grid as it undergoes the different steps of the discovered
	 *                  solution
	 * @return String representi`ng the formulated plan with a cost and number of
	 *         nodes in format: plan;cost;nodes
	 */

	public static String solve(String grid, SearchingAlgorithms strategy, Boolean visualize) {
		EndGame endGame = new EndGame(grid);
		GeneralSearch generalSearch = new GeneralSearch(endGame, strategy);
		String plan = generalSearch.search();

		if (plan.length() > 0) {
			int nodes = generalSearch.getNodeCount();

			if (visualize) {
				// TODO: run the visualization module
			}

			return plan + ";" + nodes;
		}

		return "There is no solution";
	}

	public static String solve(String grid, String strategy, Boolean visualize) {
		return solve(grid, SearchingAlgorithms.valueOf(strategy), visualize);
	}

	public static void main(String[] args) throws Exception {
		String[] grids = { "5,5;1,2;3,1;0,2,1,1,2,1,2,2,4,0;0,3,3,0,3,2,3,4,4,3", "3,3;0,0;1,1;0,1,2,0;1,0",
				"8,8;0,3;4,4;1,1,2,2,3,3,5,0,5,5;1,0,0,1,0,5,2,4,3,1,4,1,4,3",
				"15,15;12,13;5,7;7,0,9,14,14,8,5,8,8,9,8,4;6,6,4,3,10,2,7,4,3,11,10,0",
				"11,11;9,5;7,1;9,0,8,8,9,1,8,4,2,3,9,10;2,0,0,10,6,3,10,6,6,2",
				"14,14;2,13;12,7;8,6,9,4,7,1,4,4,4,7,2,3;8,13,0,4,0,8,5,7,10,0",
				"15,15;12,13;5,7;7,0,9,14,14,8,5,8,8,9,8,4;6,6,4,3,10,2,7,4,3,11" };
		SearchingAlgorithms strategy = SearchingAlgorithms.BF;

		int EXPLORE = 3;
		System.out.println("Exploring: " + grids[EXPLORE]);

		long startTime = System.currentTimeMillis();
		String solution = solve(grids[EXPLORE], strategy, true);
		long endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) + " milliseconds");
		System.out.println(solution);
	}
}