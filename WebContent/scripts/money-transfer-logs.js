// Fake Money Transfers
let trans = [
	{
		transactionId: 1,
		sourceAccount: 51 ,
		sourceHolder: "balde",
		destinationAccount: 30,
		destinationHolder: "killian",
		amount: 30,
		date: "2020-1-16",
	},
	{
		transactionId: 2,
		sourceAccount: 23 ,
		sourceHolder: "sam",
		destinationAccount: 30,
		destinationHolder: "killian",
		amount: 348,
		date: "2020-1-10",
	},
	{
		transactionId: 3,
		sourceAccount: 45,
		sourceHolder: "kim",
		destinationAccount: 50,
		destinationHolder: "jim",
		amount: 901,
		date: "2019-10-5",
	},
	{
		transactionId: 4,
		sourceAccount: 10 ,
		sourceHolder: "balde",
		destinationAccount: 90,
		destinationHolder: "jimbo",
		amount: 234,
		date: "2018-2-14",
	},
	{
		transactionId: 5,
		sourceAccount: 290 ,
		sourceHolder: "hicy",
		destinationAccount: 87,
		destinationHolder: "killian",
		amount: 73.00,
		date: "2020-1-1",
	},
];

// Get Table Body for transactions table
let tableTransactions = document.getElementById("table-transfers-body");


window.onload = loadLogs;

function loadLogs(){
	fetch("http://localhost:5050/MaximusBank/transferlogs")
		.then( res => res.json() )
		.then( data => {
			if(data.hasOwnProperty("message")){
				alert(message);
			}else{
				let output = ``;
				data.forEach( log => {
					output += `
						<tr>
							<td>${log.transactionId}</td>
							<td>${log.sourceAccount}</td>
							<td>${log.sourceHolder}</td>
							<td>${log.destinationAccount}</td>
							<td>${log.destinationHolder}</td>
							<td>${log.amount}</td>
							<td>${log.dateOfPost}</td>
						</tr>
					`;
				});
				tableTransactions.innerHTML = output;
			}
		} )
}























