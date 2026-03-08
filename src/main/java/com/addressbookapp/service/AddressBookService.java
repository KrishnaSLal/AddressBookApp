package com.addressbookapp.service;

import com.addressbookapp.model.ContactPerson;
import java.util.ArrayList;
import java.util.List;

public class AddressBookService {

    private List<ContactPerson> contactList = new ArrayList<>();

    public void addContact(ContactPerson contact) {
        contactList.add(contact);
    }

    public List<ContactPerson> getAllContacts() {
        return contactList;
    }

    public boolean editContact(String firstName, String lastName, ContactPerson updatedContact) {

        for (ContactPerson contact : contactList) {

            if (contact.getFirstName().equals(firstName) &&
                contact.getLastName().equals(lastName)) {

                contact.setAddress(updatedContact.getAddress());
                contact.setCity(updatedContact.getCity());
                contact.setState(updatedContact.getState());
                contact.setZip(updatedContact.getZip());
                contact.setPhoneNumber(updatedContact.getPhoneNumber());
                contact.setEmail(updatedContact.getEmail());

                return true;
            }
        }

        return false;
    }
}