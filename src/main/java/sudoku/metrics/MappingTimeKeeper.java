package sudoku.metrics;

import javafx.util.Pair;
import sudoku.board.Sudoku;
import sudoku.solver.SudokuSolver;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Warning do not use with very large numbers that could sum to a value greater than what can be stored in a Long or overflow will occur.
 */
public class MappingTimeKeeper<T> implements TimeKeeper<T> {

    private Map<T, List<Long>> recordings = new HashMap<>();

    @Override
    public void record(T key, long value) {
        if (key == null) {
            throw new IllegalArgumentException("Argument key cannot be null");
        }

        recordings.putIfAbsent(key, new ArrayList<>());
        recordings.get(key).add(value);
    }

    @Override
    public double mean(T key) {
        return mean(recordings.get(key));
    }

    private double mean(List<Long> nums) {
        double total = 0.0;
        for (long num : nums) {
            total += num;
        }
        return total / nums.size();
    }

    @Override
    public double median(T key) {
        List<Long> matchingRecordings = recordings.get(key);
        Collections.sort(matchingRecordings);

        int middleIndex = matchingRecordings.size() / 2;
        return matchingRecordings.get(middleIndex);
    }

    @Override
    public double max(T key) {
        List<Long> matchingRecordings = recordings.get(key);
        if (matchingRecordings.isEmpty()) {
            throw new IllegalArgumentException("No recordings for key " + key);
        }

        Collections.sort(matchingRecordings);
        return matchingRecordings.get(matchingRecordings.size() - 1);
    }

    @Override
    public double min(T key) {
        List<Long> matchingRecordings = recordings.get(key);
        if (matchingRecordings.isEmpty()) {
            throw new IllegalArgumentException("No recordings for key " + key);
        }

        Collections.sort(matchingRecordings);
        return matchingRecordings.get(0);
    }

    @Override
    public List<Pair<T, Double>> means() {
        return pairAndSort(this::mean);
    }

    @Override
    public List<Pair<T, Double>> medians() {
        return pairAndSort(this::median);
    }

    @Override
    public List<Pair<T, Double>> mins() {
        return pairAndSort(this::min);
    }

    @Override
    public List<Pair<T, Double>> maxes() {
        return pairAndSort(this::max);
    }

    private List<Pair<T, Double>> pairAndSort(Function<T, Double> function) {
        return recordings.keySet().stream()
                .map((key) -> new Pair<>(key, function.apply(key)))
                .sorted(Comparator.comparingDouble(Pair::getValue))
                .collect(Collectors.toList());
    }

}
