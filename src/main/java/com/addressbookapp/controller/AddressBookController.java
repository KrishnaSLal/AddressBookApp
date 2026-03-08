package com.addressbookapp.controller;

import com.addressbookapp.dto.ContactDTO;
import com.addressbookapp.model.ContactPerson;
import com.addressbookapp.service.AddressBookService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

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

        service.addContact(bookName, person);
        return "Contact Added Successfully";
    }

    // View Contacts
    @GetMapping("/contacts")
    public List<ContactPerson> getContacts(@RequestParam String bookName) {
        return service.getContacts(bookName);
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
    
    @PutMapping("/update/{id}")
    public ContactPerson updateContact(
            @PathVariable Long id,
            @Valid @RequestBody ContactDTO dto){

        return service.updateContact(id, dto);
    }
}