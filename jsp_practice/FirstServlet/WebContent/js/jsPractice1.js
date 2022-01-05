console.log("Start to Practice JS");

var word="bottles";
var count=99;

while(count>0){
	if(count==1){
		word="bottle";
		normalReport();
	}else{
		normalReport();
	}
	count=count-1;
}
lastReport();


function normalReport(){
	console.log(count+" "+word+" of beer on the wall");
	console.log("take one down, pass it around,");
}

function lastReport(){
	console.log("Nomore "+word+" beer on the wall, fxxcker");
}