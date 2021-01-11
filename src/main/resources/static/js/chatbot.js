function myFunction(event) {
	
	if(event.id !== "bot-button-toggle") {
		return;
	}
  var x = document.getElementById("chatbox");  
  if (x.style.display === "block") {
    
	x.style.display = "none";
	document.querySelector('#bot-button-toggle').innerText = 'Show Bot';
	document.getElementById("bot-button-toggle").setAttribute('style', 'transform: rotate(-90deg);right:-39px;');
	
  } else {
	  
	x.style.display = "block";
	document.querySelector('#bot-button-toggle').innerText = 'Hide Bot';
	document.getElementById("bot-button-toggle").setAttribute('style', 'transform: rotate(-0deg);right:0');
	
  }
}