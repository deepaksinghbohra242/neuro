CREATE TABLE IF NOT EXISTS `prescriptions` (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     patient_id BIGINT NOT NULL,
     consultant_id BIGINT NOT NULL,
     pharmacy_id BIGINT,
     date TIMESTAMP NOT NULL,
     no_of_medicines INT,
     duration INT,
     preferred_service ENUM('In-Store pickup','Home Delivery'),
     `created_at` date NOT NULL,
     `created_by` varchar(20) NOT NULL,
     `updated_at` date DEFAULT NULL,
     `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `prescription_medicines` (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        prescription_id BIGINT NOT NULL,
        medicine_name VARCHAR(255),
        prescribed_dose VARCHAR(50),
        frequency VARCHAR(50),
        no_of_order INT,
       `created_at` date NOT NULL,
       `created_by` varchar(20) NOT NULL,
       `updated_at` date DEFAULT NULL,
       `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `pharmacies` (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       user_id BIGINT NOT NULL,
       license_number VARCHAR(100),
       status ENUM('ARCHIVE','UNARCHIVE') DEFAULT 'UNARCHIVE',
       `created_at` date NOT NULL,
       `created_by` varchar(20) NOT NULL,
       `updated_at` date DEFAULT NULL,
       `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `orders`(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    prescription_id BIGINT NOT NULL,
    status ENUM('return','reject','approve'),
    reason_for_return_reject varchar(100),
   `created_at` date NOT NULL,
   `created_by` varchar(20) NOT NULL,
   `updated_at` date DEFAULT NULL,
   `updated_by` varchar(20) DEFAULT NULL
);

