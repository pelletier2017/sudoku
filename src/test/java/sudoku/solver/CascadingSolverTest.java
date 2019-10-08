package sudoku.solver;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import sudoku.board.Sudoku;
import sudoku.strategy.StepSolvingStrategy;
import sudoku.validator.Validator;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CascadingSolverTest {

    private CascadingSolver cascadingSolver;

    @Mock
    Sudoku sudoku;

    @Mock
    private Validator validator;

    @Mock
    private StepSolvingStrategy stepA;

    @Mock
    private StepSolvingStrategy stepB;

    @Mock
    private StepSolvingStrategy stepC;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void noChangesNotSolved() {
        cascadingSolver = new CascadingSolver(validator, stepA, stepB);

        when(stepA.solveStep(any())).thenReturn(false);
        when(stepB.solveStep(any())).thenReturn(false);
        when(validator.isSolved(sudoku)).thenReturn(false);

        assertFalse(cascadingSolver.solve(sudoku));
        verify(stepA, times(1)).solveStep(any());
        verify(stepB, times(1)).solveStep(any());
    }

    @Test
    public void noChangesButSolved() {
        cascadingSolver = new CascadingSolver(validator, stepA, stepB);

        when(stepA.solveStep(any())).thenReturn(false);
        when(stepB.solveStep(any())).thenReturn(false);
        when(validator.isSolved(sudoku)).thenReturn(true);

        assertTrue(cascadingSolver.solve(sudoku));
        verify(stepA, times(1)).solveStep(any());
        verify(stepB, times(1)).solveStep(any());
    }

    @Test
    public void secondStepReturnsTrueOnce() {
        cascadingSolver = new CascadingSolver(validator, stepA, stepB);

        // A -> B -> A -> B -> end
        when(stepA.solveStep(any()))
                .thenReturn(false)
                .thenReturn(false);
        when(stepB.solveStep(any()))
                .thenReturn(true)
                .thenReturn(false);
        when(validator.isSolved(sudoku)).thenReturn(true);

        assertTrue(cascadingSolver.solve(sudoku));
        verify(stepA, times(2)).solveStep(any());
        verify(stepB, times(2)).solveStep(any());
    }

    @Test
    public void thirdStepReturnsTrue() {
        cascadingSolver = new CascadingSolver(validator, stepA, stepB, stepC);

        // A -> B -> C -> A -> B -> C -> end
        when(stepA.solveStep(any()))
                .thenReturn(false)
                .thenReturn(false);
        when(stepB.solveStep(any()))
                .thenReturn(false)
                .thenReturn(false);
        when(stepC.solveStep(any()))
                .thenReturn(true)
                .thenReturn(false);

        when(validator.isSolved(sudoku)).thenReturn(true);

        assertTrue(cascadingSolver.solve(sudoku));
        verify(stepA, times(2)).solveStep(any());
        verify(stepB, times(2)).solveStep(any());
        verify(stepC, times(2)).solveStep(any());
    }

    @Test
    public void firstReturnsTrueOnce() {
        cascadingSolver = new CascadingSolver(validator, stepA, stepB, stepC);

        // A -> A -> B -> C
        when(stepA.solveStep(any()))
                .thenReturn(true)
                .thenReturn(false);
        when(stepB.solveStep(any()))
                .thenReturn(false);
        when(stepC.solveStep(any()))
                .thenReturn(false);

        when(validator.isSolved(sudoku)).thenReturn(true);

        assertTrue(cascadingSolver.solve(sudoku));
        verify(stepA, times(2)).solveStep(any());
        verify(stepB, times(1)).solveStep(any());
        verify(stepC, times(1)).solveStep(any());
    }

    @Test
    public void secondReturnsTrueOnce() {
        cascadingSolver = new CascadingSolver(validator, stepA, stepB, stepC);

        // A -> B -> A -> B -> C
        when(stepA.solveStep(any()))
                .thenReturn(false)
                .thenReturn(false);
        when(stepB.solveStep(any()))
                .thenReturn(true)
                .thenReturn(false);
        when(stepC.solveStep(any()))
                .thenReturn(false);

        when(validator.isSolved(sudoku)).thenReturn(true);

        assertTrue(cascadingSolver.solve(sudoku));
        verify(stepA, times(2)).solveStep(any());
        verify(stepB, times(2)).solveStep(any());
        verify(stepC, times(1)).solveStep(any());
    }

}