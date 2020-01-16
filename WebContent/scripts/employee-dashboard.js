// Dummy Data For Accounts
let accounts = {
    balde: [
        {
            accountNumber: 10,
            holder: "balde",
            balance: 40.00
        },
        {
            accountNumber: 15,
            holder: "balde",
            balance: 320.00
        },
        {
            accountNumber: 5,
            holder: "balde",
            balance: 4000.00
        }
    ],
    killian: [
        {
            accountNumber: 30,
            holder: "killian",
            balance: 2500.00
        }
    ],
    sam: [
        {
            accountNumber: 25,
            holder: "sam",
            balance: 3000.00
        },
        {
            accountNumber: 45,
            holder: "sam",
            balance: 100.00
        }
    ]
}


// Extract Accounts from accounts array
let individualAccounts = [];
Object.keys(accounts).forEach( user => {
    accounts[user].forEach( account => {
        individualAccounts.push(account);
    });
});


// Get Table For Displaying User Accounts
let accountTableWrapper = document.getElementById("display-accounts-table-wrapper"); 

// Get The Body Of the Display User Accounts table
let accountTableBody= document.getElementById("display-accounts-table-body");

// Get Form For Printing All Of The Accounts of A User
let formGetAllAccounts = document.getElementById("view-all-accounts-form");

// Get Input For The User Name To Get All Accounts For The Users
let inputGetUserName = document.getElementById("view-all-accounts");

// Get View Individual Account Input
let inputGetAnAccount = document.getElementById("view-account-input");

// Get The Form For Searching For A Single Account
let formGetAnAccount = document.getElementById("view-account-form");

// Add Event Listener For The Get All Accounts Form To Check If The User Name Exists
// Display All The Data If It Does
function handleGettingAccounts(){
    return populateAccountsTable();    
}

// Add Event Listener to the View Single Account Form.
// Search Through Accounts We Have To Find the Entered Account Number.
function handleGettingAccount(){
	return populateAccount();
}


function populateAccount(){
	let accountNumber = inputGetAnAccount.value;
	fetch(`http://localhost:5050/MaximusBank/bankaccount?accountNumber=${accountNumber}`)
		.then( res => res.json() )
		.then( data => {
			if(data.hasOwnProperty("message")){
				alert(data.message);
			}else{
				let output = `
					<tr>
						<td>${data.accountNumber}</td>
						<td>${data.holder}</td>
						<td>${data.balance}</td>
					</tr>
				`;
				let userName = data.holder;
				accountTableBody.innerHTML = output;
				document.getElementById("display-user-name").innerText = userName;
				if(accountTableWrapper.classList.contains("d-none")){
			        accountTableWrapper.classList.remove("d-none");
			    }
			}
		} );
	return false;
}


// Populate Table With Data If The User Is Found
function populateAccountsTable(){
	let userName = inputGetUserName.value;
	fetch(`http://localhost:5050/MaximusBank/bankaccounts?user_name=${userName}`)
		.then( res => res.json() )
		.then( data => {
			if(data.hasOwnProperty("message")){
				alert(data.message);
			}else{
				let output = ``;
				let userName = "";
				data.forEach( account => {
					output += `
			            <tr>
			                <td>${account.accountNumber}</td>
			                <td>${account.holder}</td>
			                <td>${account.balance}</td>
			            </tr>
			        `;
			        userName = account.holder;
				});
				accountTableBody.innerHTML = output
			    document.getElementById("display-user-name").innerText = userName;
			    if(accountTableWrapper.classList.contains("d-none")){
			        accountTableWrapper.classList.remove("d-none");
			    }
			}
		}).catch( err => console.log(err) );
	return false;
}