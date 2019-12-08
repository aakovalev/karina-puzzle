package org.home;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PuzzleSolverTest {
    @Test (expected = RuntimeException.class)
    public void emptyList() {
        PuzzleSolver ps = new PuzzleSolver(2, Arrays.asList());
    }

    @Test (expected = RuntimeException.class)
    public void oneElementList() {
        PuzzleSolver ps = new PuzzleSolver(3, Arrays.asList(14));
    }

    @Test
    public void noNumbersSumOfWhichCanBeDividedByK() {
        PuzzleSolver ps = new PuzzleSolver(2, Arrays.asList(1, 2));
        assertThat(ps.maxLength(), is(2));
    }

    @Test
    public void withNumbersSumOfWhichCanBeDividedByK() {
        PuzzleSolver ps = new PuzzleSolver(7, Arrays.asList(1, 2, 5, 6));
        assertThat(ps.maxLength(), is(2));
    }

    @Test
    public void withNumbersSumOfWhichWithItselfCanBeDividedByK() {
        PuzzleSolver ps = new PuzzleSolver(2, Arrays.asList(1, 1, 2, 2));
        assertThat(ps.maxLength(), is(2));
    }

    @Test
    public void arbitraryCase() {
        PuzzleSolver ps = new PuzzleSolver(7, Arrays.asList(1, 5, 6, 3, 3, 4, 2));
        assertThat(ps.maxLength(), is(4));
    }
}
