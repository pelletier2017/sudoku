package sudoku.board;

import java.util.List;

public abstract class Sudoku {

    protected static final int DEFAULT_SIZE = 9;

    public abstract int size();
    public abstract int get(int row, int col);
    public abstract void set(int row, int col, int val);
    public abstract List<Integer> getOptions(int row, int col);
    public abstract boolean removeOption(int row, int col, int val);

    public int numEmptyCells() {
        int count = 0;
        for (int row = 0; row < size(); row++) {
            for (int col = 0; col < size(); col++) {
                if (get(row, col) == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Sudoku)) {
            return false;
        }
        Sudoku other = (Sudoku) obj;
        return allCellValuesMatch(this, other);
    }

    private static boolean allCellValuesMatch(Sudoku a, Sudoku b) {
        for (int row = 0; row < DEFAULT_SIZE; row++) {
            for (int col = 0; col < DEFAULT_SIZE; col++) {
                if (a.get(row, col) != b.get(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String colMarker = "   0 1 2   3 4 5   6 7 8\n";
        String rowBorder = " +-------+-------+-------+\n";
        String rowFormat = "| %s %s %s | %s %s %s | %s %s %s |\n";

        StringBuilder builder = new StringBuilder();
        builder.append(colMarker);
        for (int row = 0; row < size(); row++) {
            if (row % 3 == 0) {
                builder.append(rowBorder);
            }
            builder.append(row);
            String rowStr = String.format(rowFormat,
                    getNumber(row, 0),
                    getNumber(row, 1),
                    getNumber(row, 2),
                    getNumber(row, 3),
                    getNumber(row, 4),
                    getNumber(row, 5),
                    getNumber(row, 6),
                    getNumber(row, 7),
                    getNumber(row, 8)
            );
            builder.append(rowStr);
        }
        builder.append(rowBorder);
        builder.append(colMarker);
        return builder.toString();
    }

    private String getNumberOrBlank(int row, int col) {
        int val = get(row, col);
        return val != 0 ? Integer.toString(val) : " ";
    }

    private String getNumber(int row, int col) {
        return String.valueOf(get(row, col));
    }
}
