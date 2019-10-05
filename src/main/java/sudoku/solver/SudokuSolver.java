package sudoku.solver;

import sudoku.board.Sudoku;

/*
Mutates the Sudoku to attempt to solve it.
Returns true if it is successfully solved and false otherwise.
 */
public interface SudokuSolver {
    boolean solve(Sudoku sudoku);
}
