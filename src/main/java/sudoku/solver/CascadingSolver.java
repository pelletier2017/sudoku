package sudoku.solver;

import sudoku.board.Sudoku;
import sudoku.strategy.StepSolvingStrategy;
import sudoku.validator.Validator;

public class CascadingSolver implements SudokuSolver {

    private StepSolvingStrategy[] stepSolvingStrategies;

    private Validator validator;

    public CascadingSolver(Validator validator, StepSolvingStrategy ... stepSolvingStrategies) {
        this.validator = validator;
        this.stepSolvingStrategies = stepSolvingStrategies;
    }

    @Override
    public boolean solve(Sudoku sudoku) {
        attemptToSolve(sudoku);
        return validator.isSolved(sudoku);
    }

    private void attemptToSolve(Sudoku sudoku) {
        int step = 0;
        while (step < stepSolvingStrategies.length) {
            boolean hasChanged = stepSolvingStrategies[step].solveStep(sudoku);
            step++;

            if (hasChanged) {
                step = 0;
            }
        }
    }
}
