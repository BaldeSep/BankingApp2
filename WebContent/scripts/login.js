// Grab Form
let formLogin = document.getElementById("login-form");

// Get Form Inputs
let inputUserName = document.getElementById("user-name");
let inputPassword = document.getElementById("password");

// Get Alerts For Invalid User Credentials
let alertUserName = document.getElementById("alert-invalid-username");
let alertPassword = document.getElementById("alert-invalid-password");



function handleSubmit(){
	let userName = inputUserName.value.trim();
    let password = inputPassword.value.trim();

    let validUserName = verify(userName, 5);
    let validPassword =  verify(password, 3);
    if(!validUserName){
        showInvalidUserName();
    }else if(!validPassword){
        showInvalidPassword();
    }else{
        let endPoint = formLogin.dataset.end;
	    fetch(`http://localhost:5050/MaximusBank/${endPoint}`, {
	    	method: "POST",
            redirect: 'follow',
	    	headers: {
	    		'Accept': ['text/plain', 'text/html'],
	    		'Content-Type': 'application/json'
            },
	    	body: JSON.stringify({ user_name: userName, password:password })
        }).then( res => {
            if(res.status == 401){
                return res.text();
            }else if(res.redirected){
                window.location.href = res.url;
            }
        }).then( text => {
            console.log(text);
        } )
    }
    return false;
}


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
