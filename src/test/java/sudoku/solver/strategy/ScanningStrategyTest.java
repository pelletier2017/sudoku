package sudoku.solver.strategy;

import org.junit.Before;
import org.junit.Test;
import sudoku.TestConstants;
import sudoku.board.CellSudoku;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScanningStrategyTest {

    private ScanningStrategy scanningStrategy;

    private static final List<Integer> OPTIONS_WITHOUT_TWO = Arrays.asList(1,3,4,5,6,7,8,9);

    private static final List<Integer> DEFAULT_OPTIONS = Arrays.asList(1,2,3,4,5,6,7,8,9);

    @Before
    public void setup() {
        scanningStrategy = new ScanningStrategy();
    }

    @Test
    public void narrowDownRows() {
        CellSudoku sudoku = new CellSudoku(TestConstants.NARROW_DOWN_TO_ONE);

        scanningStrategy.solveStep(sudoku);

        assertEquals(OPTIONS_WITHOUT_TWO, sudoku.getOptions(0,5));
        assertEquals(OPTIONS_WITHOUT_TWO, sudoku.getOptions(0,6));
    }

    @Test
    public void narrowDownCols() {
        CellSudoku sudoku = new CellSudoku(TestConstants.NARROW_DOWN_TO_ONE);

        scanningStrategy.solveStep(sudoku);

        assertEquals(OPTIONS_WITHOUT_TWO, sudoku.getOptions(6,3));
        assertEquals(OPTIONS_WITHOUT_TWO, sudoku.getOptions(6,4));
    }

    @Test
    public void narowDownBoxes() {
        CellSudoku sudoku = new CellSudoku(TestConstants.NARROW_DOWN_TO_ONE);

        scanningStrategy.solveStep(sudoku);

        assertEquals(OPTIONS_WITHOUT_TWO, sudoku.getOptions(2,0));
        assertEquals(OPTIONS_WITHOUT_TWO, sudoku.getOptions(2,1));
        assertEquals(OPTIONS_WITHOUT_TWO, sudoku.getOptions(2,2));
    }

    @Test
    public void dontChangeAllOptions() {
        CellSudoku sudoku = new CellSudoku(TestConstants.NARROW_DOWN_TO_ONE);

        scanningStrategy.solveStep(sudoku);

        assertEquals(DEFAULT_OPTIONS, sudoku.getOptions(5,0));
        assertEquals(DEFAULT_OPTIONS, sudoku.getOptions(5,2));
        assertEquals(DEFAULT_OPTIONS, sudoku.getOptions(6,0));
        assertEquals(DEFAULT_OPTIONS, sudoku.getOptions(6,2));
        assertEquals(DEFAULT_OPTIONS, sudoku.getOptions(6,5));
        assertEquals(DEFAULT_OPTIONS, sudoku.getOptions(7,6));
        assertEquals(DEFAULT_OPTIONS, sudoku.getOptions(7,7));
        assertEquals(DEFAULT_OPTIONS, sudoku.getOptions(7,8));
    }
}