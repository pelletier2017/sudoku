package sudoku.strategy;

import org.junit.Before;
import org.junit.Test;
import sudoku.TestConstants;
import sudoku.board.CellSudoku;

import static org.junit.Assert.*;

public class EliminationStrategyTest {

    private ScanningStrategy scanningStrategy = new ScanningStrategy();;

    private EliminationStrategy eliminationStrategy = new EliminationStrategy();;

    @Test
    public void solveOnlyNumInRow() {
        CellSudoku sudoku = new CellSudoku(TestConstants.ELIMINATION_BY_ROW);
        int row = 6;
        int col = 1;

        assertEquals(0, sudoku.get(row, col));
        scanningStrategy.solveStep(sudoku);
        eliminationStrategy.solveStep(sudoku);
        assertEquals(7, sudoku.get(row, col));
    }

    @Test
    public void solveOnlyNumInCol() {
        CellSudoku sudoku = new CellSudoku(TestConstants.ELIMINATION_BY_COL);
        int row = 7;
        int col = 8;

        assertEquals(0, sudoku.get(row, col));
        scanningStrategy.solveStep(sudoku);
        eliminationStrategy.solveStep(sudoku);
        assertEquals(9, sudoku.get(row, col));
    }
}