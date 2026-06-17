package com.github.shubhamjaggi.earit.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntervalContainerTest {

    @Test
    void getIntervals_returnsSameListAsProvided() {
        List<String> intervals = List.of("Perfect 5th", "Diminished 6th");
        IntervalContainer container = new IntervalContainer(intervals);
        assertEquals(intervals, container.getIntervals());
    }

    @Test
    void getIntervals_singletonList_returnsSingleton() {
        List<String> intervals = List.of("Augmented 8th");
        IntervalContainer container = new IntervalContainer(intervals);
        assertEquals(1, container.getIntervals().size());
        assertEquals("Augmented 8th", container.getIntervals().get(0));
    }
}
