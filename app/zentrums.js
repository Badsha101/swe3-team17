
window.onload = function() {
    getCenters();
};

// Add event listener for "Add Zentrum" button
document.getElementById('addCenterBtn').addEventListener('click', function() {
    addCenterRow();
});

// Function to fetch centers
function getCenters() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "centers", true);
    xhr.onload = function() {
        if (xhr.status === 200) {
            processCenters(JSON.parse(xhr.responseText));
        } else {
            console.error("Error retrieving data:", xhr.statusText);
        }
    };
    xhr.send();
}

// Function to process fetched centers
function processCenters(parsedData) {
    const centerTable = document.getElementById("centerTable");

    // Clear existing table content
    centerTable.innerHTML = "";

    // Create table header row
    const headerRow = centerTable.insertRow(0);
    const idHeader = headerRow.insertCell(0);
    const nameHeader = headerRow.insertCell(1);
    const locationHeader = headerRow.insertCell(2);
    idHeader.textContent = "ID";
    nameHeader.textContent = "Name";
    locationHeader.textContent = "Location";

    // Populate table with center data
    parsedData.data.forEach(item => {
        const newRow = centerTable.insertRow(-1);
        const idCell = newRow.insertCell(0);
        const nameCell = newRow.insertCell(1);
        const locationCell = newRow.insertCell(2);
        idCell.textContent = item.id;
        nameCell.textContent = item.name;
        locationCell.textContent = item.location;
    });
}

function addCenterRow() {
    const centerTable = document.getElementById('centerTable').getElementsByTagName('tbody')[0];
    const newRow = centerTable.insertRow(-1);
    const nameCell = newRow.insertCell(0);
    const locationCell = newRow.insertCell(1);
    const submitCell = newRow.insertCell(2);

    // Add input fields for Name and Location
    nameCell.innerHTML = '<input type="text" placeholder="Name">';
    locationCell.innerHTML = '<input type="text" placeholder="Location">';
    submitCell.innerHTML = '<button type="button" onclick="submitCenter(this)">Submit</button>';
}

// Function to generate automatic ID
// Function to handle form submission
function submitCenter(button) {
    // Get the row containing the input fields
    const row = button.parentNode.parentNode;

    // Get the input field values
    const name = row.cells[0].querySelector('input').value;
    const location = row.cells[1].querySelector('input').value;

    // Construct the URL for form submission
    // const url = 'submitCenter'; // Replace 'submitVaccine' with the actual URL

    const data = '&name=' + encodeURIComponent(name) +
        '&location=' + encodeURIComponent(location);

    // Create and send a POST request with the form data
    const xhr = new XMLHttpRequest();
    xhr.open('POST', "submitCenter", true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onload = function() {
        if (xhr.status === 200) {
            // Handle successful submission
            alert('Form submitted successfully');
            getCenters();
        } else {
            // Handle submission error
            console.error('Form submission failed:', xhr.statusText);
        }
    };
    xhr.onerror = function() {
        // Handle network error
        console.error('Network error occurred');
    };
    xhr.send(data);
}
//
// function submitCenter(button) {
//     // Get the row containing the input fields
//     const row = button.parentNode.parentNode;
//
//     // Get the input field values
//     const name = row.cells[0].querySelector('input').value;
//     const location = row.cells[1].querySelector('input').value;
//
//     // Construct the URL for form submission
//     const url = 'submitCenter'; // Replace 'submitCenter' with the actual URL
//
//     const data = '&name=' + encodeURIComponent(name) +
//         '&location=' + encodeURIComponent(location);
//
//     // Create and send a POST request with the form data
//     const xhr = new XMLHttpRequest();
//     xhr.open('POST', url, true);
//     xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//     xhr.onload = function() {
//         if (xhr.status === 200) {
//             // Handle successful submission
//             alert('Form submitted successfully');
//
//
//             // Parse the response to get the added center's information
//             const response = JSON.parse(xhr.responseText);
//             const addedCenter = response.data; // Assuming the added center data is returned in 'data' property
//
//             // Create a new row for the added center
//             const centerTable = document.getElementById('centerTable').getElementsByTagName('tbody')[0];
//             const newRow = centerTable.insertRow(-1);
//             const idCell = newRow.insertCell(0);
//             const nameCell = newRow.insertCell(1);
//             const locationCell = newRow.insertCell(2);
//
//             // Populate the new row with the added center's information
//             idCell.textContent = addedCenter.id;
//             nameCell.textContent = addedCenter.name;
//             locationCell.textContent = addedCenter.location;
//         } else {
//             // Handle submission error
//             console.error('Form submission failed:', xhr.statusText);
//         }
//     };
//     xhr.onerror = function() {
//         // Handle network error
//         console.error('Network error occurred');
//     };
//     xhr.send(data);
// }
