src = "https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
integrity = "sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
crossorigin = "anonymous"

/*var questLocationName = document.getElementById("lname").getAttribute("value");
var questLocationLat = document.getElementById("llat").getAttribute("value");
var questLocationLong = document.getElementById("llong").getAttribute("value");*/

let element1 = document.getElementById("ready");

element1.addEventListener('click', getReady);
element1.addEventListener('touch', getReady);

async function getReady() {
    let confirmation = "A Location has been picked. Start Playing !";
    document.getElementById("hideloc").innerHTML = confirmation;
    let utterance = new SpeechSynthesisUtterance(confirmation);
    speechSynthesis.speak(utterance);
}

var x = document.getElementById("demo");
function
	getLocation() {
	if
		(navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition);
	} else {
		x.innerHTML = "Geolocation is not supported by this browser."
			;
	}
}
function
	showPosition(position) {
	x.innerHTML = "Latitude: " + position.coords.latitude +
		"<br>Longitude: " + position.coords.longitude;
}

