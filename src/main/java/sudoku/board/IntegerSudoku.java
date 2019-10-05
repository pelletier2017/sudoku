package sudoku.board;

import java.util.List;

public class IntegerSudoku extends Sudoku {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public int get(int row, int col) {
        return 0;
    }

    @Override
    public void set(int row, int col, int val) {

    }

    @Override
    public List<Integer> getOptions(int row, int col) {
        return null;
    }

    @Override
    public boolean removeOption(int row, int col, int val) {
        return false;
    }
}
