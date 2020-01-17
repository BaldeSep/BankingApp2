// Grab Form
let formLogin = document.getElementById("login-form");

// Get Form Inputs
let inputUserName = document.getElementById("user-name");
let inputPassword = document.getElementById("password");



function handleSubmit(){
	let userName = inputUserName.value.trim();
    let password = inputPassword.value.trim();

    let validUserName = verify(userName, 5);
    let validPassword =  verify(password, 3);
    if(!validUserName){
        showMessage("Invalid User Name");
    }else if(!validPassword){
    	showMessage("Invalid Password");
    }else{
        let endPoint = formLogin.dataset.end;
	    fetch(`http://localhost:5050/MaximusBank/${endPoint}`, {
	    	method: "POST",
            redirect: 'follow',
	    	headers: {
	    		'Accept': 'application/json',
	    		'Content-Type': 'application/json'
            },
	    	body: JSON.stringify({ user_name: userName, password:password })
        }).then( res => {
            if(res.status == 401){
                return res.json();
            }else if(res.redirected){
                window.location.href = res.url;
            }
        }).then( message => {
            showMessage(message.message);
        } )
    }
    return false;
}



// Verify User Name and Password
function verify(input, minLength){
    let pattern = new RegExp(`^[A-Za-z0-9]{${minLength},}$`, "g");
    return input.match(pattern);
}



// Get Message span for alerts
let alert = document.getElementById("alert");
let messageSpan = document.getElementById("message");
let closeAlertButton = document.getElementById("alert-close");

closeAlertButton.addEventListener( "click", e => {
	alert.classList.remove("show");
});

function showMessage(message){
	if(!alert.classList.contains("show")){
		alert.classList.add("show");
	}
	messageSpan.innerText = message;
}
