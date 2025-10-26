package com.neuromed.billing.constants;

    public final class BillingConstants {
        private BillingConstants() {
        }
        public static final String STATUS_PAID = "PAID";
        public static final String STATUS_UNPAID = "UNPAID";
        public static final String DEFAULT_DESCRIPTION = "No description provided";
        public static final String STATUS_200 = "200";
        public static final String STATUS_201 = "201";
        public static final String STATUS_404 = "404";
        public static final String STATUS_417 = "417";
        public static final String MESSAGE_200 = "Request processed successfully";
        public static final String MESSAGE_201 = "Billing record created successfully";
        public static final String MESSAGE_404 = "Billing record not found";
        public static final String MESSAGE_417_CREATE = "Create operation failed. Please try again or contact Dev team";
        public static final String MESSAGE_417_UPDATE = "Update operation failed. Please try again or contact Dev team";
        public static final String MESSAGE_417_DELETE = "Delete operation failed. Please try again or contact Dev team";
        public static final String MODULE_NAME = "Billing Service";
    }


