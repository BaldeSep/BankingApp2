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
        showInvalidUserNameAlert();
    }else if(!passwordsMatch){
        showPasswordMismatch();
    }else if(!validPassword){
        showInvalidPasswordAlert();
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
            if(res.status === 401){
                return res.text()
            }else if(res.status === 200 && res.redirected){
                window.location.href = res.url;
            }
            return "Registration Successful Successful";
        }).then( text => console.log(text) );
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