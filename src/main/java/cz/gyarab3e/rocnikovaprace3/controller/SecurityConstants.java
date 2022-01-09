package cz.gyarab3e.rocnikovaprace3.controller;

public class SecurityConstants {

    public static final String SECRET = "86733990";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/signUp";
    public static final String SIGN_IN_URL = "/signIn";
}
