package sudoku.solver;

import org.junit.Before;
import org.junit.Test;
import sudoku.TestConstants;
import sudoku.board.CellSudoku;
import sudoku.board.Sudoku;
import sudoku.solver.strategy.EliminationStrategy;
import sudoku.solver.strategy.ScanningStrategy;
import sudoku.solver.strategy.OnlyOptionStrategy;

import static org.junit.Assert.*;

public class LoopingSolverTest {

    private LoopingSolver loopingSolver;

    @Before
    public void setup() {
        loopingSolver = new LoopingSolver(new OnlyOptionStrategy(), new ScanningStrategy(), new EliminationStrategy());
//        loopingSolver = new LoopingSolver(new OnlyOptionStrategy(), new ScanningStrategy(), new EliminationStrategy());
    }

    @Test
    public void solveBoardThatsAlreadySolvedReturnsTrue() {
        Sudoku sudoku = new CellSudoku(TestConstants.SIMPLE_SOLVED_BOARD);
        assertTrue(loopingSolver.solve(sudoku));
    }

    @Test
    public void solveImpossibleBoardReturnsFalse() {
        Sudoku sudoku = new CellSudoku(TestConstants.EMPTY_BOARD);
        assertFalse(loopingSolver.solve(sudoku));
    }

    @Test
    public void solveUnfinishedRows() {
        Sudoku sudoku = new CellSudoku(TestConstants.UNSOLVED_ROWS);
        assertTrue(loopingSolver.solve(sudoku));
    }

    @Test
    public void solveUnfinishedCols() {
        Sudoku sudoku = new CellSudoku(TestConstants.UNSOLVED_COLS);
        assertTrue(loopingSolver.solve(sudoku));
    }

    @Test
    public void solveUnfinishedBoxes() {
        Sudoku sudoku = new CellSudoku(TestConstants.UNSOLVED_BOXES);
        assertTrue(loopingSolver.solve(sudoku));
    }

    @Test
    public void solveDiagonalMissing() {
        Sudoku sudoku = new CellSudoku(TestConstants.UNSOLVED_DIAGONAL);
        assertTrue(loopingSolver.solve(sudoku));
    }

    @Test
    public void solveEasyBoard() {
        Sudoku sudoku = new CellSudoku(TestConstants.EASY_BOARD);
        assertTrue(loopingSolver.solve(sudoku));
    }

    @Test
    public void solveMediumBoard() {
        Sudoku sudoku = new CellSudoku(TestConstants.MEDIUM_BOARD);
        assertTrue(loopingSolver.solve(sudoku));
    }

    @Test
    public void solveEliminationByRows() {
        Sudoku sudoku = new CellSudoku(TestConstants.ELIMINATION_BY_ROW);
        assertTrue(loopingSolver.solve(sudoku));
    }

    @Test
    public void solveEliminationByCols() {
        Sudoku sudoku = new CellSudoku(TestConstants.ELIMINATION_BY_COL);
        assertTrue(loopingSolver.solve(sudoku));
    }

}