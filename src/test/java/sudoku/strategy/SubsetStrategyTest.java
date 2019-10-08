package sudoku.strategy;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SubsetStrategyTest {

    private SubsetStrategy subsetStrategy = new SubsetStrategy();

    @Test
    public void testSubsetCreation() {
        List<Integer> optionsA = Arrays.asList(3, 6, 8);
        List<Integer> optionsB = Arrays.asList(6, 8);
        List<Integer> optionsC = Arrays.asList(3, 8);

        List<List<Integer>> listOfOptions = Arrays.asList(optionsA, optionsB, optionsC);

        Set<Integer> expected = new HashSet<>(Arrays.asList(3,6,8));
        assertEquals(expected, subsetStrategy.combinedSet(listOfOptions));
    }

    @Test
    public void solveRowSubset() {
        fail();
    }

    @Test
    public void solveColSubset() {
        fail();
    }

    @Test
    public void solveBoxSubset() {
        fail();
    }
}