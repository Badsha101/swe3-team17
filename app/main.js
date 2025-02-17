// const bookingType = document.getElementById('bookingType');
//
//
// // Function to toggle display of relative information based on booking type
// bookingType.addEventListener('change', function() {
//   if (bookingType.value === 'relative') {
//     relativeInfo.style.display = 'block';
//   } else {
//     relativeInfo.style.display = 'none';
//   }
// });
function createTimeOptions() {
    const timeSelect = document.getElementById('time');
    timeSelect.innerHTML = ''; // Zurücksetzen der vorherigen Optionen

    // Zeitoptionen von 08:00 bis 18:00 mit einer Differenz von 20 Minuten
    for (let hour = 8; hour < 18; hour++) {
        for (let minute = 0; minute < 60; minute += 20) {
            const timeString = `${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}`;
            const option = document.createElement('option');
            option.textContent = timeString;
            timeSelect.appendChild(option);
        }
    }
}

// Beim Laden der Seite Zeitoptionen erstellen
window.onload = function () {
    createTimeOptions();
};


function updateVaccineOptions() {
    const location = document.getElementById('location').value;
    const vaccineSelect = document.getElementById('vaccine');

    // Clear previous options
    vaccineSelect.innerHTML = '<option value="">Bitte wählen</option>';

    // Populate options based on selected location
    if (location === 'location1') {
        // Location 1 options
        const optionA = document.createElement('option');
        optionA.textContent = 'Impfstoff A';
        optionA.value = 'A';
        vaccineSelect.appendChild(optionA);

        const optionB = document.createElement('option');
        optionB.textContent = 'Impfstoff B';
        optionB.value = 'B';
        vaccineSelect.appendChild(optionB);
    } else if (location === 'location2') {
        // Location 2 options
        const optionA = document.createElement('option');
        optionA.textContent = 'Impfstoff A';
        optionA.value = 'A';
        vaccineSelect.appendChild(optionA);

        const optionC = document.createElement('option');
        optionC.textContent = 'Impfstoff C';
        optionC.value = 'C';
        vaccineSelect.appendChild(optionC);

        const optionD = document.createElement('option');
        optionD.textContent = 'Impfstoff D';
        optionD.value = 'D';
        vaccineSelect.appendChild(optionD);
    }
}

// document.getElementById('appointmentForm').addEventListener('submit', function(event) {
//   event.preventDefault();

const location = document.getElementById('location').value;
const vaccine = document.getElementById('vaccine').value;
const date = document.getElementById('date').value;
const time = document.getElementById('time').value;
// const name = document.getElementById('name').value;
// const email = document.getElementById('email').value;

// Hier können Sie die Daten verarbeiten, z.B. an einen Server senden

console.log('Standort:', typeof location);
console.log('Impfstoff:', typeof vaccine);
console.log('Datum:', typeof date);
console.log('Zeit:', typeof time);
// console.log('Name (falls für Verwandte gebucht wird):', name);
// console.log('E-Mail (falls für Verwandte gebucht wird):', email);

// Formular zurücksetzen
//   this.reset();
// });
const dateInput = document.getElementById("date");

// Set the minimum date to today
dateInput.min = new Date().toISOString().split('T')[0];


    // Get the error parameter from the URL (assuming it's passed as a query parameter)
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get("error");

    // Check for the error parameter and display the message if it's "1"
    if (error === "1") {
    const errorElement = document.createElement("p");
    errorElement.style.color = "red";
    errorElement.textContent = "Appointment didn't booked Please try again.";


    const parentElement = document.querySelector("form"); //
    parentElement.appendChild(errorElement);
}


