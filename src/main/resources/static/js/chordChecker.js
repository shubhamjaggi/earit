$(document).ready(function() {
	let output;
	let chordTypes;

	$.get("/earit/options/chordTypes", function(data) {
		chordTypes = data;
		$("#answerChord").autocomplete({
			source: chordTypes
		});
	});

	$("div.chord > button.play").click(function() {
		$.get("/earit/randomizer/chord", function(data) {
			output = data;
		});
	});

	$("div.chord > button.playAgain").click(function() {
		$.get("/earit/basic-player/chord", {
			rootNote: output.midiNote.shortName1,
			type: output.chordType
		});
	});

	$("div.chord > button.arpeggiate.asc").click(function() {
		$.get("/earit/basic-player/ascendingArpeggio", {
			rootNote: output.midiNote.shortName1,
			type: output.chordType
		});
	});

	$("div.chord > button.arpeggiate.desc").click(function() {
		$.get("/earit/basic-player/descendingArpeggio", {
			rootNote: output.midiNote.shortName1,
			type: output.chordType
		});
	});

	$("div.chord > button.check").click(function() {
		$(this).hide();
		$("div.chord.logicalPartition #answerChord").hide();
		$("div.chord.logicalPartition .playAgain").hide();
		$("div.chord.logicalPartition .arpeggiate").hide();
		if ($("#answerChord").val() === output.chordType) {
			$("div.chord.logicalPartition .iconForCorrectAnswer").show();
		}
		else {
			$("div.chord.logicalPartition .iconForWrongAnswer").show();
			$("div.chord.logicalPartition .correctAnswer").text("Sorry! That was ".concat(output.chordType));
		}
	});

});