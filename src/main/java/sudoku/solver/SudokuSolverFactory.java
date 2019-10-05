package sudoku.solver;

import sudoku.solver.strategy.NarrowDownRowsColsBoxes;
import sudoku.solver.strategy.OnlyOptionStrategy;

public class SudokuSolverFactory {
    public SudokuSolver getSolver() {
        return new SimpleSolver(new OnlyOptionStrategy(), new NarrowDownRowsColsBoxes());
    }
}
