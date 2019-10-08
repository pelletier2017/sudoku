package sudoku.solver;

import sudoku.board.Sudoku;
import sudoku.strategy.StepSolvingStrategy;
import sudoku.validator.Validator;

import java.util.Arrays;
import java.util.Objects;

public class CascadingSolver implements SudokuSolver {

    private StepSolvingStrategy[] strategies;

    private Validator validator;

    public CascadingSolver(Validator validator, StepSolvingStrategy ... strategies) {
        this.validator = validator;
        this.strategies = strategies;
    }

    @Override
    public boolean solve(Sudoku sudoku) {
        attemptToSolve(sudoku);
        return validator.isSolved(sudoku);
    }

    private void attemptToSolve(Sudoku sudoku) {
        int step = 0;
        while (step < strategies.length) {
            boolean hasChanged = strategies[step].solveStep(sudoku);
            step++;

            if (hasChanged) {
                step = 0;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CascadingSolver that = (CascadingSolver) o;
        return validator.equals(that.validator) &&
                Arrays.equals(strategies, that.strategies);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(validator);
        result = 31 * result + Arrays.hashCode(strategies);
        return result;
    }
}
