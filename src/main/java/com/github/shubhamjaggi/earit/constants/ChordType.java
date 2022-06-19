package com.github.shubhamjaggi.earit.constants;

public enum ChordType {

	MAJOR("Major"),
	MINOR("Minor"),
	DIMINISHED("Diminished"),
	MAJOR_7TH("Major 7th"),
	DOMINANT_7TH("Dominant 7th"),
	MINOR_7TH("Minor 7th"),
	MINOR_7TH_FLAT_5("Minor 7th Flat 5");

	private final String value;

	private ChordType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
