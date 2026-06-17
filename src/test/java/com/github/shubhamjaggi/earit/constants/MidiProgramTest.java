package com.github.shubhamjaggi.earit.constants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MidiProgramTest {

    @Test
    void acousticGrandPiano_isProgram0() {
        assertEquals(0, MidiProgram.ACOUSTIC_GRAND_PIANO.getProgramNumber());
    }

    @Test
    void brightAcousticPiano_isProgram1() {
        assertEquals(1, MidiProgram.BRIGHT_ACOUSTIC_PIANO.getProgramNumber());
    }

    @Test
    void electricGrandPiano_isProgram2() {
        assertEquals(2, MidiProgram.ELECTRIC_GRAND_PIANO.getProgramNumber());
    }

    @Test
    void values_containsThreePrograms() {
        assertEquals(3, MidiProgram.values().length);
    }
}
