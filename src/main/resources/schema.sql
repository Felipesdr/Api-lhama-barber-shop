DROP ALL OBJECTS;

CREATE TABLE IF NOT EXISTS users  (
    id_user INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS role (
    id_role INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS user_role (
    id_user int NOT NULL,
    id_role int NOT NULL,
    PRIMARY KEY (id_user, id_role),
    FOREIGN KEY (id_user) REFERENCES users(id_user),
    FOREIGN KEY (id_role) REFERENCES role(id_role)
);

CREATE TABLE IF NOT EXISTS client (
    id_client INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    active BOOLEAN
);

CREATE TABLE IF NOT EXISTS barber (
    id_barber INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    description VARCHAR(1000),
    active BOOLEAN
);

CREATE TABLE IF NOT EXISTS barber_unavailable_time (
    id_barber_unavailable_time INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255),
    start TIMESTAMP NOT NULL,
    finish TIMESTAMP NOT NULL,
    id_barber INT NOT NULL,
    active BOOLEAN,
    FOREIGN KEY (id_barber) REFERENCES barber(id_barber)
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

CREATE TABLE IF NOT EXISTS schedule_status (
    id_schedule_status INT PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS schedule (
    id_schedule INT PRIMARY KEY AUTO_INCREMENT,
    id_client INT NOT NULL,
    id_service INT NOT NULL,
    id_barber INT NOT NULL,
    start TIMESTAMP NOT NULL,
    finish TIMESTAMP NOT NULL,
    id_schedule_status INT DEFAULT 1,
    FOREIGN KEY (id_client) REFERENCES client(id_client),
    FOREIGN KEY (id_service) REFERENCES service(id_service),
    FOREIGN KEY (id_barber) REFERENCES barber(id_barber),
    FOREIGN KEY (id_schedule_status) REFERENCES schedule_status(id_schedule_status)
);