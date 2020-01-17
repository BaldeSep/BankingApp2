// Get Form For Registration
let formCustomerRegsitration = document.getElementById("customer-registration-form");

// Get Inputs For User Name and Passwords
let inputTextUserName = document.getElementById("user-name-registration");
let inputPasswordOne = document.getElementById("password-one-registration");
let inputPasswordTwo = document.getElementById("password-two-registration");

// Get Alert For User Cerdential Verification
let alertInvalidUserName = document.getElementById("alert-invalid-username");
let alertInvalidPassword = document.getElementById("alert-invalid-password");
let alertPasswordMismatch = document.getElementById("alert-password-mismatch");


// Handle Submit Event for Registration Form
function handleSubmit(){
    let userName = inputTextUserName.value.trim();
    let passwordOne = inputPasswordOne.value;
    let passwordTwo = inputPasswordTwo.value;
    
    let validUserName = validateInput(userName, 5);
    let passwordsMatch = ( passwordOne === passwordTwo );
    let validPassword = validateInput(passwordOne, 3);

    if(!validUserName){
    	showMessage("User Name Is Invalid");
    }else if(!passwordsMatch){
        showMessage("Passwords Do Not Match");
    }else if(!validPassword){
        showMessage("Password Is Invalid");
    }else{
        let formBody = {
            userName,
            passwordOne,
            passwordTwo
        }
        fetch("http://localhost:5050/MaximusBank/register", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Accept': ['text/plain', 'text/html'],
            },
            body: JSON.stringify(formBody),
        }).then( res => {
            if(res.status === 200 && res.redirected){
                window.location.href = res.url;
            }else{
            	return res.json();
            }

        }).then( data => showMessage(data.message) );
    }

    return false;

}


// Check if Passwords And User Names Are Valid.
// Min Length Of String.
// String cannot have white space.
function validateInput(input, minLength){
    let pattern = new RegExp(`^[a-zA-Z0-9]{${minLength},}$`, "g");
    return input.match(pattern);
}


//Get Message span for alerts
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