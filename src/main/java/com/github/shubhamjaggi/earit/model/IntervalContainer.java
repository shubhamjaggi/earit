package com.github.shubhamjaggi.earit.model;

import java.util.List;

public class IntervalContainer {

	private final List<String> intervals;

	public IntervalContainer(List<String> intervals) {
		this.intervals = intervals;
	}

	public List<String> getIntervals() {
		return intervals;
	}

}
