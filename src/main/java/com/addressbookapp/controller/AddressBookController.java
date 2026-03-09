package com.addressbookapp.controller;

import com.addressbookapp.dto.ContactDTO;
import com.addressbookapp.model.ContactPerson;
import com.addressbookapp.service.AddressBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
@Tag(name = "Address Book Controller", description = "APIs for managing address book contacts")
public class AddressBookController {

    private static final Logger log = LoggerFactory.getLogger(AddressBookController.class);

    private final AddressBookService service;

    public AddressBookController(AddressBookService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new address book")
    @PostMapping("/createBook")
    public String createAddressBook(@RequestParam String bookName) {
        service.createAddressBook(bookName);
        return "Address Book Created";
    }

    @Operation(summary = "Add a contact to an address book")
    @PostMapping("/add")
    public String addContact(@RequestParam String bookName,
                             @RequestBody ContactPerson person) {
        log.info("Request received to add contact in book: {}", bookName);
        service.addContact(bookName, person);
        log.info("Contact added successfully");
        return "Contact Added Successfully";
    }

    @Operation(summary = "Get contacts from a specific address book")
    @GetMapping("/contacts")
    public List<ContactPerson> getContacts(@RequestParam String bookName) {
        return service.getContacts(bookName);
    }

    @Operation(summary = "Delete a contact by first and last name")
    @DeleteMapping("/delete")
    public String deleteContact(@RequestParam String bookName,
                                @RequestParam String firstName,
                                @RequestParam String lastName) {
        boolean deleted = service.deleteContact(bookName, firstName, lastName);
        return deleted ? "Contact Deleted" : "Contact Not Found";
    }

    @Operation(summary = "Save contact to database")
    @PostMapping("/save")
    public ContactPerson saveContact(@RequestBody ContactPerson person) {
        return service.saveContact(person);
    }

    @Operation(summary = "Get all contacts from database")
    @GetMapping("/all")
    public List<ContactPerson> getAllContacts() {
        return service.getAllContacts();
    }

    @Operation(summary = "Delete a contact by id")
    @DeleteMapping("/delete/{id}")
    public String deleteContactById(@PathVariable Long id) {
        service.deleteContact(id);
        return "Contact deleted successfully";
    }

    @Operation(summary = "Update a contact by id")
    @PutMapping("/update/{id}")
    public ContactPerson updateContact(@PathVariable Long id,
                                       @Valid @RequestBody ContactDTO dto) {
        return service.updateContact(id, dto);
    }
}