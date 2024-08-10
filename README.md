MONITORING INVENTORY EXAM

Lib :
- Mysql Driver
- Java 17
- JCalendar

SQL :
CREATE DATABASE db_inventory;

USE db_inventory;

-- Tabel t1_category_product
CREATE TABLE t1_category_product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    name VARCHAR(255) NOT NULL,
    description TEXT
);


-- Tabel t1_user
CREATE TABLE t1_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);

-- Tabel t1_product
CREATE TABLE t1_product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    category_product_id INT,
    is_deleted BOOLEAN DEFAULT FALSE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    stock INT DEFAULT 0,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (created_by) REFERENCES t1_user(id),
    FOREIGN KEY (updated_by) REFERENCES t1_user(id),
    FOREIGN KEY (category_product_id) REFERENCES t1_category_product(id)
);

-- Tabel t2_product_inventory_log
CREATE TABLE t2_product_inventory_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    product_id INT,
    stock_before INT,
    stock_after INT,
    adjustment INT,
    description TEXT,
    FOREIGN KEY (created_by) REFERENCES t1_user(id),
    FOREIGN KEY (product_id) REFERENCES t1_product(id)
);

-- Tabel t2_transaction
CREATE TABLE t2_transaction (
    id INT AUTO_INCREMENT PRIMARY KEY,
    is_deleted BOOLEAN DEFAULT FALSE,
    code VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    product_id INT,
    qty INT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    stock INT,
    FOREIGN KEY (created_by) REFERENCES t1_user(id),
    FOREIGN KEY (updated_by) REFERENCES t1_user(id),
    FOREIGN KEY (product_id) REFERENCES t1_product(id)
);


