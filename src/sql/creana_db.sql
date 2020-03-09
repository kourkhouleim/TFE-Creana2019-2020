-- Création de la db
DROP TABLE IF EXISTS creana ;
CREATE DATABASE creana ;

-- Création de la table Client ;

CREATE TABLE client (
    client_id long PRIMARY KEY,
    nomClient VARCHAR (50) NOT NULL,
    prenomClient VARCHAR (50) NOT NULL,
    email VARCHAR (300) UNIQUE NOT NULL,
    numTelClient VARCHAR (50) NOT NULL,
    adresse VARCHAR (300) NOT NULL,
    codePostal INTEGER (5) NOT NULL,
);
