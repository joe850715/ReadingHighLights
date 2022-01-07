var guess;
var hits=0;
var guesses=0;
var isSunk=false;
var location1;
var location2;
var location3;
//MAIN--------------------------------------------------

setLocation();
while(isSunk==false){
	guess=prompt("Aim and fire(choose number between 0-6):")
	var parsed =isNumCheck(guess);
	if(isNaN(parsed)){
		alert("the value is illegal !");
	}else{
		if(isInRange(parsed)){
			alert("value out of range !"); 
		}else{
			guesses=guesses+1;
			isHit(parsed);
			ifSunk(hits);
		}
	}	
}
alert("U Sank My Ship, Fxxcker! Guess Time: "+guesses);


//FUNCTION--------------------------------------------------

function setLocation(){
	var root = Math.floor(Math.random()*5);
	location1=root;
	location2=root+1;
	location3=root+2;
	console.log("locations= "+root+","+(root+1)+","+(root+2));
}

function isNumCheck(input){
	console.log("parsed= "+parseInt(input,10));
	return parseInt(input,10);
}

function isInRange(input){
	if(input<0||input>6){
		return true;
	}else{
		return false;
	}
}

function isHit(input){
	if(input==location1||input==location2||input==location3){
		hits=hits+1
	}
	console.log("hits= "+hits);
}

function ifSunk(input){
	if(input>=3){
		isSunk=true;
	}
}