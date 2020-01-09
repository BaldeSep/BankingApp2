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


// Add Event Listener For Form Registration
formCustomerRegsitration.addEventListener("submit", (e) => {
    let userName = inputTextUserName.value.trim();
    let passwordOne = inputPasswordOne.value;
    let passwordTwo = inputPasswordTwo.value;
    
    if(!validateInput(userName, 5)){
        showInvalidUserNameAlert();
        e.preventDefault();
    }
    let matchingPasswords = passwordsMatch(passwordOne, passwordTwo);
    if(!validateInput(passwordOne, 3)){
        showInvalidPasswordAlert();
        e.preventDefault();
    }

    if(!matchingPasswords){
        showPasswordMismatch();
        e.preventDefault();
    }
});

// Check If Passwords Match
function passwordsMatch(passwordA, passwordB){
    return passwordA === passwordB;
}


// Check if Passwords And User Names Are Valid.
// Min Length Of String.
// String cannot have white space.
function validateInput(input, minLength){
    let pattern = new RegExp(`^[a-zA-Z0-9]{${minLength},}$`, "g");
    return input.match(pattern);
}

// Show Alerts For Invalid user names
function showInvalidUserNameAlert(){
    if(alertInvalidUserName.classList.contains("d-none")){
        alertInvalidUserName.classList.remove("d-none");
        setTimeout( () => {
            alertInvalidUserName.classList.add("d-none");
        }, 7000 );
    }
}

// Show Alerts For Invalid user names
function showInvalidPasswordAlert(){
    if(alertInvalidPassword.classList.contains("d-none")){
        alertInvalidPassword.classList.remove("d-none");
        setTimeout( () => {
            alertInvalidPassword.classList.add("d-none");
        }, 7000 );
    }
}

// Show Password Mismatch
function showPasswordMismatch(){
    if(alertPasswordMismatch.classList.contains("d-none")){
        alertPasswordMismatch.classList.remove("d-none");
        setTimeout( () => {
            alertPasswordMismatch.classList.add("d-none");
        }, 7000 );
    }
}