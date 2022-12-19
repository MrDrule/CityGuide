package com.example.project1;

import javafx.beans.property.SimpleStringProperty;

public class OtherUsers {

    private final SimpleStringProperty Username;
    private final SimpleStringProperty Name;

    OtherUsers(String username,String name){
        this.Username= new SimpleStringProperty(username);
        this.Name=new SimpleStringProperty(name);
    }
    public String getUsername(){
        return Username.get();
    }

    public String getName(){
        return Name.get();
    }

    public void setUsername(String value){
        Username.set(value);
    }
    public void setName(String value){
        Name.set(value);
    }




}
