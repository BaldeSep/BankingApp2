// Dummy Data To Simulate Get request
let accounts = [
    {
        accountNumber: 50,
        balance: 300
    },
    {
        accountNumber: 51,
        balance: 400
    },
    {
        accountNumber: 52,
        balance: 500
    },
    {
        accountNumber: 53,
        balance: 600
    },
    {
        accountNumber: 54,
        balance: 300
    },
    {
        accountNumber: 100,
        balance: 450.98
    }
];

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

// Add Event Listener to search for account by number
formGetAccount.addEventListener("submit", e => {
    e.preventDefault();
    let accountNumber = Number(inputAccountNumber.value);
    let account = accounts.find( a => a.accountNumber === accountNumber );
    if(account){
        accountsTableBody.innerHTML = `
            <tr id=${account.accountNumber}>
                <td>${account.accountNumber}<td>
                <td>${account.balance}<td>
            </tr>
        `;
    }else{
        console.log("Could Not Find Accout");
    }
});


// When the window loads this simulates a request to get all of the logged in user's 
// accounts and displays them within a table
window.onload = loadAccounts;
function loadAccounts(){
    if(accounts.length > 0){
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
    }
}
