CREATE DATABASE ecommerce_engine
CREATE SCHEMA ecommerce;

CREATE TABLE ecommerce.customer (
    id BIGSERIAL NOT NULL,
    name VARCHAR(55) NOT NULL,
    document VARCHAR(11) NOT NULL,
    email VARCHAR(30) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(13) NOT null,
    birth_date DATE NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT pk_customer PRIMARY KEY (id)

);

CREATE UNIQUE INDEX unq_document_active ON customer(document);
CREATE UNIQUE INDEX unq_email_active ON customer(email);
CREATE UNIQUE INDEX unq_phone_number_active ON customer(phone_number);

CREATE TABLE ecommerce.address (
	id BIGSERIAL NOT NULL,
	customer_id BIGSERIAL not null,
	zipcode varchar(8) not null,
	uf varchar(2) not null,
	city varchar(20) not null,
	district varchar(20) not null,
	street_name varchar(40) not null,
	street_number varchar(4),
	complement varchar(20),
	constraint pk_address primary key (id),
	constraint fk_address_customer foreign key (customer_id) references ecommerce.customer(id)
);