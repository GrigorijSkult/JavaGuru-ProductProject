CREATE SCHEMA product_repository;

USE product_repository;

CREATE TABLE products
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(32) NOT NULL,
    regularPrice DECIMAL     NOT NULL,
    category     VARCHAR(20) NOT NULL,
    discount     DECIMAL,
    description  VARCHAR(99),

    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE shopping_carts
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(50),
    product_id  BIGINT,

    FOREIGN KEY (product_id) REFERENCES products (id),
    PRIMARY KEY (id)
);