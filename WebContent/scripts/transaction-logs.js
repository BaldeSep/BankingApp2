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
	let output = ``;
	// Replace this array with a feth call to the database
	trans.forEach( log => {
		output += `
			<tr>
				<td>${log.transactionId}</td>
				<td>${log.accountNumber}</td>
				<td>${log.holder}</td>
				<td>${log.amount}</td>
				<td>${log.date}</td>
				<td>${log.type}</td>
			</tr>
		`;
	})
	tableTransactions.innerHTML = output;
}























