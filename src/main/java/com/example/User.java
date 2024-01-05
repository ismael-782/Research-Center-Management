package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean sign_in() {
        return authenticate_user(username, password);

    }

    public boolean authenticate_user(String username, String password) {
        //s20221523@gmail.com
        //0123456789
//        username = username.trim();
//        password = password.trim();
//        String username_auth = (username.charAt(0)) + username.substring(9);
        if (username.length() == 19) {
            username = username.trim();
            password = password.trim();
            String username_auth = (username.charAt(0)) + username.substring(9);
            if ((username_auth.equals("s@gmail.com") || username_auth.equals("a@gmail.com")) && username.length() == 19) {
                if (String.valueOf(username.charAt(0)).equals("s")) {
                    return searchInMemberFile(username.substring(1, 9), password);

                } else if (String.valueOf(username.charAt(0)).equals("a")) {
                    return searchInAdminFile(username.substring(1, 9), password);
                }
            } else {
                return false;
            }
            return false;
        } else return false;
    }


    public boolean searchInMemberFile(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/Lenovo/Desktop/demo/src/main/java/com/example/memberData"))) {
            String line;
            String[] elements;

            while ((line = reader.readLine()) != null) {
                elements = line.split("-");

                if (elements[0].equals(username) && elements[1].equals(password)) {

                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean searchInAdminFile(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/Lenovo/Desktop/demo/src/main/java/com/example/adminData.file"))) {
            String line;
            String[] elements;


            while ((line = reader.readLine()) != null) {
                elements = line.split("-");
                if (elements[0].equals(username) && elements[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Search parameter not found in the file or an error occurred
    }
}