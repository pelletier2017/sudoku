package sudoku.validator;

import sudoku.board.Sudoku;

import java.util.HashSet;

public class HashSetValidator implements Validator {

    private static final int NUM_BOX_IN_GROUP = 3;

    public boolean isValidBoard(Sudoku sudoku) {
        boolean rows = hasValidRows(sudoku);
        boolean cols = hasValidCols(sudoku);
        boolean boxes = hasValidBoxes(sudoku);
        return hasValidRows(sudoku) && hasValidCols(sudoku) && hasValidBoxes(sudoku);
    }

    // TODO rewrite this to not trigger intellij duplicate code warning
    private boolean hasValidRows(Sudoku sudoku) {
        for (int row = 0; row < sudoku.size(); row++) {
            HashSet<Integer> seenSet = new HashSet<>();
            for (int col = 0; col < sudoku.size(); col++) {
                int val = sudoku.get(row, col);
                if (seenSet.contains(val)) {
                    return false;
                }
                if (val != 0) {
                    seenSet.add(val);
                }
            }
        }
        return true;
    }

    private boolean hasValidCols(Sudoku sudoku) {
        for (int col = 0; col < sudoku.size(); col++) {
            HashSet<Integer> seenSet = new HashSet<>();
            for (int row = 0; row < sudoku.size(); row++) {
                int val = sudoku.get(row, col);
                if (seenSet.contains(val)) {
                    return false;
                }
                if (val != 0) {
                    seenSet.add(val);
                }
            }
        }
        return true;
    }

    private boolean hasValidBoxes(Sudoku sudoku) {
        for (int bigRow = 0; bigRow < NUM_BOX_IN_GROUP; bigRow++) {
            for (int bigCol = 0; bigCol < NUM_BOX_IN_GROUP; bigCol++) {
                if (!isValidBox(sudoku, bigRow, bigCol)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidBox(Sudoku sudoku, int bigRow, int bigCol) {
        int startRow = bigRow * NUM_BOX_IN_GROUP;
        int startCol = bigCol * NUM_BOX_IN_GROUP;

        HashSet<Integer> seenSet = new HashSet<>();
        for (int row = startRow; row < startRow + NUM_BOX_IN_GROUP; row++) {
            for (int col = startCol; col < startCol + NUM_BOX_IN_GROUP; col++) {
                // TODO rewrite this to not trigger intellij duplicate code warning
                int val = sudoku.get(row, col);
                if (seenSet.contains(val)) {
                    return false;
                }
                if (val != 0) {
                    seenSet.add(val);
                }
            }
        }
        return true;
    }

    public boolean isSolved(Sudoku sudoku) {
        return hasNoEmptyCells(sudoku) && isValidBoard(sudoku);
    }

    private boolean hasNoEmptyCells(Sudoku sudoku) {
        for (int row = 0; row < sudoku.size(); row++) {
            for (int col = 0; col < sudoku.size(); col++) {
                int val = sudoku.get(row, col);
                if (val == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
