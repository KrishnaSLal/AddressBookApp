package com.addressbookapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.addressbookapp.model.ContactPerson;

@Service
public class AddressBookService {

    private List<ContactPerson> contacts = new ArrayList<>();

    public String addContact(ContactPerson contact) {
        contacts.add(contact);
        return "Contact Added Successfully";
    }

    public List<ContactPerson> getContacts() {
        return contacts;
    }
}