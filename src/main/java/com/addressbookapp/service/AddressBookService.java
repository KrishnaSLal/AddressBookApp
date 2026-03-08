package com.addressbookapp.service;

import com.addressbookapp.model.ContactPerson;
import java.util.*;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    private Map<String, List<ContactPerson>> addressBooks = new HashMap<>();
    private Map<String, List<ContactPerson>> cityMap = new HashMap<>();
    private Map<String, List<ContactPerson>> stateMap = new HashMap<>();
    


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
                return false;
            }
        }

        contacts.add(contact);

        cityMap.putIfAbsent(contact.getCity(), new ArrayList<>());
        cityMap.get(contact.getCity()).add(contact);

        stateMap.putIfAbsent(contact.getState(), new ArrayList<>());
        stateMap.get(contact.getState()).add(contact);

        return true;
    }
    
    public List<ContactPerson> getPersonsByCity(String city) {
        return cityMap.getOrDefault(city, new ArrayList<>());
    }
    public List<ContactPerson> getPersonsByState(String state) {
        return stateMap.getOrDefault(state, new ArrayList<>());
    }
    


    // View contacts from a specific Address Book
    public List<ContactPerson> getContacts(String bookName) {

        return addressBooks.getOrDefault(bookName, new ArrayList<>());
    }
    
    public List<ContactPerson> searchByCity(String city) {

        return addressBooks.values()
                .stream()
                .flatMap(List::stream)
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .toList();
    }
    
    public List<ContactPerson> searchByState(String state) {

        return addressBooks.values()
                .stream()
                .flatMap(List::stream)
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .toList();
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
    
   //UC10
    
    public long getCountByCity(String city) {
        return cityMap.getOrDefault(city, new ArrayList<>()).size();
    }
    
    public long getCountByState(String state) {
        return stateMap.getOrDefault(state, new ArrayList<>()).size();
    }
    
    //UC11


    public List<ContactPerson> sortByName(String bookName) {

        List<ContactPerson> contacts = addressBooks.get(bookName);

        if (contacts == null) {
            return new ArrayList<>();
        }

        return contacts.stream()
                .sorted(Comparator.comparing(ContactPerson::getFirstName)
                .thenComparing(ContactPerson::getLastName))
                .collect(Collectors.toList());
    }

}