package sudoku.validator;

import sudoku.board.Sudoku;

public interface Validator {
    boolean isValidBoard(Sudoku sudoku);
    boolean isSolved(Sudoku sudoku);
}
