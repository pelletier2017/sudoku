package sudoku.strategy;

import sudoku.board.Sudoku;

import java.util.List;

public class OnlyOptionStrategy implements StepSolvingStrategy {

    public boolean solveStep(Sudoku sudoku) {
        boolean wasUpdated = false;
        for (int row = 0; row < sudoku.size(); row++) {
            for (int col = 0; col < sudoku.size(); col++) {
                List<Integer> options = sudoku.getOptions(row, col);
                if (options.size() == 1) {
                    wasUpdated = true;
                    int val = options.get(0);
                    sudoku.set(row, col, val);
                }
            }
        }
        return wasUpdated;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
