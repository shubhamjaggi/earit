$(document).ready(function() {
	let output;
	let intervals;

	$.get("/earit/options/intervals", function(data) {
		intervals = data;
		$("#answerInterval").autocomplete({
			source: intervals
		});
	});

	$("div.interval > button.play").click(function() {
		$.get("/earit/randomizer/interval", function(data) {
			output = data;
		});
	});

	$("div.interval > button.playAgain").click(function() {
		$.get("/earit/basic-player/interval", {
			rootNote: output.midiNote.shortName1,
			intervalToPlay: output.intervalContainer.intervals[0]
		});
	});

	$("div.interval > button.check").click(function() {
		$(this).hide();
		$("div.interval.logicalPartition #answerInterval").hide();
		$("div.interval.logicalPartition .playAgain").hide();
		if (output.intervalContainer.intervals.includes($("#answerInterval").val())) {
			$("div.interval.logicalPartition .iconForCorrectAnswer").show();
		}
		else {
			$("div.interval.logicalPartition .iconForWrongAnswer").show();
			$("div.interval.logicalPartition .correctAnswer").text("Sorry! That was ".concat(output.intervalContainer.intervals[0]));
		}
	});

});