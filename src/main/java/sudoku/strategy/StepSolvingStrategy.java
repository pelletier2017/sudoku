package sudoku.strategy;

import sudoku.board.Sudoku;

/*
Performs a step or series of steps to progress solving a sudoku puzzle.
Returns true if either the value or options were changed, and false otherwise.
 */
public interface StepSolvingStrategy {
    boolean solveStep(Sudoku sudoku);
}
