package com.github.shubhamjaggi.earit.utils;

import java.util.Arrays;
import java.util.Random;

import com.github.shubhamjaggi.earit.constants.ChordType;
import com.github.shubhamjaggi.earit.constants.EarItConstants;
import com.github.shubhamjaggi.earit.constants.Interval;
import com.github.shubhamjaggi.earit.constants.MidiNote;
import com.github.shubhamjaggi.earit.constants.ScaleType;

public class EarItRandomizerUtils {

	public static MidiNote getRandomMidiNote() {
		int randomNoteNumber = new Random().nextInt(
				(MidiNote.A0.getNoteNumber() + EarItConstants.MIDI_NOTE_RANDOMIZER_OFFSET),
				((MidiNote.G9.getNoteNumber() + 1) - EarItConstants.MIDI_NOTE_RANDOMIZER_OFFSET));
		return Arrays.stream(MidiNote.values()).filter(a -> (a.getNoteNumber() == randomNoteNumber)).findAny().get();
	}

	public static ChordType getRandomChordType() {
		ChordType[] chordTypes = ChordType.values();
		int randomChordTypeIndex = new Random().nextInt(chordTypes.length);
		return chordTypes[randomChordTypeIndex];
	}

	public static ScaleType getRandomScaleType() {
		ScaleType[] scaleTypes = ScaleType.values();
		int randomScaleTypeIndex = new Random().nextInt(scaleTypes.length);
		return scaleTypes[randomScaleTypeIndex];
	}

	public static Interval getRandomInterval() {
		Interval[] intervals = Interval.values();
		int randomIntervalIndex = new Random().nextInt(intervals.length);
		return intervals[randomIntervalIndex];
	}
}