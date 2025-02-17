create database test;

use test;
CREATE TABLE admin (
    id INT AUTO_INCREMENT PRIMARY KEY,

    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE relatives (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    firstname VARCHAR(255),
    lastname VARCHAR(255),

    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE appointment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    relative_id INT,
    timeslot_id INT NOT NULL,
    vaccine_id INT NOT NULL,
    center_id INT NOT NULL,
    booking_date DATE NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (relative_id) REFERENCES relatives(id),
    FOREIGN KEY (timeslot_id) REFERENCES timeslots(id),
    FOREIGN KEY (vaccine_id) REFERENCES vaccines(id),
    FOREIGN KEY (center_id) REFERENCES vaccinationcenter(id)
);
CREATE TABLE vaccines (
    id INT AUTO_INCREMENT PRIMARY KEY,
       center_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(255) NOT NULL,
    quantity INT,
     FOREIGN KEY (center_id) REFERENCES vaccinationcenter(id)
);

CREATE TABLE vaccinationcenter (
    id INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL
);

CREATE TABLE timeslots (
    id INT AUTO_INCREMENT PRIMARY KEY,
    center_id INT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    capacity INT NOT NULL,
    FOREIGN KEY (center_id) REFERENCES vaccinationcenter(id)
);
