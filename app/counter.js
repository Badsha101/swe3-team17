function fetchData() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                displayData(response);
            } else {
                displayError('Error: ' + xhr.status);
            }
        }
    };
    xhr.open('GET', 'counter', true);
    xhr.send();
}

function displayData(data) {
    displayRegistrationData(data);
    displaySessionData(data);
    displayAppointmentData(data);
}

function displayRegistrationData(data) {
    var registrationDiv = document.getElementById('registrationData');
    registrationDiv.innerHTML = '<h2>Registration Data</h2>';
    var registrationDataDiv = document.createElement('div');
    if (data.status === '200') {
        registrationDataDiv.classList.add('data-item');
        registrationDataDiv.innerHTML = '<span class="data-label">Registrierung - Versuch:</span> <span class="data-value">' + data['Page_calledRegistrieren'] + '</span><br>' +
            '<span class="data-label">Erfolgreich Registriert:</span> <span class="data-value">' + data['s_reg'] + '</span>';
    } else {
        registrationDataDiv.classList.add('error-message');
        registrationDataDiv.textContent = 'Error: ' + data.status;
    }
    registrationDiv.appendChild(registrationDataDiv);
}

function displaySessionData(data) {
    var sessionDiv = document.getElementById('sessionData');
    sessionDiv.innerHTML = '<h2>Session Data</h2>';
    var sessionDataDiv = document.createElement('div');
    if (data.status === '200') {
        sessionDataDiv.classList.add('data-item');
        sessionDataDiv.innerHTML = '<span class="data-label">Einloggen Versuch</span> <span class="data-value">' + data['Page_calledEinloggen'] + '</span><br>' +
            '<span class="data-label">Erfolgreich :</span> <span class="data-value">' + data['sessions_created'] + '</span>';
    } else {
        sessionDataDiv.classList.add('error-message');
        sessionDataDiv.textContent = 'Error: ' + data.status;
    }
    sessionDiv.appendChild(sessionDataDiv);
}

function displayAppointmentData(data) {
    var appointmentDiv = document.getElementById('appointmentData');
    appointmentDiv.innerHTML = '<h2>Appointment Data</h2>';
    var appointmentDataDiv = document.createElement('div');
    if (data.status === '200') {
        appointmentDataDiv.classList.add('data-item');
        appointmentDataDiv.innerHTML = '<span class="data-label">Termin Versuch:</span> <span class="data-value">' + data['Page_CalledBuchung'] + '</span><br>' +
            '<span class="data-label">Erfolgreich: </span> <span class="data-value">' + data['Total_Appointment_booked'] + '</span><br>' +
            '<span class="data-label">Anwesend: </span> <span class="data-value">' + data['qr_code'] + '</span>';
    } else {
        appointmentDataDiv.classList.add('error-message');
        appointmentDataDiv.textContent = 'Error: ' + data.status;
    }
    appointmentDiv.appendChild(appointmentDataDiv);
}

function displayError(message) {
    var errorDivs = document.querySelectorAll('.error-message');
    errorDivs.forEach(function(div) {
        div.textContent = message;
    });
}

// Fetch data when the page loads
window.onload = function() {
    fetchData();
};
