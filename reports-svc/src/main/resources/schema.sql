CREATE TABLE IF NOT EXISTS tests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date DATETIME NOT NULL,
    prescription_id INT,
    consultant_id INT,

    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
   `created_at` date NOT NULL,
     `created_by` varchar(20) NOT NULL,
     `updated_at` date DEFAULT NULL,
     `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS documents (
    id INT AUTO_INCREMENT PRIMARY KEY,
    test_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    url VARCHAR(500) NOT NULL,
    CONSTRAINT fk_test FOREIGN KEY (test_id) REFERENCES tests(id) ON DELETE CASCADE,
   `created_at` date NOT NULL,
     `created_by` varchar(20) NOT NULL,
     `updated_at` date DEFAULT NULL,
     `updated_by` varchar(20) DEFAULT NULL
);
