package com.github.shubhamjaggi.earit.model;

public final class MidiNoteShortNameContainer {

	private final String shortName1;
	private final String shortName2;

	public MidiNoteShortNameContainer(String shortName1, String shortName2) {
		this.shortName1 = shortName1;
		this.shortName2 = shortName2;
	}

	public String getShortName1() {
		return shortName1;
	}

	public String getShortName2() {
		return shortName2;
	}

}