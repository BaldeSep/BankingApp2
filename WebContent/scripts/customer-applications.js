// Collection Of Fake Applications
let applications = [
    {
        id: 10,
        holder: 'balde',
        amount: 30.00,
        date: "12/15/2019"
    },
    {
        id: 11,
        holder: 'colin',
        amount: 40.00,
        date: "12/15/2019"
    },
    {
        id: 12,
        holder: 'killian',
        amount: 50.00,
        date: "12/15/2019"
    },
    {
        id: 13,
        holder: 'kim',
        amount: 34.00,
        date: "12/15/2019"
    },
    {
        id: 14,
        holder: 'sam',
        amount: 20.00,
        date: "12/15/2019"
    },
    {
        id: 15,
        holder: 'jango',
        amount: 0.00,
        date: "12/15/2019"
    },
];

// Get the table body
let tableApplicationBody = document.getElementById("table-application-body");

// Get the button for processing the applications
let btnProcessApplications = document.getElementById("process-applications");

// Add Event Listener for the button to process the applications
btnProcessApplications.addEventListener("click", e => {
    tableApplicationBody.querySelectorAll(".accepted").forEach( entry => {
        // Process Application Here Sending To The backend
        console.log("Processing Application #" + entry.id.split("-")[2]);
    } );
    tableApplicationBody.querySelectorAll(".rejected").forEach(entry => {
        // Process Application Here Sending To The backend
        console.log("Processing Application #" + entry.id.split("-")[2]);
    });
});

// Add Event Listener For table body for accept and reject radio buttons
tableApplicationBody.addEventListener('click', e => {
    if(e.target.classList.contains("accept")){
        e.target.parentNode.parentNode.classList = "accepted";
    } else if(e.target.classList.contains("reject")){
        e.target.parentNode.parentNode.classList = "rejected";
    }
});

// Load All Dummy Data At Start 
// Simulation of initial get on loading the page.
window.onload = loadApplications;

function loadApplications(){
	fetch("http://localhost:5050/MaximusBank/apply")
		.then( res => res.json() )
		.then( data => {
			if(data.hasOwnProperty("message")){
				alert(data.message);
			}else{
				let output = ``;
				data.forEach( app => {
					output += `
						 <tr class="rejected" id="application-id-${app.applicationId}">
			                <td>${app.applicationId}</td>
			                <td>${app.applicant}</td>
			                <td>$${app.initialBalance}</td>
			                <td>${app.dateApplied.date.year}-${app.dateApplied.date.month}-${app.dateApplied.date.day}</td>
			                <td> <input type="radio" class="accept" name="accept-reject=${app.applicationId}"></td>
			                <td> <input type="radio" class="reject" name="accept-reject=${app.applicationId}" checked></td>
						</tr>
					`
				});
				tableApplicationBody.innerHTML = output;
			}
		});
} 