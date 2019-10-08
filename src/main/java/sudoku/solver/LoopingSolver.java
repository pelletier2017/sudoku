package sudoku.solver;

import sudoku.board.Sudoku;
import sudoku.strategy.StepSolvingStrategy;
import sudoku.validator.Validator;
import sudoku.validator.ValidatorFactory;

import java.util.Arrays;
import java.util.Objects;

/*
Performs each StepSolvingStrategy in a loop until either nothing has changed or the puzzle is solved.
 */
public class LoopingSolver implements SudokuSolver {

    private Validator validator;

    private StepSolvingStrategy[] strategies;

    public LoopingSolver(Validator validator, StepSolvingStrategy ... strategies) {
        this.validator = validator;
        this.strategies = strategies;
    }

    @Override
    public boolean solve(Sudoku sudoku) {
        attemptToSolve(sudoku);
        return validator.isSolved(sudoku);
    }

    private void attemptToSolve(Sudoku sudoku) {
        boolean hasChanged = true;
        while(hasChanged) {
            hasChanged = false;
            for (StepSolvingStrategy strategy : strategies) {
                hasChanged = strategy.solveStep(sudoku) || hasChanged;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoopingSolver that = (LoopingSolver) o;
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
