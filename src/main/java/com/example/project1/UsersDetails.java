package com.example.project1;
import com.mysql.cj.conf.StringProperty;
import com.sun.jdi.Value;
import javafx.beans.property.SimpleStringProperty;

public class UsersDetails {
    private static UsersDetails instance;
    private String password;
    private String email;
    private String username;

    UsersDetails (String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public static UsersDetails getInstance(String username ,String password, String email){
        if (instance==null){
            instance = new UsersDetails(username,password,email);
        }
        return instance;
    }
    //getters
    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


}
