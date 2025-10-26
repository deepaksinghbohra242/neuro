package com.neuromed.appointments.constants;

public final class AppointmentsConstants {

    private AppointmentsConstants() {
        // restrict instantiation
    }

    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Appointment created successfully";
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully";
    public static final String STATUS_417 = "417";
    public static final String MESSAGE_417_UPDATE = "Update operation failed. Please try again or contact Dev team";
    public static final String MESSAGE_417_DELETE = "Delete operation failed. Please try again or contact Dev team";

    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "An error occurred. Please try again or contact Dev team";

    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_SCHEDULED = "SCHEDULED";
    public static final String STATUS_COMPLETED = "COMPLETED";
    public static final String STATUS_CANCELLED = "CANCELLED";
}
