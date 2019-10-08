package sudoku.strategy;

import sudoku.board.Sudoku;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SubsetStrategy implements StepSolvingStrategy {

    private static final int MAX_SUBSET_SIZE = 5;

    @Override
    public boolean solveStep(Sudoku sudoku) {
        for (int subsetSize = 2; subsetSize <= MAX_SUBSET_SIZE; subsetSize++) {
            reduceSubsets(sudoku, subsetSize);
        }
        return false;
    }

    private boolean reduceSubsets(Sudoku sudoku, int subsetSize) {
        boolean hasChangedRow = reduceRowSubsets(sudoku, subsetSize);
        boolean hasChangedCol = reduceColSubsets(sudoku, subsetSize);
        boolean hasChangedBox = reduceBoxSubsets(sudoku, subsetSize);

        return hasChangedRow || hasChangedCol || hasChangedBox;
    }

    private boolean reduceRowSubsets(Sudoku sudoku, int subsetSize) {
        // for subsetSize N, find a group of N cells that share exactly N unique numbers


        return false;
    }

    Set<Integer> combinedSet(List<List<Integer>> listOfOptions) {
        return listOfOptions.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }



    private boolean reduceColSubsets(Sudoku sudoku, int subsetSize) {
        return false;
    }

    private boolean reduceBoxSubsets(Sudoku sudoku, int subsetSize) {
        return false;
    }

}
