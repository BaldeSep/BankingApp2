let cachedAccounts = [];

// Get Table Accounts Body
let accountsTableBody = document.getElementById("table-accounts");

// Get Modal Account Display
let modalAccountDisplay = document.getElementById("account-number");

// Get Money Transfer Modal
let modalSelectSourceAccount = document.getElementById("post-transfer-source");

// Get Button To Show All Accounts
let btnShowAllAccounts = document.getElementById("show-all-accounts");

// Get Form To Search For Specific Account By Number
let formGetAccount = document.getElementById("search-accounts-form");

// Get Input From Get Account Form to search
let inputAccountNumber = document.getElementById("search-accounts");

// Get Input for making Deposit
let inputMakeDeposit = document.getElementById("transaction-deposit");

// Get Input for making withdrawal
let inputMakeWithdrawal = document.getElementById("transaction-withdrawal");

// Get Input For Amount To Transfer 
let inputAmountTrasnfer = document.getElementById("post-transfer-amount");

// Get Input for selected source account
let selectSourceAccount = document.getElementById("post-transfer-source");

// Get Input for destination account
let inputDestinationAccount = document.getElementById("post-transfer-destination");

// If the user clicks on an account then this will save the account number
// within memory
let selectedAccountNumber;

// Add Event Listener For Showing All Accounts Button
btnShowAllAccounts.addEventListener("click", loadAccounts);


// Add Event Listener to Accounts Table Body
accountsTableBody.addEventListener("click", e => {
    selectedAccountNumber = e.target.parentNode.id;
    modalAccountDisplay.innerText = selectedAccountNumber;
});

// Function For Getting Specific Account From Server
function getAccounts(){
	let accountNumber = inputAccountNumber.value;
	if(accountNumber < 0){
		showMessage("Account Number Must Be A Positive Number");
	}else{
		fetch("http://localhost:5050/MaximusBank/bankaccounts")
		.then( res => res.json() )
		.then( accounts => {
			if(accounts.hasOwnProperty("message")){
				showMessage(accounts.message);
			}else{
				let account = accounts.find( a => a.accountNumber == accountNumber );
				if(account){
					accountsTableBody.innerHTML = `
						<tr id=${account.accountNumber}>
						<td>${account.accountNumber}</td>
						<td>${account.balance}</td>
						</tr>
						`
				}   				
			}
		});		
	}
    return false;
}


// When the window loads this simulates a request to get all of the logged in user's 
// accounts and displays them within a table
window.onload = loadAccounts;
function loadAccounts(){
    fetch("http://localhost:5050/MaximusBank/bankaccounts")
        .then( res => res.json() )
        .then( accounts => {
        	if(accounts.hasOwnProperty("message")){
        		showMessage(accounts.message);
        	}else{
        		accounts.forEach( account => {
        			let tableOutput = ``;
        			let selectOutput = `<option selected>Source Account Number</option>`;
        			accounts.forEach( account => {
        				tableOutput += `
        					<tr id=${account.accountNumber}>
        					<td>${account.accountNumber}</td>
        					<td>$${account.balance}</td>
        					</tr>
        					`;
        				selectOutput += `
        					<option value="${account.accountNumber}">${account.accountNumber}</option>
        					`;
        			});
        			accountsTableBody.innerHTML = tableOutput;
        			modalSelectSourceAccount.innerHTML = selectOutput;
        		})        		
        	}
        })
}

// Apply For Bank Account
function applyForAccount(){
	let initialBalance = document.getElementById("initial-balance").value;
	if(initialBalance < 0){
		showMessageApply("Invalid Initial Balance Must Be Greater Than 0");
	}else{
		let application = {
				initialBalance
		}
		fetch("http://localhost:5050/MaximusBank/apply", {
			method: "POST",
			header: {
				'Content-Type': 'application/json',
				'Accept': 'application/json'
			},
			body: JSON.stringify(application)
		})
		.then( res => res.json() )
		.then( text => showMessageApply(text.message) );		
	}
    return false;
}

