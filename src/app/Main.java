package app;

import searching.algorithms.SearchingAlgorithms;
import java.util.concurrent.TimeUnit;
import searching.algorithms.GeneralSearch;

public class Main {

	/**
	 * The method uses searching to try and formulate a winning plan with the
	 * corresponding cost and number of nodes explored during search.
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
	 * @throws InterruptedException
	 */

	public static String solve(String grid, SearchingAlgorithms strategy, Boolean visualize)
			throws InterruptedException {
		EndGame endGame = new EndGame(grid, strategy);
		GeneralSearch generalSearch = new GeneralSearch(endGame, strategy);
		String plan = generalSearch.search();

		if (plan != null && plan.length() > 0) {
			if (visualize) {
				new Visualization(generalSearch.getGoalNode().pathFromRoot(), grid);
			}

			return plan;
		}

		return "There is no solution";
	}

	public static String solve(String grid, String strategy, Boolean visualize) {
		try {
			return solve(grid, SearchingAlgorithms.valueOf(strategy), visualize);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void main(String[] args) throws Exception {
		String[] grids = { "5,5;1,2;3,1;0,2,1,1,2,1,2,2,4,0,2,4;0,4",
				"8,8;0,3;4,4;1,1,2,2,3,3,5,0,5,5,0,7;1,0,0,1,0,5,2,4,3,1,4,1,4,3",
				"15,15;12,13;5,7;7,0,9,14,14,8,5,8,8,9,8,4;6,6,4,3,10,2,7,4,3,11,10,0",
				"11,11;9,5;7,1;9,0,8,8,9,1,8,4,2,3,9,10;2,0,0,10,6,3,10,6,6,2",
				"14,14;2,13;12,7;8,6,9,4,7,1,4,4,4,7,2,3;8,13,0,4,0,8,5,7,10,0",
				"15,15;12,13;5,7;7,0,9,14,14,8,5,8,8,9,8,4;0,0",
				"13,13;4,2;2,4;6,1,1,10,8,4,9,2,2,8,9,4;6,4,3,4,3,11,1,12,1,9" };
		SearchingAlgorithms strategy = SearchingAlgorithms.AS1;
		int EXPLORE = 0;
		long startTime = System.currentTimeMillis();
		String solution = solve(grids[EXPLORE], strategy, true);
		System.out.println("Exploring: " + grids[EXPLORE] + " With: " + strategy.toString());
		long heapSize = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("Memory used " + formatSize(heapSize));
		long solutionTime = System.currentTimeMillis() - startTime;
		long minutes = TimeUnit.MILLISECONDS.toMinutes(solutionTime);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(solutionTime);
		System.out.println(
				"Solution generation took " + minutes + "m " + (minutes > 0 ? seconds - (minutes * 60) : seconds) + "s "
						+ (seconds > 0 ? solutionTime - (seconds * 1000) : solutionTime) + "ms");
		System.out.println(solution);
	}

	public static String formatSize(long v) {
		if (v < 1024)
			return v + " B";
		int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
		return String.format("%.1f %sB", (double) v / (1L << (z * 10)), " KMGTPE".charAt(z));
	}
}