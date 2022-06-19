package com.github.shubhamjaggi.earit.model;

import com.github.shubhamjaggi.earit.constants.Interval;
import com.github.shubhamjaggi.earit.constants.MidiNote;
import com.github.shubhamjaggi.earit.constants.Status;
import com.github.shubhamjaggi.earit.utils.EarItUtils;

public final class EarItPlayerResponse {

	private final MidiNoteShortNameContainer midiNote;
	private final String chordType;
	private final String scaleType;
	private final IntervalContainer intervalContainer;
	private final Status status;

	public EarItPlayerResponse(MidiNote midiNote, String chordType, String scaleType, Interval interval,
			Status status) {

		this.midiNote = ((midiNote == null) ? null : EarItUtils.getMidiNoteShortNameContainer(midiNote));
		this.chordType = chordType;
		this.scaleType = scaleType;
		this.intervalContainer = ((interval == null) ? null : EarItUtils.getIntervalContainer(interval));
		this.status = status;
	}

	public MidiNoteShortNameContainer getMidiNote() {
		return midiNote;
	}

	public String getChordType() {
		return chordType;
	}

	public String getScaleType() {
		return scaleType;
	}

	public IntervalContainer getIntervalContainer() {
		return intervalContainer;
	}

	public Status getStatus() {
		return status;
	}

}