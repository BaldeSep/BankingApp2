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
    acceptedTransfers.forEach( transfer => {
        console.log("Processed Transfer: " + transfer.dataset.id);
    } );
    rejectedTransfers.forEach( transfer => {
        console.log("Processed Transfer: " + transfer.dataset.id);
    } );
});

// When Page Loads Display All Of the Money Transfers Related to the User
window.onload = () => {
    let output = ``;
    transfers.forEach( transfer => {
        output += `
            <tr data-state="pending" data-id="${transfer.id}">
                <td>${transfer.id}</td>
                <td>${transfer.srcAccount}</td>
                <td>${transfer.destAccount}</td>
                <td>${transfer.amount}</td>
                <td>${transfer.date}</td>
                <td>${transfer.status}</td>
                <td><input  type="radio" data-action="accept" name="accept-reject-transfer-${transfer.id}" ${ transfer.status !== "Pending" ? 'disabled' : '' } ></td>
                <td><input  type="radio" data-action="reject" name="accept-reject-transfer-${transfer.id}" ${ transfer.status !== "Pending" ? 'disabled' : '' }></td>
            </tr>
        `;
    } );
    tableBodyTransfers.innerHTML = output;
}

