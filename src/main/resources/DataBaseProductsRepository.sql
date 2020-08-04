CREATE SCHEMA product_repository;

USE product_repository;

CREATE TABLE products(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    regularPrice DECIMAL(20,4) NOT NULL,
    category VARCHAR(25) NOT NULL,
    discount DECIMAL(20,4),
    description VARCHAR(99),

    PRIMARY KEY (id),
    UNIQUE (name)
)