package com.github.shubhamjaggi.earit.utils;

import com.github.shubhamjaggi.earit.constants.ChordType;
import com.github.shubhamjaggi.earit.constants.Interval;
import com.github.shubhamjaggi.earit.constants.MidiNote;
import com.github.shubhamjaggi.earit.constants.ScaleType;
import com.github.shubhamjaggi.earit.model.IntervalContainer;
import com.github.shubhamjaggi.earit.model.MidiNoteShortNameContainer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EarItUtilsTest {

    // ── getMidiNotesForChord ──────────────────────────────────────────────────

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

    // ── getMidiNotesForScale ──────────────────────────────────────────────────

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

    // ── getOtherNoteOfInterval ────────────────────────────────────────────────

    @Test
    void interval_perfectFifth_returnsCorrectNote() {
        // C4 (60) + 7 semitones = G4 (67)
        assertEquals(67, EarItUtils.getOtherNoteOfInterval(MidiNote.C4, Interval.PERFECT_5TH).getNoteNumber());
    }

    @Test
    void interval_majorThird_returnsCorrectNote() {
        // C4 (60) + 4 semitones = E4 (64)
        assertEquals(64, EarItUtils.getOtherNoteOfInterval(MidiNote.C4, Interval.MAJOR_3RD).getNoteNumber());
    }

    @Test
    void interval_minorSecond_returnsCorrectNote() {
        // C4 (60) + 1 semitone = C#4/Db4 (61)
        assertEquals(61, EarItUtils.getOtherNoteOfInterval(MidiNote.C4, Interval.MINOR_2ND).getNoteNumber());
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

    // ── getIntervalContainer ──────────────────────────────────────────────────

    @Test
    void intervalContainer_perfectFifth_includesEnharmonicDiminishedSixth() {
        // Perfect 5th and Diminished 6th both = 7 semitones
        IntervalContainer c = EarItUtils.getIntervalContainer(Interval.PERFECT_5TH);
        assertEquals(List.of("Perfect 5th", "Diminished 6th"), c.getIntervals());
    }

    @Test
    void intervalContainer_minor2nd_includesAugmented1st() {
        // Minor 2nd and Augmented 1st both = 1 semitone
        IntervalContainer c = EarItUtils.getIntervalContainer(Interval.MINOR_2ND);
        assertEquals(List.of("Minor 2nd", "Augmented 1st"), c.getIntervals());
    }

    @Test
    void intervalContainer_augmented4th_includesDiminished5th() {
        // Augmented 4th and Diminished 5th both = 6 semitones (tritone)
        IntervalContainer c = EarItUtils.getIntervalContainer(Interval.AUGMENTED_4TH);
        assertEquals(List.of("Augmented 4th", "Diminished 5th"), c.getIntervals());
    }

    @Test
    void intervalContainer_augmentedEighth_isSingleton() {
        // 13 semitones — no enharmonic equivalent in the enum
        IntervalContainer c = EarItUtils.getIntervalContainer(Interval.AUGMENTED_8TH);
        assertEquals(List.of("Augmented 8th"), c.getIntervals());
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
