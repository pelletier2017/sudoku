package sudoku.metrics;

import javafx.util.Pair;

import java.util.List;

public interface TimeKeeper<T> {
    void record(T key, long time);

    double mean(T key);
    double median(T key);
    double max(T key);
    double min(T key);

    List<Pair<T, Double>> means();
    List<Pair<T, Double>> medians();
    List<Pair<T, Double>> mins();
    List<Pair<T, Double>> maxes();
}
