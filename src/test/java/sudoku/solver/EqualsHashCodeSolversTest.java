package sudoku.solver;

import org.junit.Test;
import sudoku.strategy.EliminationStrategy;
import sudoku.strategy.ScanningStrategy;
import sudoku.strategy.StepSolvingStrategy;
import sudoku.validator.Validator;
import sudoku.validator.ValidatorFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EqualsHashCodeSolversTest {

    @Test
    public void testLoopingSolver() throws Exception {
        testAllEquals(LoopingSolver.class);
    }

    @Test
    public void testCascadingSolver() throws Exception {
        testAllEquals(CascadingSolver.class);
    }

    private void testAllEquals(Class clazz) throws Exception {
        testNotEqualsDifferentStrategy(clazz);
        testEqualsSameStrategy(clazz);
        testEqualsSameValidator(clazz);
        testNotEqualsSameStepsWrongOrder(clazz);
        testHashCodeNotEquals(clazz);
        testHashCodeEquals(clazz);
    }

    private void testNotEqualsDifferentStrategy(Class clazz) throws Exception {
        Validator validator = new ValidatorFactory().getValidator();
        StepSolvingStrategy stepA = new ScanningStrategy();
        StepSolvingStrategy stepB = new EliminationStrategy();

        SudokuSolver solverA = solverFromClass(clazz, validator, stepA);
        SudokuSolver solverB = solverFromClass(clazz, validator, stepB);

        assertNotEquals(solverA, solverB);
    }

    private void testEqualsSameStrategy(Class clazz) throws Exception {
        Validator validator = new ValidatorFactory().getValidator();
        StepSolvingStrategy stepA = new ScanningStrategy();
        StepSolvingStrategy stepB = new ScanningStrategy();

        SudokuSolver solverA = solverFromClass(clazz, validator, stepA);
        SudokuSolver solverB = solverFromClass(clazz, validator, stepB);

        assertEquals(solverA, solverB);
    }

    private void testEqualsSameValidator(Class clazz) throws Exception {
        Validator validatorA = new ValidatorFactory().getValidator();
        Validator validatorB = new ValidatorFactory().getValidator();
        StepSolvingStrategy stepA = new ScanningStrategy();
        StepSolvingStrategy stepB = new ScanningStrategy();

        SudokuSolver solverA = solverFromClass(clazz, validatorA, stepA);
        SudokuSolver solverB = solverFromClass(clazz, validatorB, stepB);
        assertEquals(solverA, solverB);
    }

    private void testNotEqualsSameStepsWrongOrder(Class clazz) throws Exception {
        Validator validator = new ValidatorFactory().getValidator();
        StepSolvingStrategy stepA = new ScanningStrategy();
        StepSolvingStrategy stepB = new EliminationStrategy();

        SudokuSolver solverA = solverFromClass(clazz, validator, stepA, stepB);
        SudokuSolver solverB = solverFromClass(clazz, validator, stepB, stepA);
        assertNotEquals(solverA, solverB);
    }

    private void testHashCodeNotEquals(Class clazz) throws Exception {
        Validator validator = new ValidatorFactory().getValidator();
        StepSolvingStrategy stepA = new ScanningStrategy();
        StepSolvingStrategy stepB = new EliminationStrategy();

        SudokuSolver solverA = solverFromClass(clazz, validator, stepA);
        SudokuSolver solverB = solverFromClass(clazz, validator, stepB);

        assertNotEquals(solverA.hashCode(), solverB.hashCode());
    }

    private void testHashCodeEquals(Class clazz) throws Exception {
        Validator validator = new ValidatorFactory().getValidator();
        StepSolvingStrategy stepA = new ScanningStrategy();
        StepSolvingStrategy stepB = new ScanningStrategy();

        SudokuSolver solverA = solverFromClass(clazz, validator, stepA);
        SudokuSolver solverB = solverFromClass(clazz, validator, stepB);

        assertEquals(solverA.hashCode(), solverB.hashCode());
    }

    private SudokuSolver solverFromClass(Class clazz, Validator validator, StepSolvingStrategy ... stepSolvingStrategies) throws Exception {
        return (SudokuSolver) clazz.getDeclaredConstructor(Validator.class, StepSolvingStrategy[].class).newInstance(validator, stepSolvingStrategies);
    }
}
