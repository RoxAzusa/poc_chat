CREATE TABLE language (
    id INT PRIMARY KEY AUTO_INCREMENT,
    language VARCHAR(255) NOT NULL
);

CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_language INT NOT NULL,
    role ENUM('User', 'Support', 'Employee', 'Admin') NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    birth_date DATE,
    address VARCHAR(255),
    FOREIGN KEY (id_language) REFERENCES language(id)
);

CREATE TABLE chat (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_user_user INT NOT NULL,
    id_user_support INT,
    solved BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_user_user) REFERENCES user(id),
    FOREIGN KEY (id_user_support) REFERENCES user(id)
);

CREATE TABLE message (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_chat INT NOT NULL,
    id_user_sender INT NOT NULL,
    content TEXT NOT NULL,
    send_date DATETIME NOT NULL,
    FOREIGN KEY (id_chat) REFERENCES chat(id),
    FOREIGN KEY (id_user_sender) REFERENCES user(id)
);

CREATE TABLE rental_agency (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20)
);

CREATE TABLE vehicle_model (
	id INT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL
);

CREATE TABLE vehicle_agency (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_rental_agency INT NOT NULL,  
    id_vehicle_model INT NOT NULL,
    license_plate VARCHAR(50) UNIQUE NOT NULL,
    FOREIGN KEY (id_rental_agency) REFERENCES rental_agency(id),
    FOREIGN KEY (id_vehicle_model) REFERENCES vehicle_model(id)
);

CREATE TABLE rental (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_user INT NOT NULL,
    id_rental_agency_pickup INT NOT NULL,
    id_rental_agency_return INT NOT NULL,
	id_vehicle_agency INT NOT NULL,
	start_date_time DATETIME NOT NULL,
    end_date_time DATETIME NOT NULL,
    status ENUM('Request send', 'Accepted', 'Active', 'Finished', 'Cancelled') NOT NULL,
    price DECIMAL NOT NULL,
    FOREIGN KEY (id_user) REFERENCES user(id),
    FOREIGN KEY (id_rental_agency_pickup) REFERENCES vehicle_agency(id),
    FOREIGN KEY (id_rental_agency_return) REFERENCES vehicle_agency(id),
	FOREIGN KEY (id_vehicle_agency) REFERENCES vehicle_agency(id)
);

INSERT INTO language (id, language) VALUES (1, 'Français');
INSERT INTO user (id_language, role, email, password, first_name, last_name, birth_date, address) VALUES
(1, 'User', 'user@test.com', 'user', 'Jean', 'Dupont', '1990-05-15', '123 rue Exemple, 74000 Annecy'),
(1, 'Support', 'support@test.com', 'support', 'Sophie', 'Martin', '1985-10-20', '456 avenue Exemple, 85600 Montaigu');