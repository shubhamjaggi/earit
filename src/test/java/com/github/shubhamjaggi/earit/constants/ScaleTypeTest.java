package com.github.shubhamjaggi.earit.constants;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ScaleTypeTest {

    @ParameterizedTest(name = "{0} → \"{1}\"")
    @MethodSource("scaleTypeValues")
    void getValue_returnsCorrectDisplayString(ScaleType scaleType, String expectedValue) {
        assertEquals(expectedValue, scaleType.getValue());
    }

    static Stream<Arguments> scaleTypeValues() {
        return Stream.of(
                Arguments.of(ScaleType.MAJOR,            "Major"),
                Arguments.of(ScaleType.NATURAL_MINOR,    "Natural Minor"),
                Arguments.of(ScaleType.HARMONIC_MINOR,   "Harmonic Minor"),
                Arguments.of(ScaleType.MAJOR_PENTATONIC, "Major Pentatonic"),
                Arguments.of(ScaleType.MINOR_PENTATONIC, "Minor Pentatonic"),
                Arguments.of(ScaleType.MAJOR_BLUES,      "Major Blues"),
                Arguments.of(ScaleType.MINOR_BLUES,      "Minor Blues")
        );
    }

    @Test
    void values_containsSevenTypes() {
        assertEquals(7, ScaleType.values().length);
    }
}
