function h() {
	post("logout", undefined);
	window.location="movies.jsp";
}

function post(servlet, data){
	var xhttp = new XMLHttpRequest();
	xhttp.open("POST", servlet, true);
	xhttp.send(data);
}

