
bookingType.addEventListener('change', function() {
    if (bookingType.value === 'relative') {
        relativeInfo.style.display = 'block';
    } else {
        relativeInfo.style.display = 'none';
    }
});
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
    pleaseChoosePlaceHolderOption.textContent = "Bitte wählen";
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
function getVaccinesAndTimeSlots(JSONStringCenter) {
    console.log(JSONStringCenter)

    const centerId = JSON.parse(JSONStringCenter).id
    getVaccines(centerId);
    getTimeSlots(centerId);
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
    const vaccineSelect = document.getElementById("vaccine");
    const vaccineNameSelect = document.getElementById("vaccine_name");

    vaccineSelect.innerHTML = "";
    parsedVaccines.data.forEach(vac => {
        const vaccineOptionId = document.createElement("option");
        vaccineOptionId.value = JSON.stringify({
            id: vac.id,
            name: vac.name
        });
        vaccineOptionId.textContent = vac.name;
        vaccineSelect.appendChild(vaccineOptionId);
    });
}

function getTimeSlots(id) {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "timeslots?id=" + id, true);
    xhr.onload = function() {
        if (xhr.status === 200) {
            processTimeSlots(JSON.parse(xhr.responseText));
        } else {
            console.error("Error retrieving data:", xhr.statusText);
        }
    };
    xhr.send();
}

function processTimeSlots(parsedTimeSlots) {
    const timeSlotSelect = document.getElementById("timeslots");
    timeSlotSelect.innerHTML = "";

    // document.getElementById("timeslotId").value = defaultTimeSlotId;

    parsedTimeSlots.data.forEach(slot => {
        console.log("slot: ", slot);
        const timeSlotOption = document.createElement("option");
        timeSlotOption.value = JSON.stringify({
            id: slot.id,
            name: slot.start_time
        });
        timeSlotOption.textContent = slot.start_time;
        timeSlotSelect.appendChild(timeSlotOption);
    });
}

// Call getCenters when the page loads
window.onload = function() {
    getCenters();
};

const dateInput = document.getElementById("appointmentdate");

// Set the minimum date to today
dateInput.min = new Date().toISOString().split('T')[0];


const urlParams = new URLSearchParams(window.location.search);
const error = urlParams.get("error");
const registration = urlParams.get("registration");


// Display error message if login fails
if (error === "1") {
    const errorMessage = document.createElement("p");
    errorMessage.textContent = "Ihr Termin konnte nicht geschpeichert werden.";
    errorMessage.style.color = "red";
    const parentElement = document.querySelector(".container");
    parentElement.appendChild(errorMessage);
}
if (error === "4") {
    const limit = document.createElement("p");
    limit.textContent = "Sie dürfen maximal 4 Termine buchen";
    limit.style.color = "red";
    const parentElement = document.querySelector(".container");
    parentElement.appendChild(limit)}






