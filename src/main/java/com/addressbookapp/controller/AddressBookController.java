package com.addressbookapp.controller;

import com.addressbookapp.dto.ContactDTO;
import com.addressbookapp.model.ContactPerson;
import com.addressbookapp.service.AddressBookService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    
    private static final Logger log =
            LoggerFactory.getLogger(AddressBookController.class);

    private final AddressBookService service;

    public AddressBookController(AddressBookService service) {
        this.service = service;
    }

    // Create Address Book
    @PostMapping("/createBook")
    public String createAddressBook(@RequestParam String bookName) {
        service.createAddressBook(bookName);
        return "Address Book Created";
    }

    // Add Contact
    @PostMapping("/add")
    public String addContact(@RequestParam String bookName,
                             @RequestBody ContactPerson person) {

        log.info("Request received to add contact in book: {}", bookName);
        service.addContact(bookName, person);
        log.info("Contact added successfully");

        return "Contact Added Successfully";
    }

    // View Contacts by Book
    @GetMapping("/contacts")
    public List<ContactPerson> getContacts(@RequestParam String bookName) {
        return service.getContacts(bookName);
    }

    // Delete Contact by Name
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

    // Save contact (DB)
    @PostMapping("/save")
    public ContactPerson saveContact(@RequestBody ContactPerson person) {
        return service.saveContact(person);
    }

    // Get all contacts
    @GetMapping("/all")
    public List<ContactPerson> getAllContacts() {
        return service.getAllContacts();
    }

    // Delete by ID
    @DeleteMapping("/delete/{id}")
    public String deleteContactById(@PathVariable Long id) {
        service.deleteContact(id);
        return "Contact deleted successfully";
    }
    
    // Update contact
    @PutMapping("/update/{id}")
    public ContactPerson updateContact(
            @PathVariable Long id,
            @Valid @RequestBody ContactDTO dto){

        return service.updateContact(id, dto);
    }
}