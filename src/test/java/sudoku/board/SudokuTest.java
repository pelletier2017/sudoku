package sudoku.board;

import org.junit.Test;
import sudoku.TestConstants;

import static org.junit.Assert.*;

public class SudokuTest {

    @Test
    public void testToString() {
        Sudoku sudoku = new CellSudoku(TestConstants.SIMPLE_UNSOLVED_BOARD);
        assertNotNull(sudoku.toString());
        System.out.println(sudoku);
    }
}