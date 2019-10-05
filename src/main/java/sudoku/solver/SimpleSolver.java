package sudoku.solver;

import sudoku.board.Sudoku;
import sudoku.solver.strategy.NarrowDownRowsColsBoxes;
import sudoku.solver.strategy.OnlyOptionStrategy;
import sudoku.validator.Validator;
import sudoku.validator.ValidatorFactory;

public class SimpleSolver implements SudokuSolver {

    OnlyOptionStrategy onlyOptionStrategy;

    NarrowDownRowsColsBoxes narrowDownRowsColsBoxes;

    public SimpleSolver(OnlyOptionStrategy onlyOptionStrategy, NarrowDownRowsColsBoxes narrowDownRowsColsBoxes) {
        this.onlyOptionStrategy = onlyOptionStrategy;
        this.narrowDownRowsColsBoxes = narrowDownRowsColsBoxes;
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
            hasChanged = narrowDownRowsColsBoxes.solveStep(sudoku);
            hasChanged = onlyOptionStrategy.solveStep(sudoku) || hasChanged;
        }
    }

}
