package app;

import searching.algorithms.SearchingAlgorithms;

import java.util.ArrayList;

import org.javatuples.Pair;

import searching.algorithms.GeneralSearch;;

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
	 * @return String representing the formulated plan with a cost and number of
	 *         nodes in format: plan;cost;nodes
	 */
	public static String solve(String grid, SearchingAlgorithms strategy, Boolean visualize) {
		EndGame endGame = new EndGame(grid);
		GeneralSearch generalSearch = new GeneralSearch(endGame, strategy);
		SearchTreeNode endGameSolutionNode = generalSearch.figure();
		if (endGameSolutionNode != null) {
			Solution solution = new Solution("", 0, new ArrayList<SearchTreeNode>());
			solution = endGameSolutionNode.getSolution(solution);
			String plan = solution.getPlan();
			if (plan.length() == 0)
				return "There is no solution";
			if (visualize) {
				// TODO: run the visualization module
			}
			return plan;
		}
		return "There is no solution";
	}

	public static void main(String[] args) throws Exception {
		String grid = "5,5;1,2;3,1;0,2,1,1,2,1,2,2,4,0,4,1;0,3,3,0,3,2,3,4,4,3";
//		String grid = "3,3;0,0;1,1;0,1,2,0;1,0";
		SearchingAlgorithms strategy = SearchingAlgorithms.BF;
		String solution = solve(grid, strategy, true);
		System.out.println(solution);
	}
}