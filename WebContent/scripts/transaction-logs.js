// Fake Transactions
let trans = [
	{
		transactionId: 1,
		accountNumber: 81,
		holder: "balde",
		amount: 30,
		date: "2020-1-16",
		type: "Deposit"
	},
	{
		transactionId: 2,
		accountNumber: 51,
		holder: "killian",
		amount: 24.5,
		date: "2020-1-10",
		type: "Withdrawal"
	},
	{
		transactionId: 3,
		accountNumber: 100,
		holder: "kim",
		amount: 45.32,
		date: "2019-10-5",
		type: "Deposit"
	},
	{
		transactionId: 4,
		accountNumber: 21,
		holder: "jim",
		amount: 12,
		date: "2018-2-14",
		type: "Withdrawal"
	},
	{
		transactionId: 5,
		accountNumber: 45,
		holder: "killian",
		amount: 45.32,
		date: "2020-1-1",
		type: "Deposit"
	},
];

// Get Table Body for transactions table
let tableTransactions = document.getElementById("table-transactions-body");


window.onload = loadLogs;

function loadLogs(){
	fetch("http://localhost:5050/MaximusBank/transactions")
		.then( res => res.json() )
		.then( data => {
			if(data.hasOwnProperty("message")){
				alert(data.message);
			}else{
				let output = ``;
				data.forEach( log => {
					output += `
						<tr>
							<td>${log.transactionId}</td>
							<td>${log.accountNumber}</td>
							<td>${log.accountHolder}</td>
							<td>${log.amount}</td>
							<td>${log.date}</td>
							<td>${log.transactionType == 0 ? "Deposit" : "Withdrawal"}</td>
						</tr>
					`;
				});
				tableTransactions.innerHTML = output;
			}
		})
}























