function myFunction(event) {
	
	if(event.id !== "bot-button-toggle") {
		return;
	}
  var x = document.getElementById("chatbox");  
  if (x.style.display === "block") {
    
	x.style.display = "none";
	document.querySelector('#bot-button-toggle').innerText = 'Show bot';
	document.getElementById("bot-button-toggle").setAttribute('style', 'right:0px;');
	
  } else {
	  
	x.style.display = "block";
	document.querySelector('#bot-button-toggle').innerText = 'Hide bot';
	document.getElementById("bot-button-toggle").setAttribute('style', 'bottom:50%');
	
  }
}