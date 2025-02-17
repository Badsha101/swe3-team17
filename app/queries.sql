CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,

    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NUll,
    dateofbirth DATE NOT NULL,
    email VARCHAR(255) NOT NUll,
    password VARCHAR(255) NOT NUll,
    city VARCHAR(255) NOT NUll,
    postalcode VARCHAR(10) NOT NUll
);
CREATE TABLE relatives (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    dateofbirth DATE,
    city VARCHAR(255),
    postalcode VARCHAR(10),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    relative_id INT,
    vaccination_centre VARCHAR(255) NOT NULL,
    vaccine VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (relative_id) REFERENCES relatives(id)
);


