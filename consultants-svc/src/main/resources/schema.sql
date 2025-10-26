CREATE TABLE IF NOT EXISTS consultants (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    specialization VARCHAR(100),
    experience_years INT,
    qualification VARCHAR(255),
    status ENUM('ARCHIVED','UNARCHIVED') DEFAULT 'UNARCHIVED',
    `created_at` date NOT NULL,
   `created_by` varchar(20) NOT NULL,
   `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS  `consultant_educations` (
            id BIGINT AUTO_INCREMENT PRIMARY KEY,
            consultant_id BIGINT NOT NULL,
            degree_name VARCHAR(255),
            field_of_study VARCHAR(255),
            institution VARCHAR(255),
            start_year DATE,
            end_year DATE,
    		`created_at` date NOT NULL,
    	    `created_by` varchar(20) NOT NULL,
    	    `updated_at` date DEFAULT NULL,
    	    `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS  `certifications` (
             id BIGINT AUTO_INCREMENT PRIMARY KEY,
             consultant_id BIGINT NOT NULL,
             url VARCHAR(255),
            `created_at` date NOT NULL,
            `created_by` varchar(20) NOT NULL,
             `updated_at` date DEFAULT NULL,
             `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS  `schedule` (
            id BIGINT AUTO_INCREMENT PRIMARY KEY,
            consultant_id BIGINT NOT NULL,
            start_date DATE,
            start_time TIME,
            end_date DATE,
            end_time TIME,
            reason_for_unavailability VARCHAR(255),
            status ENUM('schedule','completed','pending','cancelled','internal_meeting'),
            `created_at` date NOT NULL,
            `created_by` varchar(20) NOT NULL,
            `updated_at` date DEFAULT NULL,
            `updated_by` varchar(20) DEFAULT NULL
);

