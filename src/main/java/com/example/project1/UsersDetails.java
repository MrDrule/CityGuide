package com.example.project1;
import com.mysql.cj.conf.StringProperty;
import com.sun.jdi.Value;
import javafx.beans.property.SimpleStringProperty;

public class UsersDetails {
    private final SimpleStringProperty password;
    private final SimpleStringProperty email;
    private final SimpleStringProperty username;

    public UsersDetails(String username, String password, String email) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.email = new SimpleStringProperty(email);
    }

    //getters
    public String getUsername() {
        return username.get();
    }


    public String getPassword() {
        return password.get();
    }

    public String getEmail() {
        return email.get();
    }

    //setters
    public void setUsername(String value){
        username.set(value);
    }
    public void setPassword(String value){
        password.set(value);
    }
    public void setEmail(String value){
        email.set(value);
    }

    //Property Values

    public SimpleStringProperty usernameProperty(){
        return username;
    }
    public SimpleStringProperty passwordProperty(){
        return password;
    }
    public SimpleStringProperty emailProperty(){
        return email;
    }




}
