package com.github.shubhamjaggi.earit.constants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IntervalTest {

    @ParameterizedTest(name = "{0} = {1} semitone(s)")
    @MethodSource("intervalSemitoneData")
    void getNumberOfSemitones_allIntervals_returnsCorrectCount(Interval interval, int expectedSemitones) {
        assertEquals(expectedSemitones, interval.getNumberOfSemitones());
    }

    static Stream<Arguments> intervalSemitoneData() {
        return Stream.of(
                Arguments.of(Interval.PERFECT_4TH,     5),
                Arguments.of(Interval.PERFECT_5TH,     7),
                Arguments.of(Interval.PERFECT_8TH,    12),
                Arguments.of(Interval.MAJOR_2ND,        2),
                Arguments.of(Interval.MAJOR_3RD,        4),
                Arguments.of(Interval.MAJOR_6TH,        9),
                Arguments.of(Interval.MAJOR_7TH,       11),
                Arguments.of(Interval.MINOR_2ND,        1),
                Arguments.of(Interval.MINOR_3RD,        3),
                Arguments.of(Interval.MINOR_6TH,        8),
                Arguments.of(Interval.MINOR_7TH,       10),
                Arguments.of(Interval.AUGMENTED_1ST,    1),
                Arguments.of(Interval.AUGMENTED_2ND,    3),
                Arguments.of(Interval.AUGMENTED_3RD,    5),
                Arguments.of(Interval.AUGMENTED_4TH,    6),
                Arguments.of(Interval.AUGMENTED_5TH,    8),
                Arguments.of(Interval.AUGMENTED_6TH,   10),
                Arguments.of(Interval.AUGMENTED_7TH,   12),
                Arguments.of(Interval.AUGMENTED_8TH,   13),
                Arguments.of(Interval.DIMINISHED_3RD,   2),
                Arguments.of(Interval.DIMINISHED_4TH,   4),
                Arguments.of(Interval.DIMINISHED_5TH,   6),
                Arguments.of(Interval.DIMINISHED_6TH,   7),
                Arguments.of(Interval.DIMINISHED_7TH,   9),
                Arguments.of(Interval.DIMINISHED_8TH,  11)
        );
    }

    @ParameterizedTest(name = "{0} → \"{1}\"")
    @MethodSource("intervalDisplayNameData")
    void getValue_allIntervals_returnsCorrectDisplayName(Interval interval, String expectedValue) {
        assertEquals(expectedValue, interval.getValue());
    }

    static Stream<Arguments> intervalDisplayNameData() {
        return Stream.of(
                Arguments.of(Interval.PERFECT_4TH,    "Perfect 4th"),
                Arguments.of(Interval.PERFECT_5TH,    "Perfect 5th"),
                Arguments.of(Interval.PERFECT_8TH,    "Perfect 8th"),
                Arguments.of(Interval.MAJOR_2ND,       "Major 2nd"),
                Arguments.of(Interval.MAJOR_3RD,       "Major 3rd"),
                Arguments.of(Interval.MAJOR_6TH,       "Major 6th"),
                Arguments.of(Interval.MAJOR_7TH,       "Major 7th"),
                Arguments.of(Interval.MINOR_2ND,       "Minor 2nd"),
                Arguments.of(Interval.MINOR_3RD,       "Minor 3rd"),
                Arguments.of(Interval.MINOR_6TH,       "Minor 6th"),
                Arguments.of(Interval.MINOR_7TH,       "Minor 7th"),
                Arguments.of(Interval.AUGMENTED_1ST,   "Augmented 1st"),
                Arguments.of(Interval.AUGMENTED_2ND,   "Augmented 2nd"),
                Arguments.of(Interval.AUGMENTED_3RD,   "Augmented 3rd"),
                Arguments.of(Interval.AUGMENTED_4TH,   "Augmented 4th"),
                Arguments.of(Interval.AUGMENTED_5TH,   "Augmented 5th"),
                Arguments.of(Interval.AUGMENTED_6TH,   "Augmented 6th"),
                Arguments.of(Interval.AUGMENTED_7TH,   "Augmented 7th"),
                Arguments.of(Interval.AUGMENTED_8TH,   "Augmented 8th"),
                Arguments.of(Interval.DIMINISHED_3RD,  "Diminished 3rd"),
                Arguments.of(Interval.DIMINISHED_4TH,  "Diminished 4th"),
                Arguments.of(Interval.DIMINISHED_5TH,  "Diminished 5th"),
                Arguments.of(Interval.DIMINISHED_6TH,  "Diminished 6th"),
                Arguments.of(Interval.DIMINISHED_7TH,  "Diminished 7th"),
                Arguments.of(Interval.DIMINISHED_8TH,  "Diminished 8th")
        );
    }

    @Test
    void values_contains25Intervals() {
        assertEquals(25, Interval.values().length);
    }

    @Test
    void augmented8th_isOnlyIntervalWith13Semitones() {
        long count = java.util.Arrays.stream(Interval.values())
                .filter(i -> i.getNumberOfSemitones() == 13)
                .count();
        assertEquals(1, count);
        assertEquals(Interval.AUGMENTED_8TH,
                java.util.Arrays.stream(Interval.values())
                        .filter(i -> i.getNumberOfSemitones() == 13)
                        .findFirst().get());
    }
}
