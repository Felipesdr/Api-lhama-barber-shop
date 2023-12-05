DROP ALL OBJECTS;
CREATE TABLE IF NOT EXISTS "USER"  (
    id_user INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_type VARCHAR(255) NOT NULL
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

CREATE TABLE IF NOT EXISTS service (
    id_service INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    duration INT,
    price DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS available_time (
    id_available_time INT PRIMARY KEY AUTO_INCREMENT,
    available_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    status VARCHAR(20) DEFAULT 'Available',
    id_barber  INT,
    FOREIGN KEY (id_barber) REFERENCES barber(id_barber)
);

CREATE TABLE IF NOT EXISTS scheduling (
    id_scheduling INT PRIMARY KEY AUTO_INCREMENT,
    id_client INT NOT NULL,
    id_service INT NOT NULL,
    id_available_time INT NOT NULL,
    scheduling_status VARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY (id_client) REFERENCES client(id_client),
    FOREIGN KEY (id_service) REFERENCES service(id_service),
    FOREIGN KEY (id_available_time) REFERENCES available_time(id_available_time)
);