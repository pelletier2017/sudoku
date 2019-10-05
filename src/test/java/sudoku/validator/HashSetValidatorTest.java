package sudoku.validator;

import org.junit.Test;
import sudoku.TestConstants;
import sudoku.board.CellSudoku;
import sudoku.board.Sudoku;

import static org.junit.Assert.*;

public class HashSetValidatorTest {

    @Test
    public void testIsValidSimpleSolved() {
        Sudoku sudoku = new CellSudoku(TestConstants.SIMPLE_SOLVED_BOARD);
        Validator validator = new HashSetValidator();

        assertTrue(validator.isValidBoard(sudoku));
    }

    @Test
    public void testIsValidForUnsolvedButValidBoard() {
        Sudoku sudoku = new CellSudoku(TestConstants.SIMPLE_UNSOLVED_BOARD);
        Validator validator = new HashSetValidator();

        assertTrue(validator.isValidBoard(sudoku));
    }

    @Test
    public void testIsSolvedSimpleSolved() {
        Sudoku sudoku = new CellSudoku(TestConstants.SIMPLE_SOLVED_BOARD);
        Validator validator = new HashSetValidator();

        assertTrue(validator.isValidBoard(sudoku));
    }

    @Test
    public void testIsSolvedForUnsolvedBoard() {
        Sudoku sudoku = new CellSudoku(TestConstants.SIMPLE_UNSOLVED_BOARD);
        Validator validator = new HashSetValidator();

        assertFalse(validator.isSolved(sudoku));
    }
}