let accounts = [];

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
    fetch("http://localhost:5050/MaximusBank/bankaccounts")
    .then( res => res.json() )
    .then( accounts => {
            let accountNumber = inputAccountNumber.value;
            let account = accounts.find( a => a.accountNumber == accountNumber );
            if(account){
                accountsTableBody.innerHTML = `
                    <tr id=${account.accountNumber}>
                        <td>${account.accountNumber}<td>
                        <td>${account.balance}<td>
                    </tr>
                `
            }else{
                alert("Could Not Find Account");
            }        
    });
    return false;
}


// When the window loads this simulates a request to get all of the logged in user's 
// accounts and displays them within a table
window.onload = loadAccounts;
function loadAccounts(){
    fetch("http://localhost:5050/MaximusBank/bankaccounts")
        .then( res => res.json() )
        .then( accounts => {
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
            }  )
        } )
}

// Apply For Bank Account
function applyForAccount(){
    let application = {
        initialBalance: document.getElementById("initial-balance").value,
    }
    fetch("http://localhost:5050/MaximusBank/apply", {
        method: "POST",
        header: {
            'Content-Type': 'application/json',
            'Accept': 'text/plain'
        },
        body: JSON.stringify(application)
    })
        .then( res => res.text() )
        .then( text => alert(text) );
    return false;
}
