const interval = 1000;



function get(message) {
    $.ajax({
        //url: "http://spbau-icloude.appspot.com/rest/api/info",
        url: "http://localhost:8888/rest/api/info",
        type: "GET",
        dataType: "json",
        success: function(data) {alert("good!" + "\n" + data.text);},
        error: function(a) {alert("bad!");},
    });
}

function post(message) {
    //alert(message);
	$.ajax({
        //url: "http://spbau-icloude.appspot.com/rest/api/info",
        url: "http://localhost:8888/rest/api/info",
        type: "POST",
        dataType: "json",
        data: {data: message},
        success: function(data) {alert("good post!" + "\n" + data.text);},
        error: function(a) {alert("bad post!");},
    });
}


function repeat() {
    setInterval(get, interval);
}



$(document).ready(function() {
    $('#getBtn').click(get);
    $('#postBtn').click(function() {
    	post('hey yo');
    });
    $('#repeatBtn').click(repeat);
    $('#sendBtn').click(function() {
    	alert($('#codeArea').val());
    	post($('#codeArea').val()); 
    });
});
