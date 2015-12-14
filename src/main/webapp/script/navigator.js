function h() {
	post("logout", undefined);
}

function post(servlet, data){
	var xhttp = new XMLHttpRequest();
	xhttp.open("POST", servlet, true);
	xhttp.send(data);
}

