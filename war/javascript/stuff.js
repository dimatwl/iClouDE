function handler(data) {
	alert(data.text);
	$('#status').text(data.text);
}


function repeat() {
    setInterval(get, interval);
}
