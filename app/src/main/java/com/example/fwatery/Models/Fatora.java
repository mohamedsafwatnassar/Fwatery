package com.example.fwatery.Models;

public class Fatora {
    String Id ;
    String Name;
    String Phone ;
    String Address ;
    String Note;
    String Date;
    float Price ;
    float ExtraPackage ;
    Boolean State ;

    public Fatora() {
    }

    public Fatora(String name, String phone, String date, float price) {
        Name = name;
        Phone = phone;
        Date = date;
        Price = price;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public float getExtraPackage() {
        return ExtraPackage;
    }

    public void setExtraPackage(float extraPackage) {
        ExtraPackage = extraPackage;
    }

    public Boolean getState() {
        return State;
    }

    public void setState(Boolean state) {
        State = state;
    }
}
