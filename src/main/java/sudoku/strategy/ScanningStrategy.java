package sudoku.strategy;

import sudoku.board.Sudoku;

public class ScanningStrategy implements StepSolvingStrategy {

    private static final int SIZE_OF_BOX = 3;

    @Override
    public boolean solveStep(Sudoku sudoku) {
        boolean updatedRow = crossOutRows(sudoku);
        boolean updatedCol = crossOutCols(sudoku);
        boolean updatedBox = crossOutBoxes(sudoku);
        return updatedRow || updatedCol || updatedBox;
    }

    private boolean crossOutRows(Sudoku sudoku) {
        boolean hasUpdated = false;
        for (int row = 0; row < sudoku.size(); row++) {
            for (int col = 0; col < sudoku.size(); col++) {
                int val = sudoku.get(row, col);
                if (val != 0) {
                    hasUpdated = removeOptionFromRow(sudoku, row, val) || hasUpdated;
                }
            }
        }
        return hasUpdated;
    }

    private boolean removeOptionFromRow(Sudoku sudoku, int row, int val) {
        boolean hasUpdated = false;
        for (int col = 0; col < sudoku.size(); col++) {
            hasUpdated = sudoku.removeOption(row, col, val) || hasUpdated;
        }
        return hasUpdated;
    }

    private boolean crossOutCols(Sudoku sudoku) {
        boolean hasUpdated = false;
        for (int row = 0; row < sudoku.size(); row++) {
            for (int col = 0; col < sudoku.size(); col++) {
                int val = sudoku.get(row, col);
                if (val != 0) {
                    hasUpdated = removeOptionFromCol(sudoku, col, val) || hasUpdated;
                }
            }
        }
        return hasUpdated;
    }

    private boolean removeOptionFromCol(Sudoku sudoku, int col, int val) {
        boolean hasUpdated = false;
        for (int row = 0; row < sudoku.size(); row++) {
            hasUpdated = sudoku.removeOption(row, col, val) || hasUpdated;
        }
        return hasUpdated;
    }

    private boolean crossOutBoxes(Sudoku sudoku) {
        boolean hasUpdated = false;
        for (int row = 0; row < sudoku.size(); row++) {
            for (int col = 0; col < sudoku.size(); col++) {
                int val = sudoku.get(row, col);
                if (val != 0) {
                    int bigRow = row / SIZE_OF_BOX;
                    int bigCol = col / SIZE_OF_BOX;
                    hasUpdated = removeOptionFromBox(sudoku, bigRow, bigCol, val) || hasUpdated;
                }
            }
        }
        return hasUpdated;
    }

    private boolean removeOptionFromBox(Sudoku sudoku, int bigRow, int bigCol, int val) {
        boolean hasUpdated = false;
        for (int row = bigRow * SIZE_OF_BOX; row < (bigRow * SIZE_OF_BOX) + SIZE_OF_BOX; row++) {
            for (int col = bigCol * SIZE_OF_BOX; col < (bigCol * SIZE_OF_BOX) + SIZE_OF_BOX; col++) {
                hasUpdated = sudoku.removeOption(row, col, val) || hasUpdated;
            }
        }
        return hasUpdated;
    }
}
