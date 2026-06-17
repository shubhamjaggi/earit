package com.github.shubhamjaggi.earit.constants;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ChordTypeTest {

    @ParameterizedTest(name = "{0} → \"{1}\"")
    @MethodSource("chordTypeValues")
    void getValue_returnsCorrectDisplayString(ChordType chordType, String expectedValue) {
        assertEquals(expectedValue, chordType.getValue());
    }

    static Stream<Arguments> chordTypeValues() {
        return Stream.of(
                Arguments.of(ChordType.MAJOR,           "Major"),
                Arguments.of(ChordType.MINOR,           "Minor"),
                Arguments.of(ChordType.DIMINISHED,      "Diminished"),
                Arguments.of(ChordType.MAJOR_7TH,       "Major 7th"),
                Arguments.of(ChordType.DOMINANT_7TH,    "Dominant 7th"),
                Arguments.of(ChordType.MINOR_7TH,       "Minor 7th"),
                Arguments.of(ChordType.MINOR_7TH_FLAT_5,"Minor 7th Flat 5")
        );
    }

    @Test
    void values_containsSevenTypes() {
        assertEquals(7, ChordType.values().length);
    }
}
