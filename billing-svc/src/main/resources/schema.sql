CREATE TABLE billing_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    description VARCHAR(255),
    amount DOUBLE,
    billed_at TIMESTAMP
);
CREATE TABLE IF NOT EXISTS `billings` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    appointment_id BIGINT NOT NULL,
    due_date DATE,
    description TEXT,
    amount DECIMAL(10,2) NOT NULL,
    status ENUM('PAID','UNPAID') DEFAULT 'UNPAID',
   `created_at` date NOT NULL,
   `created_by` varchar(20) NOT NULL,
   `updated_at` date DEFAULT NULL,
   `updated_by` varchar(20) DEFAULT NULL
);
