package com.example.vinylrecordstore.models;

public class User {
    private int id;
    private String email;
    private String name;
    private String city;
    private String address;
    private Integer phone;
    public User(int id, String email, String name, String city, String address, int phone){
        this.id = id;
        this.email = email;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFullName() {
        return name;
    }
    public void setFullName(String name) {
        this.name = name;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
}
