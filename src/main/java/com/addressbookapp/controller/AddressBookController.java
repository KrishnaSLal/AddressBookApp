package com.addressbookapp.controller;

import com.addressbookapp.model.ContactPerson;
import com.addressbookapp.service.AddressBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    // Create Address Book
    @PostMapping("/createBook")
    public String createBook(@RequestParam String bookName) {

        service.createAddressBook(bookName);

        return "Address Book Created";
    }

    // Add Contact
    @PostMapping("/add")
    public String addContact(@RequestParam String bookName,
                             @RequestBody ContactPerson contact) {

        boolean added = service.addContact(bookName, contact);

        if (added) {
            return "Contact Added Successfully";
        } else {
            return "Duplicate Contact Found";
        }
    }

    // View Contacts
    @GetMapping("/contacts")
    public List<ContactPerson> getContacts(@RequestParam String bookName) {

        return service.getContacts(bookName);
    }
    
    @GetMapping("/search/city")
    public List<ContactPerson> searchByCity(@RequestParam String city) {

        return service.searchByCity(city);
    }
    
    @GetMapping("/search/state")
    public List<ContactPerson> searchByState(@RequestParam String state) {

        return service.searchByState(state);
    }
    
    @GetMapping("/view/city")
    public List<ContactPerson> viewPersonsByCity(@RequestParam String city) {
        return service.getPersonsByCity(city);
    }
    @GetMapping("/view/state")
    public List<ContactPerson> viewPersonsByState(@RequestParam String state) {
        return service.getPersonsByState(state);
    }
    
    @GetMapping("/count/city")
    public long countByCity(@RequestParam String city) {
        return service.getCountByCity(city);
    }
    @GetMapping("/count/state")
    public long countByState(@RequestParam String state) {
        return service.getCountByState(state);
    }
    
    @GetMapping("/sort/name")
    public List<ContactPerson> sortByName(@RequestParam String bookName) {

        return service.sortByName(bookName);
    }
    
    @GetMapping("/sort/city")
    public List<ContactPerson> sortByCity(@RequestParam String bookName) {
        return service.sortByCity(bookName);
    }
    @GetMapping("/sort/state")
    public List<ContactPerson> sortByState(@RequestParam String bookName) {
        return service.sortByState(bookName);
    }
    @GetMapping("/sort/zip")
    public List<ContactPerson> sortByZip(@RequestParam String bookName) {
        return service.sortByZip(bookName);
    }

    // Edit Contact
    @PutMapping("/edit")
    public String editContact(@RequestParam String bookName,
                              @RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestBody ContactPerson updatedContact) {

        boolean updated = service.editContact(bookName, firstName, lastName, updatedContact);

        if (updated) {
            return "Contact Updated";
        } else {
            return "Contact Not Found";
        }
    }

    // Delete Contact
    @DeleteMapping("/delete")
    public String deleteContact(@RequestParam String bookName,
                                @RequestParam String firstName,
                                @RequestParam String lastName) {

        boolean deleted = service.deleteContact(bookName, firstName, lastName);

        if (deleted) {
            return "Contact Deleted";
        } else {
            return "Contact Not Found";
        }
    }
}