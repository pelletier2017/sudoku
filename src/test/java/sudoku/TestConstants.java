package sudoku;

public class TestConstants {
    public static final int[][] SIMPLE_SOLVED_BOARD = {
            {1,5,4,8,7,3,2,9,6},
            {3,8,6,5,9,2,7,1,4},
            {7,2,9,6,4,1,8,3,5},
            {8,6,3,7,2,5,1,4,9},
            {9,7,5,3,1,4,6,2,8},
            {4,1,2,9,6,8,3,5,7},
            {6,3,1,4,5,7,9,8,2},
            {5,9,8,2,3,6,4,7,1},
            {2,4,7,1,8,9,5,6,3}
    };

    public static final int[][] SIMPLE_UNSOLVED_BOARD = {
            {1,0,4,8,7,3,2,9,6},
            {3,8,6,0,9,2,7,0,4},
            {0,2,9,6,4,1,8,3,5},
            {8,6,0,7,2,5,0,4,9},
            {9,0,5,0,1,4,6,2,8},
            {4,1,2,9,6,8,3,5,7},
            {6,0,1,4,5,7,0,8,0},
            {5,0,8,2,3,6,4,7,1},
            {2,4,7,1,0,9,5,6,3}
    };

    public static final int[][] EMPTY_BOARD = new int[9][9];

    public static final int[][] UNEVEN_SIZE_BOARD = {
            {1, 2, 3},
            {2, 3, 1}
    };
}
