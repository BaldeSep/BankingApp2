// Grab Form
let formCustomer = document.getElementById("login-form");

// Get Form Inputs
let inputUserName = document.getElementById("user-name");
let inputPassword = document.getElementById("password");

// Get Alerts For Invalid User Credentials
let alertUserName = document.getElementById("alert-invalid-username");
let alertPassword = document.getElementById("alert-invalid-password");

// Add Event Listener To Form
formCustomer.addEventListener("submit", (e) => {
    let userName = inputUserName.value.trim();
    let password = inputPassword.value.trim();
    if(!verify(userName, 5)){
        showInvalidUserName();
        e.preventDefault();
    }
    if(!verify(password, 3)){
        showInvalidPassword();
        e.preventDefault();
    }
});


// Verify User Name and Password
function verify(input, minLength){
    let pattern = new RegExp(`^[A-Za-z0-9]{${minLength},}$`, "g");
    return input.match(pattern);
}


// Show Invalid User Name Alert
function showInvalidUserName(){
    if(alertUserName.classList.contains("d-none")){
        alertUserName.classList.remove("d-none");
        setTimeout( () => {
            alertUserName.classList.add("d-none");
        },3000 );
    }
}
// Show Invalid Password Alert
function showInvalidPassword(){
    if(alertPassword.classList.contains("d-none")){
        alertPassword.classList.remove("d-none");
        setTimeout( () => {
            alertPassword.classList.add("d-none");
        },3000 );
    }
}
