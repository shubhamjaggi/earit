$(document).ready(function() {
	let output;
	let scaleTypes;

	$.get("/earit/options/scaleTypes", function(data) {
		scaleTypes = data;
		$("#answerScale").autocomplete({
			source: scaleTypes
		});
	});

	$("div.scale > button.play").click(function() {
		$.get("/earit/randomizer/scale", function(data) {
			output = data;
		});
	});

	$("div.scale > button.playAgain").click(function() {
		$.get("/earit/basic-player/scale", {
			rootNote: output.midiNote.shortName1,
			type: output.scaleType
		});
	});

	$("div.scale > button.check").click(function() {
		$(this).hide();
		$("div.scale.logicalPartition #answerScale").hide();
		$("div.scale.logicalPartition .playAgain").hide();
		if ($("#answerScale").val() === output.scaleType) {
			$("div.scale.logicalPartition .iconForCorrectAnswer").show();
		}
		else {
			$("div.scale.logicalPartition .iconForWrongAnswer").show();
			$("div.scale.logicalPartition .correctAnswer").text("Sorry! That was ".concat(output.scaleType));
		}
	});

});