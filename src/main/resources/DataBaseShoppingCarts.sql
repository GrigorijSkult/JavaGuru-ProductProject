REATE SCHEMA product_repository;

USE product_repository;

-- OneToMany and ManyToOne

CREATE TABLE products
(
    id           BIGINT      NOT NULL AUTO_INCREMENT,
    name         VARCHAR(32) NOT NULL,
    regular_price DECIMAL     NOT NULL,
    category     VARCHAR(25) NOT NULL,
    discount     DECIMAL,
    description  VARCHAR(99),
    shopping_cart_id BIGINT,

    FOREIGN KEY (shopping_cart_id) REFERENCES shopping_carts (id),
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE shopping_carts
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(50),

    PRIMARY KEY (id)
);

-- ManyToMany
CREATE TABLE products
(
    id            BIGINT      NOT NULL AUTO_INCREMENT,
    name          VARCHAR(32) NOT NULL,
    regular_price DECIMAL     NOT NULL,
    category      VARCHAR(25) NOT NULL,
    discount      DECIMAL,
    description   VARCHAR(99),

    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE shopping_carts
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(50),

    PRIMARY KEY (id)
);

CREATE TABLE products_in_shopping_carts
(
    shopping_cart_id BIGINT NOT NULL ,
    products_id      BIGINT NOT NULL,

    FOREIGN KEY (shopping_cart_id) REFERENCES shopping_carts (id),
    FOREIGN KEY (products_id) REFERENCES products (id)
);

