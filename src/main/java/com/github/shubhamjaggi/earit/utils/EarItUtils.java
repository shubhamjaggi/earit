package com.github.shubhamjaggi.earit.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.github.shubhamjaggi.earit.constants.ChordType;
import com.github.shubhamjaggi.earit.constants.EarItConstants;
import com.github.shubhamjaggi.earit.constants.Interval;
import com.github.shubhamjaggi.earit.constants.MidiNote;
import com.github.shubhamjaggi.earit.constants.ScaleType;
import com.github.shubhamjaggi.earit.model.IntervalContainer;
import com.github.shubhamjaggi.earit.model.MidiNoteShortNameContainer;

public class EarItUtils {

	public static List<MidiNote> getMidiNotesForChord(MidiNote rootNote, ChordType chordType) {
		List<MidiNote> midiNotes = new ArrayList<>();
		midiNotes.add(rootNote);
		if (chordType.equals(ChordType.MAJOR)) {
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 4));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 7));
		} else if (chordType.equals(ChordType.MINOR)) {
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 3));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 7));
		} else if (chordType.equals(ChordType.DIMINISHED)) {
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 3));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 6));
		} else if (chordType.equals(ChordType.MAJOR_7TH)) {
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 4));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 7));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 11));
		} else if (chordType.equals(ChordType.DOMINANT_7TH)) {
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 4));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 7));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 10));
		} else if (chordType.equals(ChordType.MINOR_7TH)) {
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 3));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 7));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 10));
		} else if (chordType.equals(ChordType.MINOR_7TH_FLAT_5)) {
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 3));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 6));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 10));
		}
		return midiNotes;
	}

	public static List<MidiNote> getMidiNotesForScale(MidiNote rootNote, ScaleType scaleType) {
		List<MidiNote> midiNotes = new ArrayList<>();
		midiNotes.add(rootNote);
		if (scaleType.equals(ScaleType.MAJOR)) {
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 2));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 4));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 5));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 7));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 9));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 11));
		} else if (scaleType.equals(ScaleType.NATURAL_MINOR)) {
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 2));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 3));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 5));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 7));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 8));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 10));
		} else if (scaleType.equals(ScaleType.HARMONIC_MINOR)) {
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 2));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 3));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 5));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 7));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 8));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 11));
		} else if (scaleType.equals(ScaleType.MAJOR_PENTATONIC)) {
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 2));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 4));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 7));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 9));
		} else if (scaleType.equals(ScaleType.MINOR_PENTATONIC)) {
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 3));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 5));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 7));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 10));
		} else if (scaleType.equals(ScaleType.MAJOR_BLUES)) {
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 2));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 3));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 4));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 7));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 9));
		} else if (scaleType.equals(ScaleType.MINOR_BLUES)) {
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 3));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 5));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 6));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 7));
			midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 10));
		}
		midiNotes.add(getMidiNoteByNoteNumber(rootNote.getNoteNumber() + 12));
		return midiNotes;
	}

	public static MidiNote getOtherNoteOfInterval(MidiNote rootNote, Interval interval) {
		int otherNoteNumber = (rootNote.getNoteNumber() + interval.getNumberOfSemitones());
		return getMidiNoteByNoteNumber(otherNoteNumber);
	}

	public static boolean isBlank(String value) {
		return ((value == null) || value.trim().equals(""));
	}

	public static MidiNoteShortNameContainer getMidiNoteShortNameContainer(MidiNote midiNote) {
		MidiNoteShortNameContainer midiNoteShortNameContainer;
		if (midiNote.getShortName().length() == 3) {
			List<String> shortNames = Arrays.stream(MidiNote.values())
					.filter(a -> (a.getNoteNumber() == midiNote.getNoteNumber())).map(b -> b.getShortName()).toList();
			midiNoteShortNameContainer = new MidiNoteShortNameContainer(shortNames.get(0), shortNames.get(1));
		} else {
			midiNoteShortNameContainer = new MidiNoteShortNameContainer(midiNote.getShortName(),
					EarItConstants.EMPTY);
		}
		return midiNoteShortNameContainer;
	}

	public static IntervalContainer getIntervalContainer(Interval interval) {
		List<String> intervals = Arrays.stream(Interval.values())
				.filter(a -> (a.getNumberOfSemitones() == interval.getNumberOfSemitones())).map(b -> b.getValue())
				.toList();
		IntervalContainer intervalContainer = new IntervalContainer(intervals);
		return intervalContainer;
	}

	public static MidiNote getMidiNoteFromShortName(String shortName) {
		Optional<MidiNote> midiNoteOptional = Arrays.stream(MidiNote.values())
				.filter(a -> a.getShortName().equals(shortName)).findAny();
		return midiNoteOptional.get();
	}

	private static MidiNote getMidiNoteByNoteNumber(int noteNumber) {
		return Stream.of(MidiNote.values()).filter(a -> (noteNumber == a.getNoteNumber())).findFirst().get();
	}
}