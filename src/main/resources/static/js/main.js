$(document).ready(function() {

	$("nav>button.note").click(function() {
		$("div.logicalPartition").hide();
		$("div.note.logicalPartition").show();
		$("div.note.logicalPartition .hiddenByDefault").hide();
		$("div.logicalPartition>button.play").text("Play");
		$("div.note.logicalPartition .correctAnswer").text("");
	});


	$("nav>button.chord").click(function() {
		$("div.logicalPartition").hide();
		$("div.chord.logicalPartition").show();
		$("div.chord.logicalPartition .hiddenByDefault").hide();
		$("div.logicalPartition>button.play").text("Play");
		$("div.chord.logicalPartition .correctAnswer").text("");
	});


	$("nav>button.scale").click(function() {
		$("div.logicalPartition").hide();
		$("div.scale.logicalPartition").show();
		$("div.scale.logicalPartition .hiddenByDefault").hide();
		$("div.logicalPartition>button.play").text("Play");
		$("div.scale.logicalPartition .correctAnswer").text("");
	});


	$("nav>button.interval").click(function() {
		$("div.logicalPartition").hide();
		$("div.interval.logicalPartition").show();
		$("div.interval.logicalPartition .hiddenByDefault").hide();
		$("div.logicalPartition>button.play").text("Play");
		$("div.interval.logicalPartition .correctAnswer").text("");
	});

	$("nav>button").click(function() {
		$("nav>button").removeClass("active");
		$(this).addClass("active");
	});


	$("div.note.logicalPartition>button.play").click(function() {
		$("div.note.logicalPartition .correctAnswer").text("");
		$("div.note.logicalPartition .hiddenByDefault").show();
		$("div.note.logicalPartition input").val("");
		$("div.note.logicalPartition .iconForCorrectAnswer").hide();
		$("div.note.logicalPartition .iconForWrongAnswer").hide();
		$(this).text("Next");
	});


	$("div.chord.logicalPartition>button.play").click(function() {
		$("div.chord.logicalPartition .correctAnswer").text("");
		$("div.chord.logicalPartition .hiddenByDefault").show();
		$("div.chord.logicalPartition input").val("");
		$("div.chord.logicalPartition .iconForCorrectAnswer").hide();
		$("div.chord.logicalPartition .iconForWrongAnswer").hide();
		$(this).text("Next");
	});


	$("div.scale.logicalPartition>button.play").click(function() {
		$("div.scale.logicalPartition .correctAnswer").text("");
		$("div.scale.logicalPartition .hiddenByDefault").show();
		$("div.scale.logicalPartition input").val("");
		$("div.scale.logicalPartition .iconForCorrectAnswer").hide();
		$("div.scale.logicalPartition .iconForWrongAnswer").hide();
		$(this).text("Next");
	});


	$("div.interval.logicalPartition>button.play").click(function() {
		$("div.interval.logicalPartition .correctAnswer").text("");
		$("div.interval.logicalPartition .hiddenByDefault").show();
		$("div.interval.logicalPartition input").val("");
		$("div.interval.logicalPartition .iconForCorrectAnswer").hide();
		$("div.interval.logicalPartition .iconForWrongAnswer").hide();
		$(this).text("Next");
	});

});