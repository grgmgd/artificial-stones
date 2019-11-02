package app;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class EndGameTests {

    String grid5 = "5,5;2,2;4,2;4,0,1,2,3,0,2,1,4,1,2,4;3,2,0,0,3,4,4,3,4,4";
    String grid6 = "6,6;5,3;0,1;4,3,2,1,3,0,1,2,4,5,1,1;1,3,3,3,1,0,1,4,2,2";
    String grid7 = "7,7;5,4;0,1;5,0,5,6,3,1,4,3,1,2,6,3;2,5,2,6,1,0,5,5,6,5";
    String grid8 = "8,8;7,2;2,2;7,6,2,3,3,0,0,1,6,0,5,5;7,3,4,4,1,6,2,4,2,6";
    String grid9 = "9,9;2,5;3,3;6,2,5,1,3,0,2,8,8,3,0,5;5,4,5,5,1,6,6,3,4,8";
    String grid10 = "10,10;5,1;0,4;3,1,6,8,1,2,9,2,1,5,0,8;7,8,7,6,3,3,6,0,3,8";
    String grid11 = "11,11;9,5;7,1;9,0,8,8,9,1,8,4,2,3,9,10;2,0,0,10,6,3,10,6,6,2";
    String grid12 = "12,12;0,6;9,11;8,3,3,0,11,8,7,4,7,7,10,2;2,8,11,2,2,6,4,6,9,8";
    String grid13 = "13,13;4,2;2,4;6,1,1,10,8,4,9,2,2,8,9,4;6,4,3,4,3,11,1,12,1,9";
    String grid14 = "14,14;2,13;12,7;8,6,9,4,7,1,4,4,4,7,2,3;8,13,0,4,0,8,5,7,10,0";
    String grid15 = "15,15;12,13;5,7;7,0,9,14,14,8,5,8,8,9,8,4;6,6,4,3,10,2,7,4,3,11";

    @Test(timeout = 70000)
    public void testa1() {
        String solution = Main.solve(grid5, "BF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
    }

    @Test(timeout = 70000)
    public void testa2() {
        String solution = Main.solve(grid6, "BF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
    }

    @Test(timeout = 70000)
    public void testa3() {
        String solution = Main.solve(grid7, "BF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
    }

    @Test(timeout = 70000)
    public void testa4() {
        String solution = Main.solve(grid8, "BF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
    }

    @Test(timeout = 70000)
    public void testa5() {
        String solution = Main.solve(grid9, "BF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
    }

    @Test(timeout = 70000)
    public void testa6() {
        String solution = Main.solve(grid10, "BF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
    }

    @Test(timeout = 70000)
    public void testa7() {
        String solution = Main.solve(grid11, "BF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
    }

    @Test(timeout = 70000)
    public void testa8() {
        String solution = Main.solve(grid12, "BF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
    }

    @Test(timeout = 70000)
    public void testa9() {
        String solution = Main.solve(grid13, "BF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid13, solution));
    }

    @Test(timeout = 70000)
    public void testa10() {
        String solution = Main.solve(grid14, "BF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid14, solution));
    }

    @Test(timeout = 70000)
    public void testa11() {
        String solution = Main.solve(grid15, "BF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid15, solution));
    }

    @Test(timeout = 70000)
    public void testb1() {
        String solution = Main.solve(grid5, "DF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
    }

    @Test(timeout = 70000)
    public void testb2() {
        String solution = Main.solve(grid6, "DF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
    }

    @Test(timeout = 70000)
    public void testb3() {
        String solution = Main.solve(grid7, "DF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
    }

    @Test(timeout = 70000)
    public void testb4() {
        String solution = Main.solve(grid8, "DF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
    }

    @Test(timeout = 70000)
    public void testb5() {
        String solution = Main.solve(grid9, "DF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
    }

    @Test(timeout = 70000)
    public void testb6() {
        String solution = Main.solve(grid10, "DF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
    }

    @Test(timeout = 70000)
    public void testb7() {
        String solution = Main.solve(grid11, "DF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
    }

    @Test(timeout = 70000)
    public void testb8() {
        String solution = Main.solve(grid12, "DF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
    }

    @Test(timeout = 70000)
    public void testb9() {
        String solution = Main.solve(grid13, "DF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid13, solution));
    }

    @Test(timeout = 70000)
    public void testb10() {
        String solution = Main.solve(grid14, "DF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid14, solution));
    }

    @Test(timeout = 70000)
    public void testb11() {
        String solution = Main.solve(grid15, "DF", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid15, solution));
    }

    @Test(timeout = 70000)
    public void testc1() {
        String solution = Main.solve(grid5, "ID", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
    }

    @Test(timeout = 70000)
    public void testc2() {
        String solution = Main.solve(grid6, "ID", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
    }

    @Test(timeout = 70000)
    public void testc3() {
        String solution = Main.solve(grid7, "ID", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
    }

    @Test(timeout = 70000)
    public void testc4() {
        String solution = Main.solve(grid8, "ID", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
    }

    @Test(timeout = 70000)
    public void testc5() {
        String solution = Main.solve(grid9, "ID", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
    }

    @Test(timeout = 80000)
    public void testc6() {
        String solution = Main.solve(grid10, "ID", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
    }

    @Test(timeout = 80000)
    public void testc7() {
        String solution = Main.solve(grid11, "ID", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
    }

    @Test(timeout = 80000)
    public void testc8() {
        String solution = Main.solve(grid12, "ID", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
    }

    @Test(timeout = 80000)
    public void testc9() {
        String solution = Main.solve(grid13, "ID", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid13, solution));
    }

    @Test(timeout = 80000)
    public void testc10() {
        String solution = Main.solve(grid14, "ID", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid14, solution));
    }

    @Test(timeout = 80000)
    public void testc11() {
        String solution = Main.solve(grid15, "ID", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid15, solution));
    }

    @Test(timeout = 70000)
    public void testd1() {
        String solution = Main.solve(grid5, "GR1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
    }

    @Test(timeout = 70000)
    public void testd2() {
        String solution = Main.solve(grid6, "GR1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
    }

    @Test(timeout = 70000)
    public void testd3() {
        String solution = Main.solve(grid7, "GR1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
    }

    @Test(timeout = 70000)
    public void testd4() {
        String solution = Main.solve(grid8, "GR1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
    }

    @Test(timeout = 70000)
    public void testd5() {
        String solution = Main.solve(grid9, "GR1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
    }

    @Test(timeout = 70000)
    public void testd6() {
        String solution = Main.solve(grid10, "GR1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
    }

    @Test(timeout = 70000)
    public void testd7() {
        String solution = Main.solve(grid11, "GR1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
    }

    @Test(timeout = 70000)
    public void testd8() {
        String solution = Main.solve(grid12, "GR1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
    }

    @Test(timeout = 70000)
    public void testd9() {
        String solution = Main.solve(grid13, "GR1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid13, solution));
    }

    @Test(timeout = 70000)
    public void testd10() {
        String solution = Main.solve(grid14, "GR1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid14, solution));
    }

    @Test(timeout = 70000)
    public void testd11() {
        String solution = Main.solve(grid15, "GR1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid15, solution));
    }

    @Test(timeout = 70000)
    public void teste1() {
        String solution = Main.solve(grid5, "GR2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
    }

    @Test(timeout = 70000)
    public void teste2() {
        String solution = Main.solve(grid6, "GR2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
    }

    @Test(timeout = 70000)
    public void teste3() {
        String solution = Main.solve(grid7, "GR2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
    }

    @Test(timeout = 70000)
    public void teste4() {
        String solution = Main.solve(grid8, "GR2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
    }

    @Test(timeout = 70000)
    public void teste5() {
        String solution = Main.solve(grid9, "GR2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
    }

    @Test(timeout = 70000)
    public void teste6() {
        String solution = Main.solve(grid10, "GR2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
    }

    @Test(timeout = 70000)
    public void teste7() {
        String solution = Main.solve(grid11, "GR2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
    }

    @Test(timeout = 70000)
    public void teste8() {
        String solution = Main.solve(grid12, "GR2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
    }

    @Test(timeout = 70000)
    public void teste9() {
        String solution = Main.solve(grid13, "GR2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid13, solution));
    }

    @Test(timeout = 70000)
    public void teste10() {
        String solution = Main.solve(grid14, "GR2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid14, solution));
    }

    @Test(timeout = 70000)
    public void teste11() {
        String solution = Main.solve(grid15, "GR2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid15, solution));
    }

    @Test(timeout = 70000)
    public void testf1() {
        String solution = Main.solve(grid5, "UC", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
    }

    @Test(timeout = 70000)
    public void testf2() {
        String solution = Main.solve(grid6, "UC", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
    }

    @Test(timeout = 70000)
    public void testf3() {
        String solution = Main.solve(grid7, "UC", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
    }

    @Test(timeout = 70000)
    public void testf4() {
        String solution = Main.solve(grid8, "UC", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
    }

    @Test(timeout = 70000)
    public void testf5() {
        String solution = Main.solve(grid9, "UC", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
    }

    @Test(timeout = 70000)
    public void testf6() {
        String solution = Main.solve(grid10, "UC", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
    }

    @Test(timeout = 70000)
    public void testf7() {
        String solution = Main.solve(grid11, "UC", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
    }

    @Test(timeout = 70000)
    public void testf8() {
        String solution = Main.solve(grid12, "UC", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
    }

    @Test(timeout = 70000)
    public void testf9() {
        String solution = Main.solve(grid13, "UC", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid13, solution));
    }

    @Test(timeout = 70000)
    public void testf10() {
        String solution = Main.solve(grid14, "UC", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid14, solution));
    }

    @Test(timeout = 70000)
    public void testf11() {
        String solution = Main.solve(grid15, "UC", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid15, solution));
    }

    @Test(timeout = 70000)
    public void testg1() {
        String solution = Main.solve(grid5, "AS1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
    }

    @Test(timeout = 70000)
    public void testg2() {
        String solution = Main.solve(grid6, "AS1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
    }

    @Test(timeout = 70000)
    public void testg3() {
        String solution = Main.solve(grid7, "AS1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
    }

    @Test(timeout = 70000)
    public void testg4() {
        String solution = Main.solve(grid8, "AS1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
    }

    @Test(timeout = 70000)
    public void testg5() {
        String solution = Main.solve(grid9, "AS1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
    }

    @Test(timeout = 70000)
    public void testg6() {
        String solution = Main.solve(grid10, "AS1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
    }

    @Test(timeout = 70000)
    public void testg7() {
        String solution = Main.solve(grid11, "AS1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
    }

    @Test(timeout = 70000)
    public void testg8() {
        String solution = Main.solve(grid12, "AS1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
    }

    @Test(timeout = 70000)
    public void testg9() {
        String solution = Main.solve(grid13, "AS1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid13, solution));
    }

    @Test(timeout = 70000)
    public void testg10() {
        String solution = Main.solve(grid14, "AS1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid14, solution));
    }

    @Test(timeout = 70000)
    public void testg11() {
        String solution = Main.solve(grid15, "AS1", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid15, solution));
    }

    @Test(timeout = 70000)
    public void testh1() {
        String solution = Main.solve(grid5, "AS2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
    }

    @Test(timeout = 70000)
    public void testh2() {
        String solution = Main.solve(grid6, "AS2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
    }

    @Test(timeout = 70000)
    public void testh3() {
        String solution = Main.solve(grid7, "AS2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
    }

    @Test(timeout = 70000)
    public void testh4() {
        String solution = Main.solve(grid8, "AS2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
    }

    @Test(timeout = 70000)
    public void testh5() {
        String solution = Main.solve(grid9, "AS2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
    }

    @Test(timeout = 70000)
    public void testh6() {
        String solution = Main.solve(grid10, "AS2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
    }

    @Test(timeout = 70000)
    public void testh7() {
        String solution = Main.solve(grid11, "AS2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
    }

    @Test(timeout = 70000)
    public void testh8() {
        String solution = Main.solve(grid12, "AS2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
    }

    @Test(timeout = 70000)
    public void testh9() {
        String solution = Main.solve(grid13, "AS2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid13, solution));
    }

    @Test(timeout = 70000)
    public void testh10() {
        String solution = Main.solve(grid14, "AS2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid14, solution));
    }

    @Test(timeout = 70000)
    public void testh11() {
        String solution = Main.solve(grid15, "AS2", false);

        assertTrue("The output actions do not lead to a goal state.", applyPlan(grid15, solution));
    }

    private boolean applyPlan(String grid, String solution) {
        char[][] g = convertToGrid(grid);
        String plan = solution.split(";")[0];
        plan.replace(" ", "");
        plan.replace("\n", "");
        plan.replace("\r", "");
        plan.replace("\n\r", "");
        plan.replace("\t", "");
        String[] actions = plan.split(",");
        boolean snapped = false;
        String[] imPosition = grid.split(";")[1].split(",");
        String tPosition = grid.split(";")[2];
        int[] result = new int[3];
        result[0] = Integer.parseInt(imPosition[0]);
        result[1] = Integer.parseInt(imPosition[1]);
        result[2] = 0;
        int i = 0;
        for (i = 0; i < actions.length; i++) {
            switch (actions[i]) {
            case "up":
                applyUp(g, result);
                break;
            case "down":
                applyDown(g, result);
                break;
            case "right":
                applyRight(g, result);
                break;
            case "left":
                applyLeft(g, result);
                break;
            case "collect":
                applyCollect(g, result);
                break;
            case "kill":
                applyKill(g, result);
                break;
            case "snap":
                snapped = applySnapped(tPosition, result);
                break;
            }
            if (snapped)
                break;
        }

        return snapped && i == actions.length - 1;
    }

    private boolean applySnapped(String tPosition, int[] result) {
        return (result[0] + "," + result[1]).equals(tPosition) && result[2] == 6;
    }

    private void applyKill(char[][] g, int[] result) {
        int ix = result[0];
        int iy = result[1];
        int m = g.length;
        int n = g[0].length;

        if (ix + 1 < m && g[ix + 1][iy] == 'W')
            g[ix + 1][iy] = '\u0000';

        if (ix - 1 >= 0 && g[ix - 1][iy] == 'W')
            g[ix - 1][iy] = '\u0000';

        if (iy + 1 < n && g[ix][iy + 1] == 'W')
            g[ix][iy + 1] = '\u0000';

        if (iy - 1 >= 0 && g[ix][iy - 1] == 'W')
            g[ix][iy - 1] = '\u0000';
    }

    private void applyCollect(char[][] g, int[] result) {
        if (g[result[0]][result[1]] == 'S') {
            g[result[0]][result[1]] = '\u0000';
            result[2] += 1;
        }
    }

    private void applyLeft(char[][] g, int[] result) {
        if (result[1] - 1 >= 0 && (g[result[0]][result[1] - 1] == '\u0000' || g[result[0]][result[1] - 1] == 'S'
                || (g[result[0]][result[1] - 1] == 'T' && result[2] == 6)))
            result[1] = result[1] - 1;
    }

    private void applyRight(char[][] g, int[] result) {
        int n = g[0].length;
        if (result[1] + 1 < n && (g[result[0]][result[1] + 1] == '\u0000' || g[result[0]][result[1] + 1] == 'S'
                || (g[result[0]][result[1] + 1] == 'T' && result[2] == 6)))
            result[1] = result[1] + 1;
    }

    private void applyDown(char[][] g, int[] result) {
        int m = g.length;
        if (result[0] + 1 < m && (g[result[0] + 1][result[1]] == '\u0000' || g[result[0] + 1][result[1]] == 'S'
                || (g[result[0] + 1][result[1]] == 'T' && result[2] == 6)))
            result[0] = result[0] + 1;

    }

    private void applyUp(char[][] g, int[] result) {
        if (result[0] - 1 >= 0 && (g[result[0] - 1][result[1]] == '\u0000' || g[result[0] - 1][result[1]] == 'S'
                || (g[result[0] - 1][result[1]] == 'T' && result[2] == 6)))
            result[0] = result[0] - 1;
    }

    private char[][] convertToGrid(String input) {
        String[] s = input.split(";");

        String[] dimensions = s[0].split(",");
        String[] thanos = s[2].split(",");
        String[] stones = s[3].split(",");
        String[] warriors = s[4].split(",");

        char[][] grid = new char[Integer.parseInt(dimensions[0])][Integer.parseInt(dimensions[1])];
        grid[Integer.parseInt(thanos[0])][Integer.parseInt(thanos[1])] = 'T';

        for (int i = 0; i < stones.length - 1; i += 2)
            grid[Integer.parseInt(stones[i])][Integer.parseInt(stones[i + 1])] = 'S';

        for (int i = 0; i < warriors.length - 1; i += 2)
            grid[Integer.parseInt(warriors[i])][Integer.parseInt(warriors[i + 1])] = 'W';

        return grid;
    }

}