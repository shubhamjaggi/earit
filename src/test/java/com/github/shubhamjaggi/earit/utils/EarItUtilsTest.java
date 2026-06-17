package com.github.shubhamjaggi.earit.utils;

import com.github.shubhamjaggi.earit.constants.ChordType;
import com.github.shubhamjaggi.earit.constants.Interval;
import com.github.shubhamjaggi.earit.constants.MidiNote;
import com.github.shubhamjaggi.earit.constants.ScaleType;
import com.github.shubhamjaggi.earit.model.IntervalContainer;
import com.github.shubhamjaggi.earit.model.MidiNoteShortNameContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EarItUtilsTest {

    // ── getMidiNotesForChord — C4 root ────────────────────────────────────────

    @Test
    void chord_major_hasCorrectIntervals() {
        assertNoteNumbers(EarItUtils.getMidiNotesForChord(MidiNote.C4, ChordType.MAJOR), 60, 64, 67);
    }

    @Test
    void chord_minor_hasCorrectIntervals() {
        assertNoteNumbers(EarItUtils.getMidiNotesForChord(MidiNote.C4, ChordType.MINOR), 60, 63, 67);
    }

    @Test
    void chord_diminished_hasCorrectIntervals() {
        assertNoteNumbers(EarItUtils.getMidiNotesForChord(MidiNote.C4, ChordType.DIMINISHED), 60, 63, 66);
    }

    @Test
    void chord_major7th_hasCorrectIntervals() {
        assertNoteNumbers(EarItUtils.getMidiNotesForChord(MidiNote.C4, ChordType.MAJOR_7TH), 60, 64, 67, 71);
    }

    @Test
    void chord_dominant7th_hasCorrectIntervals() {
        assertNoteNumbers(EarItUtils.getMidiNotesForChord(MidiNote.C4, ChordType.DOMINANT_7TH), 60, 64, 67, 70);
    }

    @Test
    void chord_minor7th_hasCorrectIntervals() {
        assertNoteNumbers(EarItUtils.getMidiNotesForChord(MidiNote.C4, ChordType.MINOR_7TH), 60, 63, 67, 70);
    }

    @Test
    void chord_minor7thFlat5_hasCorrectIntervals() {
        assertNoteNumbers(EarItUtils.getMidiNotesForChord(MidiNote.C4, ChordType.MINOR_7TH_FLAT_5), 60, 63, 66, 70);
    }

    // ── getMidiNotesForChord — non-C root (verifies offset arithmetic) ─────────

    @Test
    void chord_major_nonCRoot_offsetAppliedCorrectly() {
        // G4 = 67; Major: root +4 +7 → 67, 71, 74
        assertNoteNumbers(EarItUtils.getMidiNotesForChord(MidiNote.G4, ChordType.MAJOR), 67, 71, 74);
    }

    @Test
    void chord_dominant7th_nonCRoot_offsetAppliedCorrectly() {
        // G4 = 67; Dominant 7th: root +4 +7 +10 → 67, 71, 74, 77
        assertNoteNumbers(EarItUtils.getMidiNotesForChord(MidiNote.G4, ChordType.DOMINANT_7TH), 67, 71, 74, 77);
    }

    @Test
    void chord_minor7thFlat5_nonCRoot_offsetAppliedCorrectly() {
        // G4 = 67; Minor 7th Flat 5: root +3 +6 +10 → 67, 70, 73, 77
        assertNoteNumbers(EarItUtils.getMidiNotesForChord(MidiNote.G4, ChordType.MINOR_7TH_FLAT_5), 67, 70, 73, 77);
    }

    // ── getMidiNotesForScale — C4 root ────────────────────────────────────────

    @Test
    void scale_major_hasCorrectNotes() {
        // root +2 +4 +5 +7 +9 +11 +12
        assertNoteNumbers(EarItUtils.getMidiNotesForScale(MidiNote.C4, ScaleType.MAJOR), 60, 62, 64, 65, 67, 69, 71, 72);
    }

    @Test
    void scale_naturalMinor_hasCorrectNotes() {
        // root +2 +3 +5 +7 +8 +10 +12
        assertNoteNumbers(EarItUtils.getMidiNotesForScale(MidiNote.C4, ScaleType.NATURAL_MINOR), 60, 62, 63, 65, 67, 68, 70, 72);
    }

    @Test
    void scale_harmonicMinor_hasCorrectNotes() {
        // root +2 +3 +5 +7 +8 +11 +12
        assertNoteNumbers(EarItUtils.getMidiNotesForScale(MidiNote.C4, ScaleType.HARMONIC_MINOR), 60, 62, 63, 65, 67, 68, 71, 72);
    }

    @Test
    void scale_majorPentatonic_hasCorrectNotes() {
        // root +2 +4 +7 +9 +12
        assertNoteNumbers(EarItUtils.getMidiNotesForScale(MidiNote.C4, ScaleType.MAJOR_PENTATONIC), 60, 62, 64, 67, 69, 72);
    }

    @Test
    void scale_minorPentatonic_hasCorrectNotes() {
        // root +3 +5 +7 +10 +12
        assertNoteNumbers(EarItUtils.getMidiNotesForScale(MidiNote.C4, ScaleType.MINOR_PENTATONIC), 60, 63, 65, 67, 70, 72);
    }

    @Test
    void scale_majorBlues_hasCorrectNotes() {
        // root +2 +3 +4 +7 +9 +12
        assertNoteNumbers(EarItUtils.getMidiNotesForScale(MidiNote.C4, ScaleType.MAJOR_BLUES), 60, 62, 63, 64, 67, 69, 72);
    }

    @Test
    void scale_minorBlues_hasCorrectNotes() {
        // root +3 +5 +6 +7 +10 +12
        assertNoteNumbers(EarItUtils.getMidiNotesForScale(MidiNote.C4, ScaleType.MINOR_BLUES), 60, 63, 65, 66, 67, 70, 72);
    }

    // ── getMidiNotesForScale — non-C root (verifies offset arithmetic) ─────────

    @Test
    void scale_naturalMinor_nonCRoot_offsetAppliedCorrectly() {
        // A4 = 69; Natural Minor: root +2 +3 +5 +7 +8 +10 +12 → 69,71,72,74,76,77,79,81
        assertNoteNumbers(EarItUtils.getMidiNotesForScale(MidiNote.A4, ScaleType.NATURAL_MINOR), 69, 71, 72, 74, 76, 77, 79, 81);
    }

    @Test
    void scale_minorPentatonic_nonCRoot_offsetAppliedCorrectly() {
        // A4 = 69; Minor Pentatonic: root +3 +5 +7 +10 +12 → 69,72,74,76,79,81
        assertNoteNumbers(EarItUtils.getMidiNotesForScale(MidiNote.A4, ScaleType.MINOR_PENTATONIC), 69, 72, 74, 76, 79, 81);
    }

    // ── getOtherNoteOfInterval — all 24 intervals from C4 (60) ───────────────

    @ParameterizedTest(name = "{0} ({1} semitones) from C4 → note {2}")
    @MethodSource("intervalComputationData")
    void getOtherNoteOfInterval_allIntervals_returnCorrectNoteNumber(
            Interval interval, int semitones, int expectedNoteNumber) {
        assertEquals(expectedNoteNumber,
                EarItUtils.getOtherNoteOfInterval(MidiNote.C4, interval).getNoteNumber());
    }

    static Stream<Arguments> intervalComputationData() {
        // C4 = MIDI 60; expected = 60 + semitones
        return Stream.of(
                Arguments.of(Interval.PERFECT_4TH,    5,  65),
                Arguments.of(Interval.PERFECT_5TH,    7,  67),
                Arguments.of(Interval.PERFECT_8TH,   12,  72),
                Arguments.of(Interval.MAJOR_2ND,       2,  62),
                Arguments.of(Interval.MAJOR_3RD,       4,  64),
                Arguments.of(Interval.MAJOR_6TH,       9,  69),
                Arguments.of(Interval.MAJOR_7TH,      11,  71),
                Arguments.of(Interval.MINOR_2ND,       1,  61),
                Arguments.of(Interval.MINOR_3RD,       3,  63),
                Arguments.of(Interval.MINOR_6TH,       8,  68),
                Arguments.of(Interval.MINOR_7TH,      10,  70),
                Arguments.of(Interval.AUGMENTED_1ST,   1,  61),
                Arguments.of(Interval.AUGMENTED_2ND,   3,  63),
                Arguments.of(Interval.AUGMENTED_3RD,   5,  65),
                Arguments.of(Interval.AUGMENTED_4TH,   6,  66),
                Arguments.of(Interval.AUGMENTED_5TH,   8,  68),
                Arguments.of(Interval.AUGMENTED_6TH,  10,  70),
                Arguments.of(Interval.AUGMENTED_7TH,  12,  72),
                Arguments.of(Interval.AUGMENTED_8TH,  13,  73),
                Arguments.of(Interval.DIMINISHED_3RD,  2,  62),
                Arguments.of(Interval.DIMINISHED_4TH,  4,  64),
                Arguments.of(Interval.DIMINISHED_5TH,  6,  66),
                Arguments.of(Interval.DIMINISHED_6TH,  7,  67),
                Arguments.of(Interval.DIMINISHED_7TH,  9,  69),
                Arguments.of(Interval.DIMINISHED_8TH, 11,  71)
        );
    }

    // ── getIntervalContainer — all 13 enharmonic groups ───────────────────────

    @ParameterizedTest(name = "{0} → {2}")
    @MethodSource("intervalContainerData")
    void getIntervalContainer_allGroups_returnCorrectEnharmonicList(
            Interval interval, int semitones, List<String> expectedIntervals) {
        assertEquals(expectedIntervals, EarItUtils.getIntervalContainer(interval).getIntervals());
    }

    static Stream<Arguments> intervalContainerData() {
        // One representative interval per semitone group; list order matches enum declaration order
        return Stream.of(
                Arguments.of(Interval.MINOR_2ND,      1,  List.of("Minor 2nd",    "Augmented 1st")),
                Arguments.of(Interval.MAJOR_2ND,      2,  List.of("Major 2nd",    "Diminished 3rd")),
                Arguments.of(Interval.MINOR_3RD,      3,  List.of("Minor 3rd",    "Augmented 2nd")),
                Arguments.of(Interval.MAJOR_3RD,      4,  List.of("Major 3rd",    "Diminished 4th")),
                Arguments.of(Interval.PERFECT_4TH,    5,  List.of("Perfect 4th",  "Augmented 3rd")),
                Arguments.of(Interval.AUGMENTED_4TH,  6,  List.of("Augmented 4th","Diminished 5th")),
                Arguments.of(Interval.PERFECT_5TH,    7,  List.of("Perfect 5th",  "Diminished 6th")),
                Arguments.of(Interval.MINOR_6TH,      8,  List.of("Minor 6th",    "Augmented 5th")),
                Arguments.of(Interval.MAJOR_6TH,      9,  List.of("Major 6th",    "Diminished 7th")),
                Arguments.of(Interval.MINOR_7TH,     10,  List.of("Minor 7th",    "Augmented 6th")),
                Arguments.of(Interval.MAJOR_7TH,     11,  List.of("Major 7th",    "Diminished 8th")),
                Arguments.of(Interval.PERFECT_8TH,   12,  List.of("Perfect 8th",  "Augmented 7th")),
                Arguments.of(Interval.AUGMENTED_8TH, 13,  List.of("Augmented 8th"))
        );
    }

    // ── getMidiNoteShortNameContainer ─────────────────────────────────────────

    @Test
    void shortNameContainer_naturalNote_hasEmptyShortName2() {
        MidiNoteShortNameContainer c = EarItUtils.getMidiNoteShortNameContainer(MidiNote.C4);
        assertEquals("C4", c.getShortName1());
        assertEquals("", c.getShortName2());
    }

    @Test
    void shortNameContainer_sharpNote_hasEnharmonicFlatAsShortName2() {
        MidiNoteShortNameContainer c = EarItUtils.getMidiNoteShortNameContainer(MidiNote.C_SHARP_4);
        assertEquals("C#4", c.getShortName1());
        assertEquals("Db4", c.getShortName2());
    }

    @Test
    void shortNameContainer_flatNote_returnsSameSharpFlatPairAsSharpNote() {
        // Db4 and C#4 share note number 61; container always returns sharp first (enum declaration order)
        MidiNoteShortNameContainer c = EarItUtils.getMidiNoteShortNameContainer(MidiNote.D_FLAT_4);
        assertEquals("C#4", c.getShortName1());
        assertEquals("Db4", c.getShortName2());
    }

    @Test
    void shortNameContainer_naturalNoteInHighOctave_hasEmptyShortName2() {
        MidiNoteShortNameContainer c = EarItUtils.getMidiNoteShortNameContainer(MidiNote.A4);
        assertEquals("A4", c.getShortName1());
        assertEquals("", c.getShortName2());
    }

    // ── isBlank ───────────────────────────────────────────────────────────────

    @Test
    void isBlank_null_returnsTrue() {
        assertTrue(EarItUtils.isBlank(null));
    }

    @Test
    void isBlank_emptyString_returnsTrue() {
        assertTrue(EarItUtils.isBlank(""));
    }

    @Test
    void isBlank_whitespaceOnly_returnsTrue() {
        assertTrue(EarItUtils.isBlank("   "));
    }

    @Test
    void isBlank_nonEmptyString_returnsFalse() {
        assertFalse(EarItUtils.isBlank("C4"));
    }

    // ── getMidiNoteFromShortName ──────────────────────────────────────────────

    @Test
    void getMidiNoteFromShortName_naturalNote_returnsCorrectEnum() {
        assertEquals(MidiNote.C4, EarItUtils.getMidiNoteFromShortName("C4"));
    }

    @Test
    void getMidiNoteFromShortName_sharpNote_returnsCorrectEnum() {
        assertEquals(MidiNote.F_SHARP_4, EarItUtils.getMidiNoteFromShortName("F#4"));
    }

    @Test
    void getMidiNoteFromShortName_flatNote_returnsCorrectEnum() {
        assertEquals(MidiNote.G_FLAT_4, EarItUtils.getMidiNoteFromShortName("Gb4"));
    }

    // ── helper ────────────────────────────────────────────────────────────────

    private void assertNoteNumbers(List<MidiNote> notes, int... expected) {
        assertEquals(expected.length, notes.size(), "Wrong number of notes in result");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], notes.get(i).getNoteNumber(),
                    "Note at index " + i + " should have number " + expected[i]);
        }
    }
}
