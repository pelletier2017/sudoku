package sudoku.solver;

import org.junit.Before;
import org.junit.Test;
import sudoku.TestConstants;
import sudoku.board.CellSudoku;
import sudoku.board.Sudoku;
import sudoku.strategy.EliminationStrategy;
import sudoku.strategy.ScanningStrategy;
import sudoku.strategy.OnlyOptionStrategy;
import sudoku.strategy.StepSolvingStrategy;
import sudoku.validator.Validator;
import sudoku.validator.ValidatorFactory;

import static org.junit.Assert.*;

public class LoopingSolverTest {

    private LoopingSolver loopingSolver;

    @Before
    public void setup() {
        Validator validator = new ValidatorFactory().getValidator();
        StepSolvingStrategy[] stepSolvingStrategies = {
                new OnlyOptionStrategy(),
                new ScanningStrategy(),
                new EliminationStrategy()
        };
        loopingSolver = new LoopingSolver(validator, stepSolvingStrategies);
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