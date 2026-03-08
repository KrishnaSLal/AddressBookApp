package com.addressbookapp.controller;

import com.addressbookapp.model.ContactPerson;
import com.addressbookapp.service.AddressBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private AddressBookService service = new AddressBookService();

    // UC2 - Add Contact
    @PostMapping("/add")
    public String addContact(@RequestBody ContactPerson contact) {
        service.addContact(contact);
        return "Contact Added Successfully";
    }

    // View Contacts
    @GetMapping("/contacts")
    public List<ContactPerson> getContacts() {
        return service.getAllContacts();
    }

    // UC3 - Edit Contact
    @PutMapping("/edit")
    public String editContact(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestBody ContactPerson updatedContact) {

        boolean updated = service.editContact(firstName, lastName, updatedContact);

        if (updated) {
            return "Contact Updated Successfully";
        } else {
            return "Contact Not Found";
        }
    }
    
    @DeleteMapping("/delete")
    public String deleteContact(@RequestParam String firstName,
                                @RequestParam String lastName) {

        boolean deleted = service.deleteContact(firstName, lastName);

        if (deleted) {
            return "Contact Deleted Successfully";
        } else {
            return "Contact Not Found";
        }
    }
    
    @GetMapping("/contacts")
    public List<ContactPerson> getAllContacts() {
        return service.getAllContacts();
    }
}