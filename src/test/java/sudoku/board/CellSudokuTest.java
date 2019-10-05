package sudoku.board;

import org.junit.Test;
import sudoku.TestConstants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CellSudokuTest {

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentWithUnevenRowsAndCols() {
        Sudoku sudoku = new CellSudoku(TestConstants.UNEVEN_SIZE_BOARD);
    }

    @Test
    public void testSize() {
        Sudoku sudoku = new CellSudoku(TestConstants.SIMPLE_SOLVED_BOARD);
        assertEquals(9, sudoku.size());
    }

    @Test
    public void testGetOnEmptyCell() {
        Sudoku sudoku = new CellSudoku(TestConstants.EMPTY_BOARD);
        assertEquals(0, sudoku.get(0,0));
        assertEquals(0, sudoku.get(4,5));
    }

    @Test
    public void testGetOnNonEmptyCell() {
        Sudoku sudoku = new CellSudoku(TestConstants.SIMPLE_SOLVED_BOARD);
        assertEquals(1, sudoku.get(0,0));
    }

    @Test
    public void testSettingEmptyCellValue() {
        Sudoku sudoku = new CellSudoku(TestConstants.EMPTY_BOARD);
        int row = 3;
        int col = 4;
        assertEquals(0, sudoku.get(row,col));

        sudoku.set(row, col, 9);

        assertEquals(9, sudoku.get(row, col));
    }

    @Test
    public void testSettingEmptyCellOptions() {
        CellSudoku sudoku = new CellSudoku(TestConstants.EMPTY_BOARD);
        int row = 3;
        int col = 4;
        assertEquals(0, sudoku.get(row,col));

        sudoku.set(row, col, 9);

        assertEquals(Collections.emptyList(), sudoku.getOptions(row, col));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSettingNonEmptyCell() {
        Sudoku sudoku = new CellSudoku(TestConstants.SIMPLE_SOLVED_BOARD);
        sudoku.set(0, 0, 7);
    }

    @Test
    public void testOptionsEmptyCell() {
        CellSudoku sudoku = new CellSudoku(TestConstants.EMPTY_BOARD);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        assertEquals(expected, sudoku.getOptions(0, 0));
    }

    @Test
    public void testOptionsNonEmptyCell() {
        CellSudoku sudoku = new CellSudoku(TestConstants.SIMPLE_SOLVED_BOARD);

        assertEquals(Collections.emptyList(), sudoku.getOptions(0, 0));
    }
}