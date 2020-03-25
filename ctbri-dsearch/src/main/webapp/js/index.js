$(function() {

	$("#search").bind("click", function() {
		var word = $("#word").val();
		if (word != null && word != "") {
			window.location.href = "/dsearch/forward/search?word=" + word;
		}
	});
	
});