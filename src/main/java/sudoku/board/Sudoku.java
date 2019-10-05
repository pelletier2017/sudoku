package sudoku.board;

public abstract class Sudoku {

    protected static final int DEFAULT_SIZE = 9;

    public abstract int size();
    public abstract int get(int row, int col);
    public abstract void set(int row, int col, int val);

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
        String rowBorder = "+-------+-------+-------+\n";
        String rowFormat = "| %s %s %s | %s %s %s | %s %s %s |\n";

        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < size(); row++) {
            if (row % 3 == 0) {
                builder.append(rowBorder);
            }
            String rowStr = String.format(rowFormat,
                    getStr(row, 0),
                    getStr(row, 1),
                    getStr(row, 2),
                    getStr(row, 3),
                    getStr(row, 4),
                    getStr(row, 5),
                    getStr(row, 6),
                    getStr(row, 7),
                    getStr(row, 8)
            );
            builder.append(rowStr);
        }
        builder.append(rowBorder);
        return builder.toString();
    }

    private String getStr(int row, int col) {
        int val = get(row, col);
        return val != 0 ? Integer.toString(val) : " ";
    }
}
