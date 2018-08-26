package com.telerik.payment_system.constants;

public class Constants {
    public static final String EMAIL_SUBJECT = "Password change";

    public static final String EMAIL_TEXT = "Hello %s!\n ";

    //Error messages
    public static final String USERNAME_LENGTH = "Username should be between 4 and 20 symbols";

    public static final String PASSWORD_LENGTH = "Password should be between 4 and 30 symbols";

    public static final String FULL_NAME_LENGTH = "Name should be at least 3 symbols.";

    public static final String ENTER_VALID_EMAIL = "Name should be at least 3 symbols.";

    //Security
    public static final String SECRET = "NGPUPPIES_APP_SECRET";

    public static final int EXPIRATION_DURATION = 1200000;
}
