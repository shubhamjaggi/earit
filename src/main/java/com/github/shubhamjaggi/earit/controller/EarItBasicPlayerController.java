package com.github.shubhamjaggi.earit.controller;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.github.shubhamjaggi.earit.utils.EarItUtils;

@RestController
@RequestMapping("/earit/basic-player/")
public class EarItBasicPlayerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EarItBasicPlayerController.class);

	@GetMapping(value = "note", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EarItPlayerResponse> playNote(@RequestParam("note") String note) {
		ResponseEntity<EarItPlayerResponse> responseEntity;
		if (!EarItUtils.isBlank(note)) {
			try {
				MidiNote midiNote = EarItUtils.getMidiNoteFromShortName(note);
				Player.playNote(MidiProgram.ACOUSTIC_GRAND_PIANO.getProgramNumber(), midiNote,
						EarItConstants.DEFAULT_VELOCITY,
						EarItConstants.DEFAULT_NOTE_DURATION_IN_MILLISECONDS);
				responseEntity = new ResponseEntity<EarItPlayerResponse>(
						new EarItPlayerResponse(midiNote, null, null, null, Status.SUCCESS), HttpStatus.OK);
			} catch (NoSuchElementException noSuchElementException) {
				responseEntity = new ResponseEntity<EarItPlayerResponse>(
						new EarItPlayerResponse(null, null, null, null, Status.FAILED), HttpStatus.BAD_REQUEST);
				LOGGER.error(noSuchElementException.toString(), noSuchElementException);
			}
		} else {
			responseEntity = new ResponseEntity<EarItPlayerResponse>(
					new EarItPlayerResponse(null, null, null, null, Status.FAILED), HttpStatus.BAD_REQUEST);
			LOGGER.error("Blank request parameter provided!");
		}
		return responseEntity;
	}

	@GetMapping(value = "chord", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EarItPlayerResponse> playChord(@RequestParam("rootNote") String rootNote,
			@RequestParam("type") String type) {
		ResponseEntity<EarItPlayerResponse> responseEntity;
		if ((!EarItUtils.isBlank(rootNote)) && (!EarItUtils.isBlank(type))) {
			try {
				MidiNote midiNote = EarItUtils.getMidiNoteFromShortName(rootNote);
				ChordType chordType = Arrays.stream(ChordType.values()).filter(a -> a.getValue().equalsIgnoreCase(type))
						.findFirst().get();
				Player.playChord(MidiProgram.ACOUSTIC_GRAND_PIANO.getProgramNumber(), midiNote, chordType,
						EarItConstants.DEFAULT_VELOCITY,
						EarItConstants.DEFAULT_NOTE_DURATION_IN_MILLISECONDS);
				responseEntity = new ResponseEntity<EarItPlayerResponse>(
						new EarItPlayerResponse(midiNote, chordType.getValue(), null, null, Status.SUCCESS),
						HttpStatus.OK);
			} catch (NoSuchElementException noSuchElementException) {
				responseEntity = new ResponseEntity<EarItPlayerResponse>(
						new EarItPlayerResponse(null, null, null, null, Status.FAILED), HttpStatus.BAD_REQUEST);
				LOGGER.error(noSuchElementException.toString(), noSuchElementException);
			}
		} else {
			responseEntity = new ResponseEntity<EarItPlayerResponse>(
					new EarItPlayerResponse(null, null, null, null, Status.FAILED), HttpStatus.BAD_REQUEST);
			LOGGER.error("Blank request parameter provided!");
		}
		return responseEntity;
	}

	@GetMapping(value = "ascendingArpeggio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EarItPlayerResponse> playAscendingArpeggio(@RequestParam("rootNote") String rootNote,
			@RequestParam("type") String type) {
		ResponseEntity<EarItPlayerResponse> responseEntity;
		if ((!EarItUtils.isBlank(rootNote)) && (!EarItUtils.isBlank(type))) {
			try {
				MidiNote midiNote = EarItUtils.getMidiNoteFromShortName(rootNote);
				ChordType chordType = Arrays.stream(ChordType.values()).filter(a -> a.getValue().equalsIgnoreCase(type))
						.findFirst().get();
				Player.playAscendingArpeggio(MidiProgram.ACOUSTIC_GRAND_PIANO.getProgramNumber(), midiNote, chordType,
						EarItConstants.DEFAULT_NOTE_PLAY_TIME_GAP_IN_MILLISECONDS,
						EarItConstants.DEFAULT_VELOCITY,
						EarItConstants.DEFAULT_NOTE_DURATION_IN_MILLISECONDS);
				responseEntity = new ResponseEntity<EarItPlayerResponse>(
						new EarItPlayerResponse(midiNote, chordType.getValue(), null, null, Status.SUCCESS),
						HttpStatus.OK);
			} catch (NoSuchElementException noSuchElementException) {
				responseEntity = new ResponseEntity<EarItPlayerResponse>(
						new EarItPlayerResponse(null, null, null, null, Status.FAILED), HttpStatus.BAD_REQUEST);
				LOGGER.error(noSuchElementException.toString(), noSuchElementException);
			}
		} else {
			responseEntity = new ResponseEntity<EarItPlayerResponse>(
					new EarItPlayerResponse(null, null, null, null, Status.FAILED), HttpStatus.BAD_REQUEST);
			LOGGER.error("Blank request parameter provided!");
		}
		return responseEntity;
	}

	@GetMapping(value = "descendingArpeggio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EarItPlayerResponse> playDescendingArpeggio(@RequestParam("rootNote") String rootNote,
			@RequestParam("type") String type) {
		ResponseEntity<EarItPlayerResponse> responseEntity;
		if ((!EarItUtils.isBlank(rootNote)) && (!EarItUtils.isBlank(type))) {
			try {
				MidiNote midiNote = EarItUtils.getMidiNoteFromShortName(rootNote);
				ChordType chordType = Arrays.stream(ChordType.values()).filter(a -> a.getValue().equalsIgnoreCase(type))
						.findFirst().get();
				Player.playDescendingArpeggio(MidiProgram.ACOUSTIC_GRAND_PIANO.getProgramNumber(), midiNote, chordType,
						EarItConstants.DEFAULT_NOTE_PLAY_TIME_GAP_IN_MILLISECONDS,
						EarItConstants.DEFAULT_VELOCITY,
						EarItConstants.DEFAULT_NOTE_DURATION_IN_MILLISECONDS);
				responseEntity = new ResponseEntity<EarItPlayerResponse>(
						new EarItPlayerResponse(midiNote, chordType.getValue(), null, null, Status.SUCCESS),
						HttpStatus.OK);
			} catch (NoSuchElementException noSuchElementException) {
				responseEntity = new ResponseEntity<EarItPlayerResponse>(
						new EarItPlayerResponse(null, null, null, null, Status.FAILED), HttpStatus.BAD_REQUEST);
				LOGGER.error(noSuchElementException.toString(), noSuchElementException);
			}
		} else {
			responseEntity = new ResponseEntity<EarItPlayerResponse>(
					new EarItPlayerResponse(null, null, null, null, Status.FAILED), HttpStatus.BAD_REQUEST);
			LOGGER.error("Blank request parameter provided!");
		}
		return responseEntity;
	}

	@GetMapping(value = "scale", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EarItPlayerResponse> playScale(@RequestParam("rootNote") String rootNote,
			@RequestParam("type") String type) {
		ResponseEntity<EarItPlayerResponse> responseEntity;
		if ((!EarItUtils.isBlank(rootNote)) && (!EarItUtils.isBlank(type))) {
			try {
				MidiNote midiNote = EarItUtils.getMidiNoteFromShortName(rootNote);
				ScaleType scaleType = Arrays.stream(ScaleType.values()).filter(a -> a.getValue().equalsIgnoreCase(type))
						.findFirst().get();
				Player.playScale(MidiProgram.ACOUSTIC_GRAND_PIANO.getProgramNumber(), midiNote, scaleType,
						EarItConstants.DEFAULT_NOTE_PLAY_TIME_GAP_IN_MILLISECONDS,
						EarItConstants.DEFAULT_VELOCITY,
						EarItConstants.DEFAULT_NOTE_DURATION_IN_MILLISECONDS);
				responseEntity = new ResponseEntity<EarItPlayerResponse>(
						new EarItPlayerResponse(midiNote, null, scaleType.getValue(), null, Status.SUCCESS),
						HttpStatus.OK);
			} catch (NoSuchElementException noSuchElementException) {
				responseEntity = new ResponseEntity<EarItPlayerResponse>(
						new EarItPlayerResponse(null, null, null, null, Status.FAILED), HttpStatus.BAD_REQUEST);
				LOGGER.error(noSuchElementException.toString(), noSuchElementException);
			}
		} else {
			responseEntity = new ResponseEntity<EarItPlayerResponse>(
					new EarItPlayerResponse(null, null, null, null, Status.FAILED), HttpStatus.BAD_REQUEST);
			LOGGER.error("Blank request parameter provided!");
		}
		return responseEntity;
	}

	@GetMapping(value = "interval", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EarItPlayerResponse> playInterval(@RequestParam("rootNote") String rootNote,
			@RequestParam("intervalToPlay") String intervalToPlay) {
		ResponseEntity<EarItPlayerResponse> responseEntity;
		if ((!EarItUtils.isBlank(rootNote)) && (!EarItUtils.isBlank(intervalToPlay))) {
			try {
				MidiNote midiNote = EarItUtils.getMidiNoteFromShortName(rootNote);
				Interval interval = Arrays.stream(Interval.values())
						.filter(a -> a.getValue().equalsIgnoreCase(intervalToPlay)).findFirst().get();
				Player.playInterval(MidiProgram.ACOUSTIC_GRAND_PIANO.getProgramNumber(), midiNote, interval,
						EarItConstants.DEFAULT_NOTE_PLAY_TIME_GAP_IN_MILLISECONDS,
						EarItConstants.DEFAULT_VELOCITY,
						EarItConstants.DEFAULT_NOTE_DURATION_IN_MILLISECONDS);
				responseEntity = new ResponseEntity<EarItPlayerResponse>(
						new EarItPlayerResponse(midiNote, null, null, interval, Status.SUCCESS),
						HttpStatus.OK);
			} catch (NoSuchElementException noSuchElementException) {
				responseEntity = new ResponseEntity<EarItPlayerResponse>(
						new EarItPlayerResponse(null, null, null, null, Status.FAILED), HttpStatus.BAD_REQUEST);
				LOGGER.error(noSuchElementException.toString(), noSuchElementException);
			}
		} else {
			responseEntity = new ResponseEntity<EarItPlayerResponse>(
					new EarItPlayerResponse(null, null, null, null, Status.FAILED), HttpStatus.BAD_REQUEST);
			LOGGER.error("Blank request parameter provided!");
		}
		return responseEntity;
	}
}