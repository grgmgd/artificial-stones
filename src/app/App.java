package app;

public class App {

    /**
     * The method uses search to try to formulate a winning plan with the
     * corresponding cost and number of nodes explored
     * 
     * @param grid      is a string representing the grid to perform the search on
     * @param strategy  is a string indicating the search strategy to be applied
     *                  [BF, DF, ID, UC, GRi, ASi]
     * @param visualize is a boolean parameter which, when set to true, results in
     *                  your pro- gram’s side-effecting a visual presentation of the
     *                  grid as it undergoes the diffe- rent steps of the discovered
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

    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");
    }
}