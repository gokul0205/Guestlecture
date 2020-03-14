package com.example.guestlec;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
public class Unittest {
    public static final int PASSWORD_LENGTH = 8;
    public static boolean checkEmailForValidity(String email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public static boolean checkPasswordValidity(String password){
        if (password.length() < PASSWORD_LENGTH)
            return false;

        int charCount = 0;
        int numCount = 0;
        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            if (Character.isDigit(ch))
                numCount++;
            else if (Character.isLetter(ch))
                charCount++;
            else
                return false;
        }

        return (charCount >= 2 && numCount >= 2);
    }
}
