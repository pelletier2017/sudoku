package sudoku.board;

import java.util.List;

public class CellSudoku extends Sudoku {

    private Cell[][] board;

    public CellSudoku(int[][] boardInput) {
        assertSquareBoard(boardInput);

        board = createEmptyBoard();
        copyValuesIntoBoard(boardInput);
    }

    private Cell[][] createEmptyBoard() {
        Cell[][] board = new Cell[DEFAULT_SIZE][DEFAULT_SIZE];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
            }
        }
        return board;
    }

    private void copyValuesIntoBoard(int[][] intBoard) {
        for (int row = 0; row < intBoard.length; row++) {
            for (int col = 0; col < intBoard.length; col++) {
                int val = intBoard[row][col];
                if (val != 0) {
                    set(row, col, val);
                }
            }
        }
    }

    private void assertSquareBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i].length != board.length) {
                throw new IllegalArgumentException("Found column with " + board[i].length + " cells but should match number of rows " + board.length);
            }
        }
    }

    @Override
    public int size() {
        return board.length;
    }

    @Override
    public int get(int row, int col) {
        return board[row][col].getValue();
    }

    @Override
    public void set(int row, int col, int value) {
        //System.out.printf("set (%d, %d) to %d\n", row, col, value);
        board[row][col].setValue(value);
    }

    @Override
    public List<Integer> getOptions(int row, int col) {
        return board[row][col].getOptions();
    }

    @Override
    public boolean removeOption(int row, int col, int val) {
        //System.out.printf("remove option (%d,%d) remove %d\n", row, col, val);
        return board[row][col].removeOption(val);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());

        String cellOptionsFormat = "(%d,%d)=%s\n";
        builder.append("Options:\n");
        for (int row = 0; row < size(); row++) {
            for (int col = 0; col < size(); col++) {
                List<Integer> options = getOptions(row, col);
                builder.append(String.format(cellOptionsFormat, row, col, options));
            }
        }
        return builder.toString();
    }
}
