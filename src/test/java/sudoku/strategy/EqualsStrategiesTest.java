package sudoku.strategy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EqualsStrategiesTest {

    @Test
    public void testElimination() throws Exception {
        testAllEquals(EliminationStrategy.class);
    }

    @Test
    public void testOnlyOption() throws Exception {
        testAllEquals(OnlyOptionStrategy.class);
    }

    @Test
    public void testScanning() throws Exception {
        testAllEquals(ScanningStrategy.class);
    }

    @Test
    public void testSubset() throws Exception {
        testAllEquals(SubsetStrategy.class);
    }

    private void testAllEquals(Class clazz) throws Exception {
        testEquals(clazz);
        notEqualsNull(clazz);
        notEqualsOtherClass(clazz);
        equalHashCode(clazz);
    }

    public void testEquals(Class clazz) throws Exception {
        StepSolvingStrategy a = (StepSolvingStrategy) clazz.newInstance();
        StepSolvingStrategy b = (StepSolvingStrategy) clazz.newInstance();

        assertEquals(a, b);
    }

    public void notEqualsNull(Class clazz) throws Exception {
        StepSolvingStrategy a = (StepSolvingStrategy) clazz.newInstance();
        StepSolvingStrategy b = null;
        assertNotEquals(a, b);
    }

    public void notEqualsOtherClass(Class clazz) throws Exception {
        StepSolvingStrategy a = (StepSolvingStrategy) clazz.newInstance();
        String b = "abc";
        assertNotEquals(a, b);
    }

    public void equalHashCode(Class clazz) throws Exception {
        StepSolvingStrategy a = (StepSolvingStrategy) clazz.newInstance();
        StepSolvingStrategy b = (StepSolvingStrategy) clazz.newInstance();

        assertEquals(a.hashCode(), b.hashCode());
    }
}
