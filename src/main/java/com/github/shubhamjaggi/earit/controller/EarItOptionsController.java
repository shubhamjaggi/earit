package com.github.shubhamjaggi.earit.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.shubhamjaggi.earit.constants.ChordType;
import com.github.shubhamjaggi.earit.constants.Interval;
import com.github.shubhamjaggi.earit.constants.MidiNote;
import com.github.shubhamjaggi.earit.constants.ScaleType;

@RestController
@RequestMapping("/earit/options/")
public class EarItOptionsController {

	@GetMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getAllNotes() {
		return Arrays.stream(MidiNote.values()).map(a -> a.getShortName()).toList();
	}

	@GetMapping(value = "/chordTypes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getAllChordTypes() {
		return Arrays.stream(ChordType.values()).map(a -> a.getValue()).toList();
	}

	@GetMapping(value = "/scaleTypes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getAllScaleTypes() {
		return Arrays.stream(ScaleType.values()).map(a -> a.getValue()).toList();
	}

	@GetMapping(value = "/intervals", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getAllIntervals() {
		return Arrays.stream(Interval.values()).map(a -> a.getValue()).toList();
	}
}