package com.github.shubhamjaggi.earit.constants;

public enum Interval {
	
	PERFECT_4TH("Perfect 4th", 5),
	PERFECT_5TH("Perfect 5th", 7),
	PERFECT_8TH("Perfect 8th", 12),
	MAJOR_2ND("Major 2nd", 2),
	MAJOR_3RD("Major 3rd", 4),
	MAJOR_6TH("Major 6th", 9),
	MAJOR_7TH("Major 7th", 11),
	MINOR_2ND("Minor 2nd", 1),
	MINOR_3RD("Minor 3rd", 3),
	MINOR_6TH("Minor 6th", 8),
	MINOR_7TH("Minor 7th", 10),
	AUGMENTED_1ST("Augmented 1st", 1),
	AUGMENTED_2ND("Augmented 2nd", 3),
	AUGMENTED_3RD("Augmented 3rd", 5),
	AUGMENTED_4TH("Augmented 4th", 6),
	AUGMENTED_5TH("Augmented 5th", 8),
	AUGMENTED_6TH("Augmented 6th", 10),
	AUGMENTED_7TH("Augmented 7th", 12),
	AUGMENTED_8TH("Augmented 8th", 13),
	DIMINISHED_3RD("Diminished 3rd", 2),
	DIMINISHED_4TH("Diminished 4th", 4),
	DIMINISHED_5TH("Diminished 5th", 6),
	DIMINISHED_6TH("Diminished 6th", 7),
	DIMINISHED_7TH("Diminished 7th", 9),
	DIMINISHED_8TH("Diminished 8th", 11);

	private final String value;
	private int numberOfSemitones;

	private Interval(String value, int numberOfSemitones) {
		this.value = value;
		this.numberOfSemitones = numberOfSemitones;
	}

	public String getValue() {
		return value;
	}

	public int getNumberOfSemitones() {
		return numberOfSemitones;
	}

}
