package com.example.fwatery.Models;

public class A3tal {
    String Id ;
    String Name;
    String Phone ;
    String Address ;
    String Note;
    String date;
    Boolean State ;

    public A3tal() {
        Note ="  ";
    }

    public A3tal(String name, String phone, String address) {
        Name = name;
        Phone = phone;
        Address = address;
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
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getState() {
        return State;
    }

    public void setState(Boolean state) {
        State = state;
    }
}
