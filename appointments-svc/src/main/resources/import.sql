-- Patients
INSERT INTO patient (user_id, gender, contact, address, status, created_at, created_by)
VALUES
(201, 'Male', '9876543210', '12 MG Road, Bangalore', 'UNARCHIVED', CURRENT_TIMESTAMP, 'system'),
(202, 'Female', '9998887776', '45 Nehru Street, Chennai', 'UNARCHIVED', CURRENT_TIMESTAMP, 'system');

-- Appointments
INSERT INTO appointments (patient_id, consultant_id, date, time_slot, end_time, status, visit_type, reason, billing_id, created_at, created_by)
VALUES
(1, 1, '2025-10-22', '09:00:00', '09:30:00', 'SCHEDULED', 'Follow-up', 'Headache and dizziness', 5001, CURRENT_TIMESTAMP, 'system'),
(2, 2, '2025-10-23', '10:00:00', '10:20:00', 'COMPLETED', 'First Visit', 'Back pain', 5002, CURRENT_TIMESTAMP, 'system');

-- Appointment Address
INSERT INTO appointment_address (appointment_id, full_address, city, state, country, zip_code, created_at, created_by)
VALUES
(1, '12 MG Road', 'Bangalore', 'Karnataka', 'India', '560001', CURRENT_TIMESTAMP, 'system'),
(2, '45 Nehru Street', 'Chennai', 'Tamil Nadu', 'India', '600001', CURRENT_TIMESTAMP, 'system');

-- Appointment Medical Details
INSERT INTO appointment_medical_details (appointment_id, weight, blood_pressure, heart_rate, date, doctor_name, clinic_name, address, created_at, created_by)
VALUES
(1, 70.50, '120/80', 75, '2025-10-22', 'Dr. Rajesh Kumar', 'NeuroMed Clinic', '12 MG Road, Bangalore', CURRENT_TIMESTAMP, 'system'),
(2, 82.30, '130/85', 78, '2025-10-23', 'Dr. Priya Sharma', 'Ayush Care Center', '45 Nehru Street, Chennai', CURRENT_TIMESTAMP, 'system');

-- Appointment Other Details
INSERT INTO appointment_other_details (appointment_id, full_name, phone, email, relation, insurance_company, policy_number, created_at, created_by)
VALUES
(1, 'Amit Singh', '9876543210', 'amit.singh@example.com', 'Brother', 'HDFC Ergo', 'HDFC12345', CURRENT_TIMESTAMP, 'system'),
(2, 'Neha Verma', '9998887776', 'neha.verma@example.com', 'Wife', 'ICICI Lombard', 'ICICI99887', CURRENT_TIMESTAMP, 'system');

-- Schedules
INSERT INTO schedules (consultant_id, date, created_at, created_by)
VALUES
(101, '2025-10-22', CURRENT_TIMESTAMP, 'system'),
(102, '2025-10-23', CURRENT_TIMESTAMP, 'system');

-- Time Slots
INSERT INTO time_slots (schedule_id, start_time, end_time, available, created_at, created_by)
VALUES
(1, '09:00:00', '09:30:00', FALSE, CURRENT_TIMESTAMP, 'system'),
(2, '10:00:00', '10:20:00', TRUE, CURRENT_TIMESTAMP, 'system');
