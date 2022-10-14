package com.example.desktopapp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Users {

    public SimpleIntegerProperty id;
    public SimpleStringProperty name;
    public SimpleIntegerProperty phone;
    public SimpleIntegerProperty age;
    public Users(Integer id, String name, Integer phone,Integer age) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleIntegerProperty(phone);
        this.age= new SimpleIntegerProperty(age);


    }

    public String getName(){
        return name.get();
    }

    public Integer getId(){
        return id.get();
    }

    public Integer getPhone(){
        return phone.get();
    }

    public Integer getAge(){
        return age.get();
    }

    public void setName(String name){
        this.name = new SimpleStringProperty(name);
    }

    public void setId(Integer id){
        this.id = new SimpleIntegerProperty(id);
    }

    public void setPhone(Integer phone){
        this.phone = new SimpleIntegerProperty(phone);
    }

    public void setAge(Integer age){
        this.age= new SimpleIntegerProperty(age);
    }
}