// Make A Deposit
function makeDeposit(){
	let amount = inputMakeDeposit.value;
	if(amount < 0){
		showMessageTransaction("Deposit Amount Must Be Greater Than 0");
	}else{
		inputMakeDeposit.value = "";
		fetch("http://localhost:5050/MaximusBank/deposit", {
			method: "PUT",
			header: {
				'Content-Type': "application/json",
				'Accept': 'application.json'
			},
			body: JSON.stringify({ amount, accountNumber: selectedAccountNumber })
		}).then( res => res.json() )
		.then( data => {
			showMessageTransaction(data.message);
			loadAccounts();
		} );		
	}
	return false;
}

//Make A Withdrawal
function makeWithdrawal(){
	let amount = inputMakeWithdrawal.value;
	if(amount < 0){
		showMessageTransaction("Withdrawals Amount Must Be Greater Than 0");
	}else{
		inputMakeWithdrawal.value = "";
		let withdrawal = { amount, accountNumber: selectedAccountNumber };
		fetch("http://localhost:5050/MaximusBank/withdrawal", {
			method: "PUT",
			header: {
				'Content-Type': "application/json",
				'Accept': 'application.json'
			},
			body: JSON.stringify(withdrawal)
		}).then( res => res.json())
		.then( data => {
			showMessageTransaction(data.message);
			loadAccounts();
		})		
	}
	return false;
}

// Post Money Transfer
function postMoneyTransfer(){
	let amount = +inputAmountTrasnfer.value; 
	let destinationAccount = +inputDestinationAccount.value;
	let selectedSource = +selectSourceAccount.value;
	if(isNaN(selectedSource)){
		showMessageTransfer("Select a source account");
	}else if(amount < 0){
		showMessageTransfer("Amount must be greater than 0");
	}else if(destinationAccount < 0){
		showMessageTransfer("Detination account number must be greater than 0");
	}else{
		let transfer = {
				sourceAccount: selectedSource,
				destinationAccount,
				amount
		}
		console.log(transfer);
		fetch("http://localhost:5050/MaximusBank/transfers", {
			method: "POST",
			header: {
				'Content-Type': 'application/json',
				'Accept': 'applcation/json'
			},
			body: JSON.stringify(transfer)
		}).then( res => res.json() )
		.then( data => {
			showMessageTransfer(data.message);
		} )
		
	}
	
	return false;
}



//Get Message span for alerts on dashboard
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

//Get Message span for applications alerts
let alertApply = document.getElementById("alert-apply");
let messageSpanApply = document.getElementById("message-apply");
let closeAlertButtonApply = document.getElementById("alert-close-apply");

closeAlertButtonApply.addEventListener( "click", e => {
	alertApply.classList.remove("show");
});

function showMessageApply(message){
	if(!alertApply.classList.contains("show")){
		alertApply.classList.add("show");
	}
	messageSpanApply.innerText = message;
}

//Get Message span for transactions alerts
let alertTransaction = document.getElementById("alert-transactions");
let messageSpanTransaction= document.getElementById("message-transactions");
let closeAlertButtonTransaction= document.getElementById("alert-close-transactions");

closeAlertButtonTransaction.addEventListener( "click", e => {
	alertTransaction.classList.remove("show");
});

function showMessageTransaction(message){
	if(!alertTransaction.classList.contains("show")){
		alertTransaction.classList.add("show");
	}
	messageSpanTransaction.innerText = message;
}


//Get Message span for transfers alerts
let alertTransfer= document.getElementById("alert-transfers");
let messageSpanTransfer= document.getElementById("message-transfers");
let closeAlertButtonTransfer= document.getElementById("alert-close-transfers");

closeAlertButtonTransfer.addEventListener( "click", e => {
	alertTransfer.classList.remove("show");
});

function showMessageTransfer(message){
	if(!alertTransfer.classList.contains("show")){
		alertTransfer.classList.add("show");
	}
	messageSpanTransfer.innerText = message;
}





