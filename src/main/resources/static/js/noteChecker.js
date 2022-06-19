$(document).ready(function() {
	let output;
	let notes;

	$.get("/earit/options/notes", function(data) {
		notes = data;
		$("#answerNote").autocomplete({
			source: notes
		});
	});

	$("div.note > button.play").click(function() {
		$.get("/earit/randomizer/note", function(data) {
			output = data;
		});
	});

	$("div.note > button.playAgain").click(function() {
		$.get("/earit/basic-player/note", {
			note: output.midiNote.shortName1
		});
	});

	$("div.note > button.check").click(function() {
		$(this).hide();
		$("div.note.logicalPartition #answerNote").hide();
		$("div.note.logicalPartition .playAgain").hide();
		if ($("#answerNote").val() === "") {
			$("div.note.logicalPartition .iconForWrongAnswer").show();
			$("div.note.logicalPartition .correctAnswer").text("Sorry! That was ".concat(output.midiNote.shortName1));
		}
		else {
			if ($("#answerNote").val() === output.midiNote.shortName1) {
				$("div.note.logicalPartition .iconForCorrectAnswer").show();
			} else if ($("#answerNote").val() === output.midiNote.shortName2) {
				$("div.note.logicalPartition .iconForCorrectAnswer").show();
			}
			else {
				$("div.note.logicalPartition .iconForWrongAnswer").show();
				$("div.note.logicalPartition .correctAnswer").text("Sorry! That was ".concat(output.midiNote.shortName1));
			}
		}
	});

});