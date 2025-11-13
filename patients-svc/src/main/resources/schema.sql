CREATE TABLE IF NOT EXISTS `patient` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    phone_number VARCHAR(20),
    date_of_birth DATE DEFAULT NULL,
    occupation VARCHAR(100) DEFAULT NULL,
    nationality VARCHAR(50) DEFAULT NULL,
    ppsn VARCHAR(20) DEFAULT NULL,
    address_line1 VARCHAR(200) DEFAULT NULL,
    city VARCHAR(100) DEFAULT NULL,
    state VARCHAR(100) DEFAULT NULL,
    country VARCHAR(100) DEFAULT NULL,
    zip_code VARCHAR(20) DEFAULT NULL,
    status ENUM('ARCHIVED','UNARCHIVED') DEFAULT 'UNARCHIVED',
   `created_at` date NOT NULL,
   `created_by` varchar(20) NOT NULL,
   `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `treatments` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    treatment_name VARCHAR(100) NOT NULL,
    link VARCHAR(255),
   `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
     `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS family_profiles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    relation VARCHAR(50) NOT NULL,
    patient_id BIGINT NOT NULL,
    age INT DEFAULT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
     `updated_by` varchar(20) DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS requests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transfer_id VARCHAR(50) UNIQUE,
    patient_id BIGINT NOT NULL,
    consultant_id BIGINT NOT NULL,
    date DATE NOT NULL,
    request_type ENUM('LEAVE_LETTER', 'APPOINTMENT', 'PRESCRIPTIONS', 'PATIENT_TRANSFER', 'GENERAL') NOT NULL,
    doctor_name VARCHAR(50),
    transferred_to VARCHAR(100) DEFAULT NULL,
    description TEXT,
    status ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING',
    reason VARCHAR(255),
    duration VARCHAR(100),
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
     `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS medical_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profile_id BIGINT NOT NULL,
    blood_group VARCHAR(10),
    creyOS_score INT,
    physically_challenged BOOLEAN,
    weight DECIMAL(5,2),
    height VARCHAR(20),
    blood_pressure VARCHAR(20),
    heart_rate INT,
    other_medical_condition TEXT,
    allergies TEXT,
    smoking INT,
    drinking INT,
   `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
     `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS gp_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profile_id BIGINT NOT NULL,
    doctor_name VARCHAR(100),
    clinic_name VARCHAR(100),
    phone_number VARCHAR(50),
    email VARCHAR(255),
    licence_number VARCHAR(50),
    patient_since DATE,
    address TEXT,
    city VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100),
    zip_code VARCHAR(20),
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
     `updated_by` varchar(20) DEFAULT NULL
);

