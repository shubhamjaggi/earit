package com.github.shubhamjaggi.earit.utils;

import com.github.shubhamjaggi.earit.constants.ChordType;
import com.github.shubhamjaggi.earit.constants.EarItConstants;
import com.github.shubhamjaggi.earit.constants.Interval;
import com.github.shubhamjaggi.earit.constants.MidiNote;
import com.github.shubhamjaggi.earit.constants.ScaleType;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EarItRandomizerUtilsTest {

    // Random.nextInt(origin, bound) returns [origin, bound).
    // origin = A0 (21) + 25 = 46
    // bound  = G9 (127) + 1 - 25 = 103  →  max returned value = 102
    private static final int MIN_NOTE = MidiNote.A0.getNoteNumber() + EarItConstants.MIDI_NOTE_RANDOMIZER_OFFSET;
    private static final int MAX_NOTE = MidiNote.G9.getNoteNumber() - EarItConstants.MIDI_NOTE_RANDOMIZER_OFFSET;

    @RepeatedTest(50)
    void getRandomMidiNote_alwaysWithinValidRange() {
        MidiNote note = EarItRandomizerUtils.getRandomMidiNote();
        assertNotNull(note);
        assertTrue(note.getNoteNumber() >= MIN_NOTE,
                "Note number " + note.getNoteNumber() + " is below minimum " + MIN_NOTE);
        assertTrue(note.getNoteNumber() <= MAX_NOTE,
                "Note number " + note.getNoteNumber() + " is above maximum " + MAX_NOTE);
    }

    @Test
    void getRandomChordType_returnsValidChordType() {
        ChordType result = EarItRandomizerUtils.getRandomChordType();
        assertNotNull(result);
        assertTrue(Arrays.asList(ChordType.values()).contains(result));
    }

    @Test
    void getRandomScaleType_returnsValidScaleType() {
        ScaleType result = EarItRandomizerUtils.getRandomScaleType();
        assertNotNull(result);
        assertTrue(Arrays.asList(ScaleType.values()).contains(result));
    }

    @Test
    void getRandomInterval_returnsValidInterval() {
        Interval result = EarItRandomizerUtils.getRandomInterval();
        assertNotNull(result);
        assertTrue(Arrays.asList(Interval.values()).contains(result));
    }
}
