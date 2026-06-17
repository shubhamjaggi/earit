package com.github.shubhamjaggi.earit.model;

import com.github.shubhamjaggi.earit.constants.Interval;
import com.github.shubhamjaggi.earit.constants.MidiNote;
import com.github.shubhamjaggi.earit.constants.Status;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EarItPlayerResponseTest {

    // ── note response ─────────────────────────────────────────────────────────

    @Test
    void noteResponse_naturalNote_setsShortNamesCorrectly() {
        EarItPlayerResponse r = new EarItPlayerResponse(MidiNote.C4, null, null, null, Status.SUCCESS);

        assertEquals("C4", r.getMidiNote().getShortName1());
        assertEquals("", r.getMidiNote().getShortName2());
        assertNull(r.getChordType());
        assertNull(r.getScaleType());
        assertNull(r.getIntervalContainer());
        assertEquals(Status.SUCCESS, r.getStatus());
    }

    @Test
    void noteResponse_accidentalNote_setsEnharmonicPairCorrectly() {
        // C#4 and Db4 share note 61; shortName1 = sharp, shortName2 = flat (enum declaration order)
        EarItPlayerResponse r = new EarItPlayerResponse(MidiNote.C_SHARP_4, null, null, null, Status.SUCCESS);

        assertEquals("C#4", r.getMidiNote().getShortName1());
        assertEquals("Db4", r.getMidiNote().getShortName2());
        assertEquals(Status.SUCCESS, r.getStatus());
    }

    // ── chord response ────────────────────────────────────────────────────────

    @Test
    void chordResponse_setsChordTypeAndMidiNote() {
        EarItPlayerResponse r = new EarItPlayerResponse(MidiNote.C4, "Minor 7th", null, null, Status.SUCCESS);

        assertEquals("C4", r.getMidiNote().getShortName1());
        assertEquals("Minor 7th", r.getChordType());
        assertNull(r.getScaleType());
        assertNull(r.getIntervalContainer());
        assertEquals(Status.SUCCESS, r.getStatus());
    }

    // ── scale response ────────────────────────────────────────────────────────

    @Test
    void scaleResponse_setsScaleTypeAndMidiNote() {
        EarItPlayerResponse r = new EarItPlayerResponse(MidiNote.C4, null, "Harmonic Minor", null, Status.SUCCESS);

        assertEquals("C4", r.getMidiNote().getShortName1());
        assertNull(r.getChordType());
        assertEquals("Harmonic Minor", r.getScaleType());
        assertNull(r.getIntervalContainer());
        assertEquals(Status.SUCCESS, r.getStatus());
    }

    // ── interval response ─────────────────────────────────────────────────────

    @Test
    void intervalResponse_setsIntervalContainerWithAllEnharmonicEquivalents() {
        // Perfect 5th = 7 semitones; enharmonic group = ["Perfect 5th", "Diminished 6th"]
        EarItPlayerResponse r = new EarItPlayerResponse(MidiNote.C4, null, null, Interval.PERFECT_5TH, Status.SUCCESS);

        assertEquals("C4", r.getMidiNote().getShortName1());
        assertNull(r.getChordType());
        assertNull(r.getScaleType());
        assertEquals(List.of("Perfect 5th", "Diminished 6th"), r.getIntervalContainer().getIntervals());
        assertEquals(Status.SUCCESS, r.getStatus());
    }

    @Test
    void intervalResponse_augmented8th_singletonContainer() {
        // Augmented 8th = 13 semitones; no enharmonic equivalent
        EarItPlayerResponse r = new EarItPlayerResponse(MidiNote.C4, null, null, Interval.AUGMENTED_8TH, Status.SUCCESS);

        assertEquals(List.of("Augmented 8th"), r.getIntervalContainer().getIntervals());
    }

    // ── failure response ──────────────────────────────────────────────────────

    @Test
    void failureResponse_allFieldsNull_statusFailed() {
        EarItPlayerResponse r = new EarItPlayerResponse(null, null, null, null, Status.FAILED);

        assertNull(r.getMidiNote());
        assertNull(r.getChordType());
        assertNull(r.getScaleType());
        assertNull(r.getIntervalContainer());
        assertEquals(Status.FAILED, r.getStatus());
    }
}
