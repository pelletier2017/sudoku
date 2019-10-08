package sudoku.strategy;

import sudoku.board.Sudoku;

import java.util.List;

public class EliminationStrategy implements StepSolvingStrategy {

    @Override
    public boolean solveStep(Sudoku sudoku) {
        boolean updatedInRow = eliminateInRow(sudoku);
        boolean updatedInCol = eliminateInCol(sudoku);
        return updatedInRow || updatedInCol;
    }

    private boolean eliminateInRow(Sudoku sudoku) {
        boolean hasUpdated = false;
        for (int row = 0; row < sudoku.size(); row++) {
            for (int optionVal = 1; optionVal < 10; optionVal++) {
                if (rowContainsOptionVal(sudoku, row, optionVal)) {
                    continue;
                }
                if (countRowOptionsForVal(sudoku, row, optionVal) == 1) {
                    int colSeen = colWhereOptionSeen(sudoku, row, optionVal);
                    sudoku.set(row, colSeen, optionVal);
                    hasUpdated = true;
                }
            }
        }
        return hasUpdated;
    }

    private boolean rowContainsOptionVal(Sudoku sudoku, int row, int optionVal) {
        for (int col = 0; col < sudoku.size(); col++) {
            if (sudoku.get(row, col) == optionVal) {
                return true;
            }
        }
        return false;
    }

    // TODO there is some duplication here marked by intellij that could be cleaned up
    private int countRowOptionsForVal(Sudoku sudoku, int row, int optionVal) {
        int countOptionsInRow = 0;
        for (int col = 0; col < sudoku.size(); col++) {
            List<Integer> options = sudoku.getOptions(row, col);
            if (options.contains(optionVal)) {
                countOptionsInRow++;
            }
        }
        return countOptionsInRow;
    }

    private int colWhereOptionSeen(Sudoku sudoku, int row, int val) {
        for (int col = 0; col < sudoku.size(); col++) {
            if (sudoku.getOptions(row, col).contains(val)) {
                return col;
            }
        }
        throw new IllegalStateException("This method not be called if val does not appear in the row");
    }

    private boolean eliminateInCol(Sudoku sudoku) {
        boolean hasUpdated = false;
        for (int col = 0; col < sudoku.size(); col++) {
            for (int optionVal = 1; optionVal < 10; optionVal++) {
                if (colContainsOptionVal(sudoku, col, optionVal)) {
                    continue;
                }
                if (countColOptionsForVal(sudoku, col, optionVal) == 1) {
                    int rowSeen = rowWhereOptionSeen(sudoku, col, optionVal);
                    sudoku.set(rowSeen, col, optionVal);
                    hasUpdated = true;
                }
            }
        }
        return hasUpdated;
    }

    private boolean colContainsOptionVal(Sudoku sudoku, int col, int optionVal) {
        for (int row = 0; row < sudoku.size(); row++) {
            if (sudoku.get(row, col) == optionVal) {
                return true;
            }
        }
        return false;
    }

    private int countColOptionsForVal(Sudoku sudoku, int col, int optionVal) {
        int countOptionsInCol = 0;
        for (int row = 0; row < sudoku.size(); row++) {
            List<Integer> options = sudoku.getOptions(row, col);
            if (options.contains(optionVal)) {
                countOptionsInCol++;
            }
        }
        return countOptionsInCol;
    }

    private int rowWhereOptionSeen(Sudoku sudoku, int col, int val) {
        for (int row = 0; row < sudoku.size(); row++) {
            if (sudoku.getOptions(row, col).contains(val)) {
                return row;
            }
        }
        throw new IllegalStateException("This method not be called if val does not appear in the row");
    }

    // TODO add the same elimination for elements in each box,
    // is this needed or already coverd by only option?

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
