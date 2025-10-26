package com.neuromed.patients.constants;

public final class FamilyProfileConstants {

    private FamilyProfileConstants() {}

    public static final String STATUS_200 = "200";
    public static final String STATUS_201 = "201";
    public static final String STATUS_417 = "417";

    public static final String MESSAGE_201_CREATE = "Family profile created successfully";
    public static final String MESSAGE_200_LIST = "Family profiles fetched successfully";
    public static final String MESSAGE_200_INVITE = "Family member invited successfully";

    public static final String MESSAGE_417_CREATE = "Failed to create family profile. Please try again.";
    public static final String MESSAGE_417_LIST = "Failed to fetch family profiles. Please try again.";
    public static final String MESSAGE_417_INVITE = "Failed to send invitation. Please try again.";
}
