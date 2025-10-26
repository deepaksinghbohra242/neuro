package com.neuromed.patients.constants;

public final class RequestsConstants {

    private RequestsConstants() {}

    public static final String STATUS_200 = "200";
    public static final String STATUS_201 = "201";
    public static final String STATUS_417 = "417";

    public static final String MESSAGE_201_CREATE = "Request created successfully";
    public static final String MESSAGE_200_LIST = "Requests fetched successfully";
    public static final String MESSAGE_200_DETAILS = "Request details fetched successfully";
    public static final String MESSAGE_200_UPDATE = "Request updated successfully";
    public static final String MESSAGE_200_DELETE = "Request deleted successfully";

    public static final String MESSAGE_417_CREATE = "Failed to create request. Please try again.";
    public static final String MESSAGE_417_LIST = "Failed to fetch requests. Please try again.";
    public static final String MESSAGE_417_DETAILS = "Failed to fetch request details. Please try again.";
    public static final String MESSAGE_417_UPDATE = "Failed to update request. Please try again.";
    public static final String MESSAGE_417_DELETE = "Failed to delete request. Please try again.";
}
