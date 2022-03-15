package cz.gyarab3e.rocnikovaprace3.controller;

public class SecurityConstants {

    public static final String SECRET = "09876543456789";
    public static final long EXPIRATION_TIME = 1_800_000; // 30 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/signUp";
    public static final String SIGN_IN_URL = "/users/signIn";
}
