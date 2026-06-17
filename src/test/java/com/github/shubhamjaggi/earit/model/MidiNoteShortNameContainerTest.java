package com.github.shubhamjaggi.earit.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MidiNoteShortNameContainerTest {

    @Test
    void getters_returnCorrectValuesForAccidentalNote() {
        MidiNoteShortNameContainer container = new MidiNoteShortNameContainer("C#4", "Db4");
        assertEquals("C#4", container.getShortName1());
        assertEquals("Db4", container.getShortName2());
    }

    @Test
    void getters_returnCorrectValuesForNaturalNote() {
        MidiNoteShortNameContainer container = new MidiNoteShortNameContainer("C4", "");
        assertEquals("C4", container.getShortName1());
        assertEquals("", container.getShortName2());
    }
}
