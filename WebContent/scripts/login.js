// Grab Form
let formCustomer = document.getElementById("login-form");

// Get Form Inputs
let inputUserName = document.getElementById("user-name");
let inputPassword = document.getElementById("password");

// Add Event Listener To Form
formCustomer.addEventListener("submit", (e) => {
    let userName = inputUserName.value.trim();
    let password = inputPassword.value.trim();
    // Post this to the server to verify user credientials
});


// Verify User Name and Password
function verify(input, minLength){
    let pattern = new RegExp(`^[A-Za-z0-9]{${minLength},}$`, "g");
    return input.match(pattern);
}
