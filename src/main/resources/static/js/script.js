src = "https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
integrity = "sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
crossorigin = "anonymous"
/*
var questLocationName = document.getElementById("lname").getAttribute("value");
var questLocationLat = document.getElementById("llat").getAttribute("value");
var questLocationLong = document.getElementById("llong").getAttribute("value");*/
var questLocationName = document.getElementById("hideloc").innerHTML;
var questLocationlat = document.getElementById("hideloclat").innerHTML;
var questLocationlong = document.getElementById("hideloclong").innerHTML;

let element1 = document.getElementById("ready");

element1.addEventListener('click', getReady);
element1.addEventListener('touch', getReady);
window.addEventListener('load', main);
function main() {
    console.log('Page is fully loaded');
console.log("==========================>",questLocationName);
console.log("==========================>",questLocationlat);
console.log("==========================>",questLocationlong);}

async function getReady() {
    let confirmation = "A Location has been picked. Start Playing !";
	document.getElementById("read").innerHTML = confirmation
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



async function inOut() {
    if (incrementer == 1) {
        const locText = await getLocation();

        currentlat = locText.coords.latitude;
        // console.log("============>clat", currentlat)
        document.getElementById("device-lat").innerHTML = currentlat.toFixed(9);
        currentlon = locText.coords.longitude;
        // console.log(currentlon)
        document.getElementById("device-long").innerHTML = currentlon.toFixed(9);
        // console.log("===============before is inside");
        // console.log(questLocationLat);



        if (isInside() == true) {
            // console.log("==========> inside inside")
            document.getElementById("target").innerHTML = questLocationName;
            let utterance = new SpeechSynthesisUtterance(`Congratulations!, You found location ${questLocationName}`);
            speechSynthesis.speak(utterance);
            // console.log(questLocationLat);
            error = false;
        };

        if (error) {
            // console.log("error is here")
            document.getElementById("error").innerHTML = "Sorry,You're not near to the treasure";
            let utterance = new SpeechSynthesisUtterance("Sorry,You're not near to the treasure");
            speechSynthesis.speak(utterance);
        } else {
            document.getElementById("target1").innerHTML = "";
        }
        // console.log(incrementer, "===================>")

    } else {
        document.getElementById("target").innerHTML = "First click on Box 2";
    }

}

function isInside(questLocationLat, questLocationLong) {
    var questLocationLat = document.getElementById("llat").getAttribute("value");
    var questLocationLong = document.getElementById("llong").getAttribute("value");
    // console.log(questLocationLat);
    let distance = distanceBetweenLocations(currentlat, currentlon, questLocationLat, questLocationLong);
    // console.log("distance: " + distance);
    // console.log("quest lat " + questLocationLat);


    if (distance < 30) {
        return true;
    } else {
        return false;
    }
}

function distanceBetweenLocations(currentlat, currentlon, questLocatiionLat, questLocationLong) {
    var p = 0.017453292519943295;
    // console.log(currentlat, currentlon, questLocatiionLat, questLocationLong)
    var a = 0.5 - Math.cos((questLocatiionLat - currentlat) * p) / 2 +
        Math.cos(currentlat * p) * Math.cos(questLocatiionLat * p) *
        (1 - Math.cos((questLocationLong - currentlon) * p)) / 2;
    // console.log("================>");
    var result = 12742 * Math.asin(Math.sqrt(a));
    // console.log(result)

    return result;

}

