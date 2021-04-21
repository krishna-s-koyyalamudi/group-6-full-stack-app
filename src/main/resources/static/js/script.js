src = "https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
integrity = "sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
crossorigin = "anonymous"


let colorElement1 = document.getElementById("refresh");
var questLocationName = document.getElementById("hideloc").innerHTML;
var questLocationlat = document.getElementById("hideloclat").innerHTML;
var questLocationlong = document.getElementById("hideloclong").innerHTML;

let incrementer = 1;

let distance = 0;
let currentlon = 0;
let currentlat = 0;
function main() {
	console.log('Page is fully loaded');
	console.log(questLocationName);
	console.log(currentlon, currentlat, questLocationlat, questLocationlong);
	if (incrementer == 1) {
		document.getElementById("readyy").innerHTML = "Click to start playing";
		let utterance = new SpeechSynthesisUtterance("Click to start playing");
		speechSynthesis.speak(utterance);
	}
	incrementer = 0;
	console.log(incrementer);
}

window.addEventListener('load', main);
colorElement1.addEventListener('click', getRead);
colorElement1.addEventListener('touch', getRead);
colorElement1.addEventListener('click', getReady);
colorElement1.addEventListener('touch', getReady);


async function getRead() {
	incrementer
	if (incrementer === 1) {
		window.location.reload()
	}
}


function getReady() {
	if (incrementer === 0) {
		document.getElementById("readyy").innerHTML = "The treasure location is ready..! ";
		document.getElementById("readyy").innerHTML = " Start playing the game.";
		let utterance = new SpeechSynthesisUtterance(`The treasure location has been set. start exploring !`);
		speechSynthesis.speak(utterance);
		incrementer = 1;
	}
}



async function getLocation() {
	return new Promise((resolve, reject) => {
		navigator.geolocation.getCurrentPosition(resolve, reject);
	}).then(position => {
		return position;
	});
}


let error = true;

async function inOut() {
	if (incrementer == 1) {
		const locText = await getLocation();

		currentlat = locText.coords.latitude;
		//document.getElementById("device-lat").innerHTML = ("Current Latitude: " + currentlat.toFixed(9));

		currentlon = locText.coords.longitude;
		//document.getElementById("device-long").innerHTML = ("Current Longitude: " + currentlon.toFixed(9));

		if (isInside() == true) {
			document.getElementById("result").innerHTML = "Congratulations! You have found the location " + questLocationName;
			document.getElementById("distance").innerHTML = "  ";
			let utterance = new SpeechSynthesisUtterance(`Congratulations!, You have found the location ${questLocationName}`);
			speechSynthesis.speak(utterance);
			// console.log(questLocationLat);
			error = false;
		};

		if (error) {
			document.getElementById("result").innerHTML = "Sorry,You're not near the treasure";
			document.getElementById("distance").innerHTML = "Distance to the location:  " + distance.toFixed(2) + " miles.";

			let utterance = new SpeechSynthesisUtterance("Sorry,You're not near the treasure. You need to go " + distance.toFixed(2) + " miles to reach the target !");
			speechSynthesis.speak(utterance);
		}
	}
}

function isInside(questLocationlat, questLocationlong) {
	var questLocationlat = document.getElementById("hideloclat").getAttribute("value");
	var questLocationlong = document.getElementById("hideloclong").getAttribute("value");
	distance = distanceBetweenLocations(currentlat, currentlon, questLocationlat, questLocationlong);
	console.log("distance: " + distance);

	if (distance < 0.05) {
		return true;
	} else {
		return false;
	}
}

function distanceBetweenLocations(currentlat, currentlon, questLocationlat, questLocationlong) {
	var p = 0.017453292519943295;
	var questLocationlat = document.getElementById("hideloclat").innerHTML;
	var questLocationlong = document.getElementById("hideloclong").innerHTML;
	var a = 0.5 - Math.cos((questLocationlat - currentlat) * p) / 2 +
		Math.cos(currentlat * p) * Math.cos(questLocationlat * p) *
		(1 - Math.cos((questLocationlong - currentlon) * p)) / 2;
	var result = 12742 * Math.asin(Math.sqrt(a));
	console.log(result)
	console.log(currentlon, currentlat, questLocationlat, questLocationlong);
	return result;

}