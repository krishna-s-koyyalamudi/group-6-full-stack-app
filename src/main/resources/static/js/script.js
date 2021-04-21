src = "https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
integrity = "sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
crossorigin = "anonymous"

/*let colorElement = document.getElementById("bgrtwo");*/

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
		//document.getElementById("ready1").innerHTML = " ";
		let utterance = new SpeechSynthesisUtterance("Click to start playing");
		speechSynthesis.speak(utterance);
		//document.getElementById("lname").innerHTML = "        ";
	}
	incrementer = 0;
	console.log(incrementer);
}

window.addEventListener('load', main);
/*colorElement.addEventListener('click', onClickSquareBox2);
colorElement.addEventListener('touch', onClickSquareBox2);
colorElement1.addEventListener('click', onClickSquareBox1);
colorElement1.addEventListener('touch', onClickSquareBox1);

colorElement1.addEventListener('click', display);
colorElement1.addEventListener('touch', display);*/
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
		//console.log("============================", incrementer)
		let utterance = new SpeechSynthesisUtterance(`The treasure location has been set. start exploring !`);
		speechSynthesis.speak(utterance);
		//document.getElementById("hint").innerHTML = ("Hint: " + questLocationHint);
		//let utterance1 = new SpeechSynthesisUtterance(`Hint:   ${questLocationHint}`);
		//speechSynthesis.speak(utterance1);
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
			// console.log("==========> inside inside")
			document.getElementById("result").innerHTML = "Congratulations!, You have found the location ${questLocationName}",questLocationName;
			document.getElementById("distance").innerHTML = "  ";
			let utterance = new SpeechSynthesisUtterance(`Congratulations!, You have found the location ${questLocationName}`);
			speechSynthesis.speak(utterance);
			// console.log(questLocationLat);
			error = false;
		};

		if (error) {
			// console.log("error is here")
			document.getElementById("result").innerHTML = "Sorry,You're not near to the treasure";
			document.getElementById("distance").innerHTML = "Distance to the location:  " + distance.toFixed(2) + " miles.";

			let utterance = new SpeechSynthesisUtterance("Sorry,You're not near to the treasure. You need to go "+distance.toFixed(2)+" miles to reach the target !");
			speechSynthesis.speak(utterance);
		}
		// console.log(incrementer, "===================>")
	}


}

function isInside(questLocationlat, questLocationlong) {
	var questLocationlat = document.getElementById("hideloclat").getAttribute("value");
	var questLocationlong = document.getElementById("hideloclong").getAttribute("value");
	// console.log(questLocationLat);
	//console.log(currentlon,currentlat);
	distance = distanceBetweenLocations(currentlat, currentlon, questLocationlat, questLocationlong);
	console.log("distance: " + distance);
	// console.log("quest lat " + questLocationLat);


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
	// console.log(currentlat, currentlon, questLocatiionLat, questLocationLong)
	var a = 0.5 - Math.cos((questLocationlat - currentlat) * p) / 2 +
		Math.cos(currentlat * p) * Math.cos(questLocationlat * p) *
		(1 - Math.cos((questLocationlong - currentlon) * p)) / 2;
	// console.log("================>");
	var result = 12742 * Math.asin(Math.sqrt(a));
	console.log(result)
	console.log(currentlon, currentlat, questLocationlat, questLocationlong);
	return result;

}