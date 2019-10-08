package sudoku.metrics;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MappingTimeKeeperTest {

    private MappingTimeKeeper<String> timeKeeper;

    @Before
    public void setUp() throws Exception {
        timeKeeper = new MappingTimeKeeper<>();
    }

    @Test
    public void meanOneElement() {
        timeKeeper.record("abc", 3);

        assertEquals(3.0, timeKeeper.mean("abc"), 0.001);
    }

    @Test
    public void meanMultiple() {
        timeKeeper.record("abc", 3);
        timeKeeper.record("abc", 6);
        timeKeeper.record("abc", 7);

        assertEquals(5.333, timeKeeper.mean("abc"), 0.001);
    }

    @Test
    public void median() {
        timeKeeper.record("abc", 3);
        timeKeeper.record("abc", 6);
        timeKeeper.record("abc", 7);

        assertEquals(6, timeKeeper.median("abc"), 0.001);
    }

    @Test
    public void max() {
        timeKeeper.record("abc", 3);
        timeKeeper.record("abc", 6);
        timeKeeper.record("abc", 7);

        assertEquals(7, timeKeeper.max("abc"), 0.001);
    }

    @Test
    public void min() {
        timeKeeper.record("abc", 3);
        timeKeeper.record("abc", 6);
        timeKeeper.record("abc", 7);


        assertEquals(timeKeeper.min("abc"), 3, 0.001);
    }

    @Test
    public void meansSingleElement() {
        timeKeeper.record("a", 3);
        timeKeeper.record("b", 4);
        timeKeeper.record("c", 5);

        List<Pair<String, Double>> expected = Arrays.asList(new Pair<>("a", 3.0), new Pair<>("b", 4.0), new Pair<>("c", 5.0));
        assertEquals(expected, timeKeeper.means());
    }

    @Test
    public void meansMultipleElements() {
        timeKeeper.record("a", 3);
        timeKeeper.record("a", 4);
        timeKeeper.record("b", 4);
        timeKeeper.record("b", 6);
        timeKeeper.record("c", 5);

        List<Pair<String, Double>> expected = Arrays.asList(new Pair<>("a", 3.5), new Pair<>("b", 5.0), new Pair<>("c", 5.0));
        assertEquals(expected, timeKeeper.means());
    }

    @Test
    public void medians() {
        timeKeeper.record("a", 3);
        timeKeeper.record("a", 4);
        timeKeeper.record("a", 8);

        timeKeeper.record("b", 4);
        timeKeeper.record("b", 2);
        timeKeeper.record("b", 6);

        List<Pair<String, Double>> expected = Arrays.asList(new Pair<>("a", 4.0), new Pair<>("b", 4.0));
        assertEquals(expected, timeKeeper.medians());
    }

    @Test
    public void minsAndSortedOrder() {
        timeKeeper.record("a", 3);
        timeKeeper.record("a", 4);
        timeKeeper.record("a", 8);

        timeKeeper.record("b", 4);
        timeKeeper.record("b", 2);
        timeKeeper.record("b", 6);

        List<Pair<String, Double>> expected = Arrays.asList(new Pair<>("b", 2.0), new Pair<>("a", 3.0));
        assertEquals(expected, timeKeeper.mins());
    }

    @Test
    public void maxes() {
        timeKeeper.record("a", 3);
        timeKeeper.record("a", 4);
        timeKeeper.record("a", 8);

        timeKeeper.record("b", 4);
        timeKeeper.record("b", 2);
        timeKeeper.record("b", 6);

        List<Pair<String, Double>> expected = Arrays.asList(new Pair<>("b", 6.0), new Pair<>("a", 8.0));
        assertEquals(expected, timeKeeper.maxes());
    }
}