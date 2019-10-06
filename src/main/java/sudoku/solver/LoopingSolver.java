package sudoku.solver;

import sudoku.board.Sudoku;
import sudoku.solver.strategy.StepSolvingStrategy;
import sudoku.validator.Validator;
import sudoku.validator.ValidatorFactory;

/*
Performs each StepSolvingStrategy in a loop until either nothing has changed or the puzzle is solved.
 */
public class LoopingSolver implements SudokuSolver {

    private StepSolvingStrategy[] strategies;

    public LoopingSolver(StepSolvingStrategy ... strategies) {
        this.strategies = strategies;
    }

    @Override
    public boolean solve(Sudoku sudoku) {
        attemptToSolve(sudoku);
        Validator validator = new ValidatorFactory().getValidator();
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

}
