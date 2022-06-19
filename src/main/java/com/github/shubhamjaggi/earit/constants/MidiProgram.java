package com.github.shubhamjaggi.earit.constants;

public enum MidiProgram {

	ACOUSTIC_GRAND_PIANO(0), BRIGHT_ACOUSTIC_PIANO(1), ELECTRIC_GRAND_PIANO(2);

	private final int programNumber;

	private MidiProgram(int programNumber) {
		this.programNumber = programNumber;
	}

	public int getProgramNumber() {
		return programNumber;
	}

}
