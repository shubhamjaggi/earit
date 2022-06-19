package com.github.shubhamjaggi.earit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.shubhamjaggi.earit.constants.ChordType;
import com.github.shubhamjaggi.earit.constants.EarItConstants;
import com.github.shubhamjaggi.earit.constants.Interval;
import com.github.shubhamjaggi.earit.constants.MidiNote;
import com.github.shubhamjaggi.earit.constants.MidiProgram;
import com.github.shubhamjaggi.earit.constants.ScaleType;
import com.github.shubhamjaggi.earit.constants.Status;
import com.github.shubhamjaggi.earit.model.EarItPlayerResponse;
import com.github.shubhamjaggi.earit.player.Player;
import com.github.shubhamjaggi.earit.utils.EarItRandomizerUtils;

@RestController
@RequestMapping("/earit/randomizer/")
public class EarItRandomizerController {

	@GetMapping(value = "note", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EarItPlayerResponse> playRandomNote() {
		MidiNote randomMidiNote = EarItRandomizerUtils.getRandomMidiNote();
		Player.playNote(MidiProgram.ACOUSTIC_GRAND_PIANO.getProgramNumber(), randomMidiNote,
				EarItConstants.DEFAULT_VELOCITY, EarItConstants.DEFAULT_NOTE_DURATION_IN_MILLISECONDS);
		return new ResponseEntity<>(new EarItPlayerResponse(randomMidiNote, null, null, null, Status.SUCCESS),
				HttpStatus.OK);
	}

	@GetMapping(value = "chord", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EarItPlayerResponse> playRandomChord() {
		MidiNote randomMidiNote = EarItRandomizerUtils.getRandomMidiNote();
		ChordType randomChordType = EarItRandomizerUtils.getRandomChordType();
		Player.playChord(MidiProgram.ACOUSTIC_GRAND_PIANO.getProgramNumber(), randomMidiNote, randomChordType,
				EarItConstants.DEFAULT_VELOCITY, EarItConstants.DEFAULT_NOTE_DURATION_IN_MILLISECONDS);
		return new ResponseEntity<>(
				new EarItPlayerResponse(randomMidiNote, randomChordType.getValue(), null, null, Status.SUCCESS),
				HttpStatus.OK);
	}

	@GetMapping(value = "ascendingArpeggio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EarItPlayerResponse> playRandomAscendingArpeggio() {
		MidiNote randomMidiNote = EarItRandomizerUtils.getRandomMidiNote();
		ChordType randomChordType = EarItRandomizerUtils.getRandomChordType();
		Player.playAscendingArpeggio(MidiProgram.ACOUSTIC_GRAND_PIANO.getProgramNumber(), randomMidiNote,
				randomChordType, EarItConstants.DEFAULT_NOTE_PLAY_TIME_GAP_IN_MILLISECONDS,
				EarItConstants.DEFAULT_VELOCITY, EarItConstants.DEFAULT_NOTE_DURATION_IN_MILLISECONDS);
		return new ResponseEntity<>(
				new EarItPlayerResponse(randomMidiNote, randomChordType.getValue(), null, null, Status.SUCCESS),
				HttpStatus.OK);
	}

	@GetMapping(value = "descendingArpeggio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EarItPlayerResponse> playRandomDescendingArpeggio() {
		MidiNote randomMidiNote = EarItRandomizerUtils.getRandomMidiNote();
		ChordType randomChordType = EarItRandomizerUtils.getRandomChordType();
		Player.playDescendingArpeggio(MidiProgram.ACOUSTIC_GRAND_PIANO.getProgramNumber(), randomMidiNote,
				randomChordType, EarItConstants.DEFAULT_NOTE_PLAY_TIME_GAP_IN_MILLISECONDS,
				EarItConstants.DEFAULT_VELOCITY, EarItConstants.DEFAULT_NOTE_DURATION_IN_MILLISECONDS);
		return new ResponseEntity<>(
				new EarItPlayerResponse(randomMidiNote, randomChordType.getValue(), null, null, Status.SUCCESS),
				HttpStatus.OK);
	}

	@GetMapping(value = "scale", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EarItPlayerResponse> playRandomScale() {
		MidiNote randomMidiNote = EarItRandomizerUtils.getRandomMidiNote();
		ScaleType randomScaleType = EarItRandomizerUtils.getRandomScaleType();
		Player.playScale(MidiProgram.ACOUSTIC_GRAND_PIANO.getProgramNumber(), randomMidiNote, randomScaleType,
				EarItConstants.DEFAULT_NOTE_PLAY_TIME_GAP_IN_MILLISECONDS, EarItConstants.DEFAULT_VELOCITY,
				EarItConstants.DEFAULT_NOTE_DURATION_IN_MILLISECONDS);
		return new ResponseEntity<>(
				new EarItPlayerResponse(randomMidiNote, null, randomScaleType.getValue(), null, Status.SUCCESS),
				HttpStatus.OK);
	}

	@GetMapping(value = "interval", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EarItPlayerResponse> playRandomInterval() {
		MidiNote randomMidiNote = EarItRandomizerUtils.getRandomMidiNote();
		Interval randomInterval = EarItRandomizerUtils.getRandomInterval();
		Player.playInterval(MidiProgram.ACOUSTIC_GRAND_PIANO.getProgramNumber(), randomMidiNote, randomInterval,
				EarItConstants.DEFAULT_NOTE_PLAY_TIME_GAP_IN_MILLISECONDS, EarItConstants.DEFAULT_VELOCITY,
				EarItConstants.DEFAULT_NOTE_DURATION_IN_MILLISECONDS);
		return new ResponseEntity<>(
				new EarItPlayerResponse(randomMidiNote, null, null, randomInterval, Status.SUCCESS),
				HttpStatus.OK);
	}

}