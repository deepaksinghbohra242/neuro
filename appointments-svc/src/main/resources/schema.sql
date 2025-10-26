
CREATE TABLE IF NOT EXISTS `appointments` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    consultant_id BIGINT NOT NULL,
    date DATE NOT NULL,
    time_slot TIME NOT NULL,
    status ENUM('PENDING','SCHEDULED','COMPLETED','CANCELLED') DEFAULT 'PENDING',
    visit_type VARCHAR(50),
    reason  VARCHAR(200),
    billing_id BIGINT,
   `created_at` date NOT NULL,
   `created_by` varchar(20) NOT NULL,
   `updated_at` date DEFAULT NULL,
   `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS appointment_address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    appointment_id BIGINT NOT NULL,
    full_address VARCHAR(100),
    city VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100),
    zip_code VARCHAR(20),
   `created_at` date NOT NULL,
   `created_by` varchar(20) NOT NULL,
   `updated_at` date DEFAULT NULL,
   `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `appointment_medical_details` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    appointment_id BIGINT NOT NULL,
    weight DECIMAL(5,2),
    blood_pressure VARCHAR(20),
    heart_rate INT,
    date DATE,
    doctor_name VARCHAR(100),
    clinic_name VARCHAR(100),
    address VARCHAR(100),
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `appointment_other_details` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    appointment_id BIGINT NOT NULL,
    full_name VARCHAR(100),
    phone VARCHAR(50),
    email VARCHAR(255),
    relation VARCHAR(50),
    insurance_company VARCHAR(100),
    policy_number VARCHAR(50),
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS time_slots (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    schedule_id BIGINT NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    available BOOLEAN DEFAULT TRUE,
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);
