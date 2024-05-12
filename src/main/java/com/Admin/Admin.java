package com.Admin;

public class Admin {
    public String adminId;
    public String username;
    public String password;
    public String gender;
    public String gmail;

    public Admin(String adminId, String username, String password, String gender, String gmail) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.gmail = gmail;
    }

    @Override
    public String toString() {
        return adminId + ";" +
        username + ";" +
        password + ";" +
        gender + ";" + 
        gmail + "\n";
    }
}
