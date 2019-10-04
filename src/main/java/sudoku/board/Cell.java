package sudoku.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cell {

    private static final int OPTION_SIZE = 9;

    private int val;

    private List<Integer> options;

    public Cell() {
        this.val = 0;
        this.options = defaultOptions();
    }

    public Cell(int val) {
        this.val = val;
        this.options = new ArrayList<>();
    }

    private List<Integer> defaultOptions() {
        List<Integer> options = new ArrayList<>();
        for (int i = 1; i <= OPTION_SIZE; i++) {
            options.add(i);
        }
        return options;
    }

    public int getValue() {
        return this.val;
    }

    public List<Integer> getOptions() {
        return Collections.unmodifiableList(this.options);
    }

    public void setValue(int val) {
        if (this.val != 0) {
            throw new IllegalStateException("Cannot set value to " + val + " when value is already non-zero " + this.val);
        }

        this.val = val;
        this.options = Collections.emptyList();
    }

    public void removeOption(int valToRemove) {
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).equals(valToRemove)) {
                options.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Cannot remove " + valToRemove + " because it is not in the list of options" + options);
    }

}
