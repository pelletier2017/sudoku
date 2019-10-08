package sudoku.solver;

import sudoku.board.Sudoku;
import sudoku.metrics.TimeKeeper;

public class TimedWrapper implements SudokuSolver {

    private SudokuSolver sudokuSolver;

    private TimeKeeper<SudokuSolver> timeKeeper;

    public TimedWrapper(SudokuSolver sudokuSolver, TimeKeeper<SudokuSolver> timeKeeper) {
        this.sudokuSolver = sudokuSolver;
        this.timeKeeper = timeKeeper;
    }

    @Override
    public boolean solve(Sudoku sudoku) {
        long timeStart = System.currentTimeMillis();
        boolean hasSolved = sudokuSolver.solve(sudoku);
        long timeEnd = System.currentTimeMillis();

        long timeTaken = timeEnd - timeStart;
        timeKeeper.record(sudokuSolver, timeTaken);

        return hasSolved;
    }
}
