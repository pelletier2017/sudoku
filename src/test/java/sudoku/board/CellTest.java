package sudoku.board;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CellTest {

    private static final List<Integer> DEFAULT_OPTIONS = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    @Test
    public void testSimpleGet() {
        Cell cell = new Cell(4);

        assertEquals(4, cell.getValue());
    }

    @Test
    public void testEmptyGet() {
        Cell cell = new Cell();

        assertEquals(0, cell.getValue());
    }

    @Test
    public void testSetValueOnEmptyCell() {
        Cell cell = new Cell();

        cell.setValue(3);

        assertEquals(3, cell.getValue());
        assertEquals(Collections.EMPTY_LIST, cell.getOptions());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetValueOnNonEmptyCell() {
        Cell cell = new Cell(4);

        cell.setValue(3);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableOptions() {
        Cell cell = new Cell();
        List<Integer> options = cell.getOptions();
        options.add(4);
    }

    @Test
    public void testNonEmptyCellOptions() {
        Cell cell = new Cell(3);

        assertEquals(Collections.EMPTY_LIST, cell.getOptions());
    }

    @Test
    public void testEmptyCellOptions() {
        Cell cell = new Cell();

        assertEquals(DEFAULT_OPTIONS, cell.getOptions());
    }

    @Test
    public void testRemoveOptionThatDoesExistShouldReturnTrue() {
        Cell cell = new Cell();

        assertTrue(cell.removeOption(3));
        assertEquals(Arrays.asList(1, 2, 4, 5, 6, 7, 8, 9), cell.getOptions());
    }

    @Test
    public void testRemoveOptionThatDoesntExistShouldReturnFalse() {
        Cell cell = new Cell();

        cell.removeOption(3);
        assertFalse(cell.removeOption(3));
    }
}