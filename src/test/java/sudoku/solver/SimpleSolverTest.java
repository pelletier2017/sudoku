package sudoku.solver;

import org.junit.Before;
import org.junit.Test;
import sudoku.TestConstants;
import sudoku.board.CellSudoku;
import sudoku.board.Sudoku;
import sudoku.solver.strategy.NarrowDownRowsColsBoxes;
import sudoku.solver.strategy.OnlyOptionStrategy;

import static org.junit.Assert.*;

public class SimpleSolverTest {

    private SimpleSolver simpleSolver;

    @Before
    public void setup() {
        simpleSolver = new SimpleSolver(new OnlyOptionStrategy(), new NarrowDownRowsColsBoxes());
    }

    @Test
    public void solveBoardThatsAlreadySolvedReturnsTrue() {
        Sudoku sudoku = new CellSudoku(TestConstants.SIMPLE_SOLVED_BOARD);
        assertTrue(simpleSolver.solve(sudoku));
    }

    @Test
    public void solveImpossibleBoardReturnsFalse() {
        Sudoku sudoku = new CellSudoku(TestConstants.EMPTY_BOARD);
        assertFalse(simpleSolver.solve(sudoku));
    }

    @Test
    public void solveUnfinishedRows() {
        Sudoku sudoku = new CellSudoku(TestConstants.UNSOLVED_ROWS);
        assertTrue(simpleSolver.solve(sudoku));
    }

    @Test
    public void solveUnfinishedCols() {
        Sudoku sudoku = new CellSudoku(TestConstants.UNSOLVED_COLS);
        assertTrue(simpleSolver.solve(sudoku));
    }

    @Test
    public void solveUnfinishedBoxes() {
        Sudoku sudoku = new CellSudoku(TestConstants.UNSOLVED_BOXES);
        assertTrue(simpleSolver.solve(sudoku));
    }

    @Test
    public void solveDiagonalMissing() {
        Sudoku sudoku = new CellSudoku(TestConstants.UNSOLVED_DIAGONAL);
        assertTrue(simpleSolver.solve(sudoku));
    }

}