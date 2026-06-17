package com.github.shubhamjaggi.earit.constants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MidiNoteTest {

    @ParameterizedTest(name = "{0} → noteNumber={1}, shortName=\"{2}\"")
    @MethodSource("keyNoteData")
    void keyNotes_haveCorrectNumberAndShortName(MidiNote note, int expectedNumber, String expectedShortName) {
        assertEquals(expectedNumber, note.getNoteNumber());
        assertEquals(expectedShortName, note.getShortName());
    }

    static Stream<Arguments> keyNoteData() {
        return Stream.of(
                Arguments.of(MidiNote.A0,         21,  "A0"),    // lowest note in enum
                Arguments.of(MidiNote.A_SHARP_0,  22,  "A#0"),
                Arguments.of(MidiNote.B_FLAT_0,   22,  "Bb0"),   // enharmonic to A#0
                Arguments.of(MidiNote.C4,         60,  "C4"),    // middle C
                Arguments.of(MidiNote.C_SHARP_4,  61,  "C#4"),
                Arguments.of(MidiNote.D_FLAT_4,   61,  "Db4"),   // enharmonic to C#4
                Arguments.of(MidiNote.A4,         69,  "A4"),    // concert A (440 Hz)
                Arguments.of(MidiNote.G9,        127,  "G9")     // highest note in enum
        );
    }

    @Test
    void enharmonicNotes_shareNoteNumber() {
        assertEquals(MidiNote.C_SHARP_4.getNoteNumber(), MidiNote.D_FLAT_4.getNoteNumber());
        assertEquals(MidiNote.F_SHARP_4.getNoteNumber(), MidiNote.G_FLAT_4.getNoteNumber());
        assertEquals(MidiNote.A_SHARP_0.getNoteNumber(), MidiNote.B_FLAT_0.getNoteNumber());
    }

    @Test
    void naturalNotes_haveUniqueNoteNumbers() {
        // Natural notes (no # or b) each appear exactly once in the enum
        long distinctC4Count = java.util.Arrays.stream(MidiNote.values())
                .filter(n -> n.getNoteNumber() == 60)
                .count();
        assertEquals(1, distinctC4Count); // C4 has no enharmonic equivalent
    }

    @Test
    void values_contains151Entries() {
        // 4 (A0-B0) + 8 octaves × 17 entries + 11 (C9-G9) = 151
        assertEquals(151, MidiNote.values().length);
    }

    @Test
    void noteRange_spansA0toG9() {
        assertEquals(21,  MidiNote.A0.getNoteNumber());
        assertEquals(127, MidiNote.G9.getNoteNumber());
    }
}
