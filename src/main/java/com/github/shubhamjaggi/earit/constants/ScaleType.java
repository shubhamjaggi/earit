package com.github.shubhamjaggi.earit.constants;

public enum ScaleType {

	MAJOR("Major"),
	NATURAL_MINOR("Natural Minor"),
	HARMONIC_MINOR("Harmonic Minor"),
	MAJOR_PENTATONIC("Major Pentatonic"),
	MINOR_PENTATONIC("Minor Pentatonic"),
	MAJOR_BLUES("Major Blues"),
	MINOR_BLUES("Minor Blues");


	private final String value;

	private ScaleType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
