// Dummy Data To Represent Money Transfers
let transfers = [
    {
        id: 1,
        srcAccount: 10,
        destAccount: 20,
        amount: 30.00,
        date: "12/30/2019",
        status: "Pending"
    },
    {
        id: 2,
        srcAccount: 20,
        destAccount: 23,
        amount: 300.00,
        date: "12/31/2019",
        status: "Accepted"
    },
    {
        id: 3,
        srcAccount: 5,
        destAccount: 39,
        amount: 27.00,
        date: "12/30/2019",
        status: "Rejected"
    },
    {
        id: 4,
        srcAccount: 7,
        destAccount: 13,
        amount: 123.00,
        date: "11/30/2019",
        status: "Accepted"
    },
    {
        id: 5,
        srcAccount: 67,
        destAccount: 20,
        amount: 30.00,
        date: "12/30/2019",
        status: "Pending"
    }
];


// Get The Button For Processing The Transfers
let btnProcessTransfers = document.getElementById("btn-process-transfers");


// Get Table Body For Displaying Money Transfers
let tableBodyTransfers = document.getElementById("table-body-transfers");

// Add Event Listener For Table Body listening for the click of the radio buttons
tableBodyTransfers.addEventListener("click", e => {
    if(e.target.type == "radio" && e.target.dataset.action == "accept" ){
        e.target.parentNode.parentNode.dataset.state = "accepted";
    }else if(e.target.type == "radio" && e.target.dataset.action == "reject"){
        e.target.parentNode.parentNode.dataset.state = "rejected";
    }
});

//Add Event Listener to process transfer button
btnProcessTransfers.addEventListener("click", e => {
    let acceptedTransfers = tableBodyTransfers.querySelectorAll("[data-state=accepted]");
    let rejectedTransfers = tableBodyTransfers.querySelectorAll("[data-state=rejected]");
    let transfers = [];
    acceptedTransfers.forEach( transfer => {
    	let transferId = transfer.dataset.id;
    	transfers.push({ transferId, status: Status.Accepted });
    } );
    rejectedTransfers.forEach( transfer => {
    	let transferId = transfer.dataset.id;
    	transfers.push({ transferId, status: Status.Rejected });
    } );
    if(transfers.length > 0){
    	transfers.forEach( transfer => {
    		fetch("http://localhost:5050/MaximusBank/transfers", {
    			method: "PUT",
    			header: {
    				"Content-Type": "application/json",
    				"Accept" : "application/json"
    			},
    			body: JSON.stringify(transfer)
    		}).then( res => res.json() )
    			.then( data => {
    				showMessage(data.message)
    			});    		
    	});
    	loadTransfers();
    }else{
    	showMessage("Please Make A Selection!!!");
    }
});

// When Page Loads Display All Of the Money Transfers Related to the User
window.onload = loadTransfers;
	
function loadTransfers(){
    let output = ``;
    fetch("http://localhost:5050/MaximusBank/transfers")
    	.then( res => res.json() )
    	.then( data => {
    		if(data.hasOwnProperty("message")){
    			showMessage(data.message);
    		}else{
    			data.forEach( transfer => {
    				let status;
    				for(let prop in Status){
    					if(transfer.status == Status[prop]){
    						status = prop;
    					}
    				}
					 output += `
				            <tr data-state="pending" data-id="${transfer.transferId}">
				                <td>${transfer.transferId}</td>
				                <td>${transfer.sourceAccount}</td>
				                <td>${transfer.destinationAccount}</td>
				                <td>${transfer.amount}</td>
				                <td>${ status  }</td>
				                <td><input  type="radio" data-action="accept" name="accept-reject-transfer-${transfer.transferId}" ${ transfer.status !== Status.Pending ? 'disabled' : '' } ></td>
				                <td><input  type="radio" data-action="reject" name="accept-reject-transfer-${transfer.transferId}" ${ transfer.status !== Status.Pending ? 'disabled' : '' }></td>
				            </tr>
				        `;
					 tableBodyTransfers.innerHTML = output;
    			})
    		}
    	});
    return false;
}

const Status = Object.freeze({"Pending": 1, "Accepted": 2, "Rejected": 0});


//Get Message span for alerts
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
