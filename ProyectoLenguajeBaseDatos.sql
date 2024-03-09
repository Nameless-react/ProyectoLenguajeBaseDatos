--------------------PROYECTO--------------------
-----------LENGUAJE DE BASES DE DATOS-----------

CREATE USER PROYECTO IDENTIFIED BY 123;
GRANT CONNECT, RESOURCE TO PROYECTO;




CREATE TABLE Address (
    id_address NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    street VARCHAR2(255),
    province VARCHAR2(50),
    canton VARCHAR2(50),
    others_signs VARCHAR2(255)
);


CREATE TABLE Rol (
    id_rol  NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    name VARCHAR2(50) NOT NULL
);


CREATE TABLE Characteristics_Property (
    id_characteristics_property NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    number_rooms NUMBER,
    numbers_bathrooms NUMBER,
    description VARCHAR2(255),
    garage NUMBER(1, 0),
    pool NUMBER(1, 0)
);


CREATE TABLE Comodity (
    id_comodity  NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    name VARCHAR2(100),
    description VARCHAR2(255)
);



CREATE TABLE Users (
    id_user NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    password VARCHAR2(255) NOT NULL,
    birth_date DATE,
    first_sur_name VARCHAR2(50),
    second_sur_name VARCHAR2(50),
    name VARCHAR2(50),
    identification VARCHAR2(20) UNIQUE,
    email VARCHAR2(100) UNIQUE,
    phone VARCHAR2(15),
    image VARCHAR2(1024)
);



CREATE TABLE Agent (
    id_agent  NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    salary DECIMAL(10, 2),
    experience NUMBER,
    id_user NUMBER,
    hire_date DATE,
    FOREIGN KEY (id_user) REFERENCES Users(id_user)
);


CREATE TABLE User_Rol (
    id_user_rol  NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    id_rol NUMBER,
    id_user NUMBER,
    FOREIGN KEY (id_rol) REFERENCES Rol(id_rol),
    FOREIGN KEY (id_user) REFERENCES Users(id_user)
);


CREATE TABLE Property (
    id_property  NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    name VARCHAR2(50),
    id_characteristics NUMBER,
    id_agent NUMBER,
    id_address NUMBER,
    price DECIMAL(10, 2),
    transaction_type VARCHAR2(50),
    antiquity NUMBER,
    owner VARCHAR2(100),
    FOREIGN KEY (id_characteristics) REFERENCES Characteristics(ID_characteristics),
    FOREIGN KEY (id_agent) REFERENCES Agent(ID_Agent),
    FOREIGN KEY (id_address) REFERENCES Address(ID_Address)
);


CREATE TABLE Favorite_Property (
    id_favorite_property  NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    id_property NUMBER,
    id_user NUMBER,
    FOREIGN KEY (id_property) REFERENCES Property(id_property),
    FOREIGN KEY (id_user) REFERENCES Users(id_user)
);



CREATE TABLE Comodity_property (
    id_comodity_property NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    id_property NUMBER,
    id_comodity NUMBER,
    FOREIGN KEY (id_property) REFERENCES Property(id_property),
    FOREIGN KEY (id_comodity) REFERENCES Comodity(id_comodity)
);


CREATE TABLE Transactions (
    id_transaction NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    id_property NUMBER,
    id_agent NUMBER,
    transaction_date DATE DEFAULT SYSDATE,
    id_user NUMBER,
    FOREIGN KEY (id_property) REFERENCES Property(id_property),
    FOREIGN KEY (id_agent) REFERENCES Agent(id_agent),
    FOREIGN KEY (id_user) REFERENCES Users(id_user)
);



CREATE TABLE ImageProperty (
    id_image_property  NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    id_property NUMBER,
    image VARCHAR2(1024),
    FOREIGN KEY (id_property) REFERENCES Property(id_property)
);


INSERT INTO Address (street, province, canton, others_signs)
VALUES ('Calle Principal 123', 'San José', 'Escazú', 'Cerca del parque');


INSERT INTO Agent (salary, experience, id_user, hire_date)
VALUES (50000.00, 5, 1,TO_DATE('2020-03-12', 'YYYY-MM-DD'));


INSERT INTO Characteristics_Property (number_rooms, numbers_bathrooms, description, garage, pool)
VALUES (3, 2, 'Hermosa casa con amplio jardín', 1, 0);


INSERT INTO Property (name, id_characteristics, id_agent, id_address, price, transaction_type, antiquity, owner)
VALUES ('Casa de Ensueño', 1, 1, 1, 250000.00, 'Venta', 10, 'Juan Pérez');

INSERT INTO Users (password, birth_date, first_sur_name, second_sur_name, name, identification, email, phone, image)
VALUES ('contrasena123', TO_DATE('1990-01-15', 'YYYY-MM-DD'), 'Pérez', 'Gómez', 'Juan', '123456789', 'juan@example.com', '123-456-7890', 'imagen.jpg');