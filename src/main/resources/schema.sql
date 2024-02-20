DROP ALL OBJECTS;

CREATE TABLE IF NOT EXISTS users  (
    id_user INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    role VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS barber_unavailable_time (
    id_barber_unavailable_time INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255),
    start TIMESTAMP NOT NULL,
    finish TIMESTAMP NOT NULL,
    id_barber INT NOT NULL,
    active BOOLEAN,
    FOREIGN KEY (id_barber) REFERENCES users(id_user)
);

CREATE TABLE IF NOT EXISTS service (
    id_service INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    duration INT,
    price DECIMAL(10, 2),
    active BOOLEAN
);

CREATE TABLE IF NOT EXISTS unavailable_time (
    id_unavailable_time INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255),
    start TIMESTAMP NOT NULL,
    finish TIMESTAMP NOT NULL,
    active BOOLEAN
);

CREATE TABLE IF NOT EXISTS schedule (
    id_schedule INT PRIMARY KEY AUTO_INCREMENT,
    id_client INT NOT NULL,
    id_service INT NOT NULL,
    id_barber INT NOT NULL,
    start TIMESTAMP NOT NULL,
    finish TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_client) REFERENCES users(id_user),
    FOREIGN KEY (id_service) REFERENCES service(id_service),
    FOREIGN KEY (id_barber) REFERENCES users(id_user)
);