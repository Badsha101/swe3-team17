function initializePage() {
    function onCenterSelected() {
        const centerSelect = document.getElementById("location");
        const selectedOption = centerSelect.options[centerSelect.selectedIndex];
        if (selectedOption.value) {
            const center = JSON.parse(selectedOption.value);
            console.log(center.id)
            getVaccines(center.id);
            displayCenterTable(true);
        } else {
            displayCenterTable(false);
        }
    }

    function displayCenterTable(show) {
        document.getElementById("centerTable").style.display = show ? "table" : "none";
    }

    window.addEventListener("DOMContentLoaded", function () {
        const urlParams = new URLSearchParams(window.location.search);
        const centerId = urlParams.get('centerId');
        if (centerId) {
            getVaccines(centerId);
            displayCenterTable(true);
        } else {
            getCenters();
            document.getElementById("location").style.display = "block";
            displayCenterTable(false);
        }
    });


// Add event listener to handle center selection changes
    const centerSelect = document.getElementById("location");
    centerSelect.addEventListener("change", onCenterSelected);
}

// Call the initializePage function when the DOM content is loaded
initializePage();

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

function processCenters(parsedData) {
    const centerSelect = document.getElementById("location");
    centerSelect.innerHTML = "";
    const pleaseChoosePlaceHolderOption = document.createElement("option");
    pleaseChoosePlaceHolderOption.value = "";
    pleaseChoosePlaceHolderOption.textContent = "Bitte wählen Sie ein Zentrum";
    centerSelect.appendChild(pleaseChoosePlaceHolderOption);

    parsedData.data.forEach(item => {
        const centerOption = document.createElement("option");
        centerOption.value = JSON.stringify({
            id: item.id,
            name: item.name
        });
        centerOption.textContent = item.name;
        centerSelect.appendChild(centerOption);
    });
}


function getVaccines(id) {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "vaccines?id=" + id, true);
    xhr.onload = function() {
        if (xhr.status === 200) {
            processVaccines(JSON.parse(xhr.responseText));
        } else {
            console.error("Error retrieving data:", xhr.statusText);
        }
    };
    xhr.send();
}
function processVaccines(parsedVaccines) {
    const vaccineTable = document.getElementById("centerTable");
    // Clear existing table content
    vaccineTable.innerHTML = "";

    // Create table header row
    const headerRow = vaccineTable.insertRow(0);
    const idHeader = headerRow.insertCell(0);
    const nameHeader = headerRow.insertCell(1);
    const manuHeader = headerRow.insertCell(2);
    const quantityHeader = headerRow.insertCell(3);
    idHeader.textContent = "ID";
    nameHeader.textContent = "Name";
    manuHeader.textContent = "Manufacturer";
    quantityHeader.textContent = "Quantity";

    // Populate table with vaccine data
    parsedVaccines.data.forEach(vac => {
        const newRow = vaccineTable.insertRow(-1);
        const idCell = newRow.insertCell(0);
        const nameCell = newRow.insertCell(1);
        const manuCell = newRow.insertCell(2);
        const quantityCell = newRow.insertCell(3);

        idCell.textContent = vac.id;
        nameCell.textContent = vac.name;
        manuCell.textContent = vac.manufacturer;
        quantityCell.textContent = vac.quantity;
    });
}


// function processVaccines(parsedVaccines) {
//     const vaccineSelect = document.getElementById("vaccine");
//     const vaccineNameSelect = document.getElementById("vaccine_name");
//
//     vaccineSelect.innerHTML = "";
//     parsedVaccines.data.forEach(vac => {
//         const vaccineOptionId = document.createElement("option");
//         vaccineOptionId.value = JSON.stringify({
//             id: vac.id,
//             name: vac.name
//         });
//         vaccineOptionId.textContent = vac.name;
//         vaccineSelect.appendChild(vaccineOptionId);
//     });
// }

function addVacRow() {
    const centerTable = document.getElementById('centerTable').getElementsByTagName('tbody')[0];
    const newRow = centerTable.insertRow(-1);
    const idCell = newRow.insertCell(0);
    const nameCell = newRow.insertCell(1);
    const manufacturerCell = newRow.insertCell(2);
    const quantityCell = newRow.insertCell(3);
    const submitCell = newRow.insertCell(4);

    // Get the center object from the selected option
    const centerSelect = document.getElementById("location");
    const selectedOption = centerSelect.options[centerSelect.selectedIndex];
    let centerId = '';
    if (selectedOption.value) {
        const center = JSON.parse(selectedOption.value);
        centerId = center.id; // Assuming the center object has an 'id' property
    }

    // Add input fields for Name and Location
    idCell.innerHTML = '<input type="hidden" value="' + centerId + '">';
    nameCell.innerHTML = '<input type="text" placeholder="Name">';
    manufacturerCell.innerHTML = '<input type="text" placeholder="Manufacturer">';
    quantityCell.innerHTML = '<input type="text" placeholder="Quantity">';
    submitCell.innerHTML = '<button type="button" onclick="submitVaccine(this)">Submit</button>';
}
// Function to generate automatic ID
// Function to handle form submission
function submitVaccine(button) {
    // Get the row containing the input fields
    const row = button.parentNode.parentNode;

    // Get the input field values
    const id = row.cells[0].querySelector('input').value;
    const name = row.cells[1].querySelector('input').value;
    const Manufacturer = row.cells[2].querySelector('input').value;
    const quantity = row.cells[3].querySelector('input').value;

    // Construct the URL for form submission
    const url = 'submitVaccine'; // Replace 'submitVaccine' with the actual URL

    // Prepare the form data
    const data = 'id=' + encodeURIComponent(id) +
        '&name=' + encodeURIComponent(name) +
        '&manufacturer=' + encodeURIComponent(Manufacturer) +
        '&quantity=' + encodeURIComponent(quantity);

    // Create and send a POST request with the form data
    const xhr = new XMLHttpRequest();
    xhr.open('POST', url);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onload = function() {
        if (xhr.status === 200) {
            // Handle successful submission
            alert('Form submitted successfully');
            getVaccines(id);
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
    console.log(typeof data);
    console.log(data);

}
document.getElementById('addVacButton').addEventListener('click', function() {
    addVacRow();
});
// Call getCenters when the page loads
window.onload = function() {
    getCenters();
}










