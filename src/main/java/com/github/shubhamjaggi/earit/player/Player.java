package com.github.shubhamjaggi.earit.player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.shubhamjaggi.earit.constants.ChordType;
import com.github.shubhamjaggi.earit.constants.Interval;
import com.github.shubhamjaggi.earit.constants.MidiNote;
import com.github.shubhamjaggi.earit.constants.ScaleType;
import com.github.shubhamjaggi.earit.utils.EarItUtils;

public class Player {

	private static Synthesizer synthesizer;
	private static MidiChannel midiChannel;
	private static final Logger LOGGER = LoggerFactory.getLogger(Player.class);

	static {
		try {
			synthesizer = MidiSystem.getSynthesizer();
			synthesizer.open();
		} catch (MidiUnavailableException midiUnavailableException) {
			throw new RuntimeException(midiUnavailableException);
		}
		midiChannel = synthesizer.getChannels()[0];
	}

	public static void playNote(int program, MidiNote midiNote, int velocity, long noteDurationInMilliseconds) {
		LOGGER.info(" playing note: " + midiNote.toString());
		midiChannel.programChange(program);
		midiChannel.noteOn(midiNote.getNoteNumber(), velocity);
		LOGGER.info(" played note: " + midiNote.toString());
		try {
			Thread.sleep(noteDurationInMilliseconds);
		} catch (InterruptedException interruptedException) {
			throw new RuntimeException(interruptedException);
		}
		midiChannel.noteOff(midiNote.getNoteNumber());
	}

	public static void playChord(int program, MidiNote chordRootNote, ChordType chordType, int velocity,
			long noteDurationInMilliseconds) {
		LOGGER.info(" playing chord: " + chordRootNote.toString() + " " + chordType.toString());
		midiChannel.programChange(program);
		List<MidiNote> midiNotes = EarItUtils.getMidiNotesForChord(chordRootNote, chordType);
		for (MidiNote midiNote : midiNotes) {
			midiChannel.noteOn(midiNote.getNoteNumber(), velocity);
			LOGGER.info(" played note: " + midiNote.toString());
		}
		try {
			Thread.sleep(noteDurationInMilliseconds);
		} catch (InterruptedException interruptedException) {
			throw new RuntimeException(interruptedException);
		}
		for (MidiNote midiNote : midiNotes) {
			midiChannel.noteOff(midiNote.getNoteNumber());
		}
	}

	public static void playAscendingArpeggio(int program, MidiNote chordRootNote, ChordType chordType,
			long arpeggioTimeGap, int velocity, long lastNoteDurationInMilliseconds) {
		LOGGER.info(" playing ascending arpeggio: " + chordRootNote.toString() + " " + chordType.toString());
		midiChannel.programChange(program);
		List<MidiNote> midiNotes = EarItUtils.getMidiNotesForChord(chordRootNote, chordType);
		for (MidiNote midiNote : midiNotes) {
			try {
				Thread.sleep(arpeggioTimeGap);
			} catch (InterruptedException interruptedException) {
				throw new RuntimeException(interruptedException);
			}
			midiChannel.noteOn(midiNote.getNoteNumber(), velocity);
			LOGGER.info(" played note: " + midiNote.toString());
		}
		try {
			Thread.sleep(lastNoteDurationInMilliseconds);
		} catch (InterruptedException interruptedException) {
			throw new RuntimeException(interruptedException);
		}
		for (MidiNote midiNote : midiNotes) {
			midiChannel.noteOff(midiNote.getNoteNumber());
		}
	}

	public static void playDescendingArpeggio(int program, MidiNote chordRootNote, ChordType chordType,
			long arpeggioTimeGap, int velocity, long lastNoteDurationInMilliseconds) {
		LOGGER.info(" playing descending arpeggio: " + chordRootNote.toString() + " " + chordType.toString());
		midiChannel.programChange(program);
		List<MidiNote> midiNotes = EarItUtils.getMidiNotesForChord(chordRootNote, chordType);
		List<MidiNote> midiNotesReversed = new ArrayList<>();
		IntStream.range(0, midiNotes.size()).map(a -> ((midiNotes.size() - 1) - a))
				.forEach(a -> midiNotesReversed.add(midiNotes.get(a)));
		for (MidiNote midiNote : midiNotesReversed) {
			try {
				Thread.sleep(arpeggioTimeGap);
			} catch (InterruptedException interruptedException) {
				throw new RuntimeException(interruptedException);
			}
			midiChannel.noteOn(midiNote.getNoteNumber(), velocity);
			LOGGER.info(" played note: " + midiNote.toString());
		}
		try {
			Thread.sleep(lastNoteDurationInMilliseconds);
		} catch (InterruptedException interruptedException) {
			throw new RuntimeException(interruptedException);
		}
		for (MidiNote midiNote : midiNotesReversed) {
			midiChannel.noteOff(midiNote.getNoteNumber());
		}
	}

	public static void playScale(int program, MidiNote scaleRootNote, ScaleType scaleType, long scaleTimeGap,
			int velocity, long lastNoteDurationInMilliseconds) {
		LOGGER.info(" playing scale: " + scaleRootNote.toString() + " " + scaleType.toString());

		midiChannel.programChange(program);
		List<MidiNote> midiNotes = EarItUtils.getMidiNotesForScale(scaleRootNote, scaleType);
		List<MidiNote> midiNotesReversed = new ArrayList<>();
		IntStream.range(0, midiNotes.size()).map(a -> ((midiNotes.size() - 1) - a))
				.forEach(a -> midiNotesReversed.add(midiNotes.get(a)));
		midiNotes.addAll(midiNotesReversed);
		for (MidiNote midiNote : midiNotes) {
			try {
				Thread.sleep(scaleTimeGap);
			} catch (InterruptedException interruptedException) {
				throw new RuntimeException(interruptedException);
			}
			midiChannel.noteOn(midiNote.getNoteNumber(), velocity);
			LOGGER.info(" played note: " + midiNote.toString());
		}
		try {
			Thread.sleep(lastNoteDurationInMilliseconds);
		} catch (InterruptedException interruptedException) {
			throw new RuntimeException(interruptedException);
		}
		for (MidiNote midiNote : midiNotes) {
			midiChannel.noteOff(midiNote.getNoteNumber());
		}
	}

	public static void playInterval(int program, MidiNote intervalRootNote, Interval interval, long intervalTimeGap,
			int velocity, long lastNoteDurationInMilliseconds) {
		LOGGER.info(" playing interval: " + intervalRootNote.toString() + " " + interval.toString());
		midiChannel.programChange(program);
		MidiNote intervalOtherNote = EarItUtils.getOtherNoteOfInterval(intervalRootNote, interval);
		midiChannel.noteOn(intervalRootNote.getNoteNumber(), velocity);
		LOGGER.info(" played note: " + intervalRootNote.toString());
		try {
			Thread.sleep(intervalTimeGap);
		} catch (InterruptedException interruptedException) {
			throw new RuntimeException(interruptedException);
		}
		midiChannel.noteOn(intervalOtherNote.getNoteNumber(), velocity);
		LOGGER.info(" played note: " + intervalOtherNote.toString());

		try {
			Thread.sleep(lastNoteDurationInMilliseconds);
		} catch (InterruptedException interruptedException) {
			throw new RuntimeException(interruptedException);
		}

		midiChannel.noteOff(intervalRootNote.getNoteNumber());
		midiChannel.noteOff(intervalOtherNote.getNoteNumber());
	}

	public static void finish() {
		synthesizer.close();
	}

}