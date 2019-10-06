package sudoku.solver;

import sudoku.solver.strategy.ScanningStrategy;
import sudoku.solver.strategy.OnlyOptionStrategy;

public class SudokuSolverFactory {
    public SudokuSolver getSolver() {
        return new LoopingSolver(new OnlyOptionStrategy(), new ScanningStrategy());
    }
}
