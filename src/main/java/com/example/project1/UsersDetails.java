package com.example.project1;

public class UsersDetails {
    public static int id;
    private static UsersDetails instance;
    private String password;
    private String email;
    private String username;
    private String name;

    public UsersDetails (String username, String password, String email,int id,String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        UsersDetails.id =id;
        this.name=name;
    }


    public static UsersDetails getInstance(String username , String password, String email, int id,String name){
        if (instance==null){
            instance = new UsersDetails(username,password,email,id,name);
        }
        return instance;
    }
    public void cleanUserDetails() {
        username = "";// or null
        password = "";// or null
        email="";
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
    public String getName(){
        return name;
    }
    public  int getId(){
        return id;
    }


}