// Get Form For Registration
let formCustomerRegsitration = document.getElementById("customer-registration-form");

// Get Inputs For User Name and Passwords
let inputTextUserName = document.getElementById("user-name-registration");
let inputPasswordOne = document.getElementById("password-one-registration");
let inputPasswordTwo = document.getElementById("password-two-registration");

// Add Event Listener For Form Registration
formCustomerRegsitration.addEventListener("submit", (e) => {
    e.preventDefault();// for testing remove for after adding backend support
    let userName = inputTextUserName.value.trim();
    let passwordOne = inputPasswordOne.value;
    let passwordTwo = inputPasswordTwo.value;
    
    if(validateInput(userName, 6)){
        console.log("user name is valid");
    }else{
        console.log("user name is invalid");
    }
    let matchingPasswords = passwordsMatch(passwordOne, passwordTwo);
    if(matchingPasswords && validateInput(passwordOne, 5)){
        console.log("password is valid.");
    }else{
        console.log("password is invalid.");
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