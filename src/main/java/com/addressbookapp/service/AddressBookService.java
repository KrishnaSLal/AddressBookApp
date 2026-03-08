package com.addressbookapp.service;

import com.addressbookapp.model.ContactPerson;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class AddressBookService {

    private Map<String, List<ContactPerson>> addressBooks = new HashMap<>();


    // UC6 - Create new Address Book
    public void createAddressBook(String bookName) {
        addressBooks.putIfAbsent(bookName, new ArrayList<>());
    }


    // Add contact to specific Address Book
    public boolean addContact(String bookName, ContactPerson contact) {

        addressBooks.putIfAbsent(bookName, new ArrayList<>());

        List<ContactPerson> contacts = addressBooks.get(bookName);

        for (ContactPerson c : contacts) {

            if (c.getFirstName().equals(contact.getFirstName()) &&
                c.getLastName().equals(contact.getLastName())) {

                return false; // duplicate found
            }
        }

        contacts.add(contact);
        return true;
    }


    // View contacts from a specific Address Book
    public List<ContactPerson> getContacts(String bookName) {

        return addressBooks.getOrDefault(bookName, new ArrayList<>());
    }


    // Edit contact
    public boolean editContact(String bookName, String firstName, String lastName, ContactPerson updatedContact) {

        List<ContactPerson> contacts = addressBooks.get(bookName);

        if (contacts == null) return false;

        for (ContactPerson contact : contacts) {

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


    // Delete contact
    public boolean deleteContact(String bookName, String firstName, String lastName) {

        List<ContactPerson> contacts = addressBooks.get(bookName);

        if (contacts == null) return false;

        return contacts.removeIf(contact ->
                contact.getFirstName().equals(firstName) &&
                contact.getLastName().equals(lastName));
    }
    

}