package com.qooke.contacts.Model;

import java.io.Serializable;

public class Contact implements Serializable {

    public String name;
    public String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;

    }
}
