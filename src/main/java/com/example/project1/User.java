package com.example.project1;

public class User {
    public static String username;
    public static String password;
    public static String name;
    public static String email;


    public User (String un, String pass, String name,String mail){
        this.username=un;
        this.password=pass;
        this.name=name;
        this.email=mail;
    }

    public User(){
        this.username=null;
        this.password=null;
        this.name=null;
        this.email=null;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }




}
