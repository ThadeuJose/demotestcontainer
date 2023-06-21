DROP TABLE IF EXISTS customers;
CREATE TABLE customers(id serial PRIMARY KEY, first_name VARCHAR(255), last_name VARCHAR(255));
INSERT INTO customers (first_name, last_name) VALUES ('Trisha', 'Gee');
INSERT INTO customers (first_name, last_name) VALUES ('Marco', 'Behler');