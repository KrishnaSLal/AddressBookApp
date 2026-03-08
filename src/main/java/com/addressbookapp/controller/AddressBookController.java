package com.addressbookapp.controller;

import com.addressbookapp.model.ContactPerson;
import com.addressbookapp.service.AddressBookService;
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

    // Edit Contact
    @PutMapping("/edit")
    public String editContact(@RequestParam String bookName,
                              @RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestBody ContactPerson updatedContact) {

        boolean updated = service.editContact(bookName, firstName, lastName, updatedContact);

        return updated ? "Contact Updated" : "Contact Not Found";
    }

    // Delete Contact
    @DeleteMapping("/delete")
    public String deleteContact(@RequestParam String bookName,
                                @RequestParam String firstName,
                                @RequestParam String lastName) {

        boolean deleted = service.deleteContact(bookName, firstName, lastName);

        return deleted ? "Contact Deleted" : "Contact Not Found";
    }

    // Search by City
    @GetMapping("/search/city")
    public List<ContactPerson> searchByCity(@RequestParam String city) {
        return service.searchByCity(city);
    }

    // Search by State
    @GetMapping("/search/state")
    public List<ContactPerson> searchByState(@RequestParam String state) {
        return service.searchByState(state);
    }

    // View persons by city
    @GetMapping("/view/city")
    public List<ContactPerson> viewPersonsByCity(@RequestParam String city) {
        return service.getPersonsByCity(city);
    }

    // View persons by state
    @GetMapping("/view/state")
    public List<ContactPerson> viewPersonsByState(@RequestParam String state) {
        return service.getPersonsByState(state);
    }

    // Count by city
    @GetMapping("/count/city")
    public long countByCity(@RequestParam String city) {
        return service.getCountByCity(city);
    }

    // Count by state
    @GetMapping("/count/state")
    public long countByState(@RequestParam String state) {
        return service.getCountByState(state);
    }

    // Sort by Name
    @GetMapping("/sort/name")
    public List<ContactPerson> sortByName(@RequestParam String bookName) {
        return service.sortByName(bookName);
    }

    // Sort by City
    @GetMapping("/sort/city")
    public List<ContactPerson> sortByCity(@RequestParam String bookName) {
        return service.sortByCity(bookName);
    }

    // Sort by State
    @GetMapping("/sort/state")
    public List<ContactPerson> sortByState(@RequestParam String bookName) {
        return service.sortByState(bookName);
    }

    // Sort by Zip
    @GetMapping("/sort/zip")
    public List<ContactPerson> sortByZip(@RequestParam String bookName) {
        return service.sortByZip(bookName);
    }

    // Save JSON
    @PostMapping("/save")
    public String saveAddressBook() throws Exception {
        service.saveToFile();
        return "AddressBook saved to file";
    }

    // Load JSON
    @GetMapping("/load")
    public String loadAddressBook() throws Exception {
        service.loadFromFile();
        return "AddressBook loaded from file";
    }

    // Export CSV
    @PostMapping("/export-csv")
    public String exportCSV() throws Exception {
        service.writeToCSV();
        return "Contacts exported to CSV";
    }

    // Import CSV
    @GetMapping("/import-csv")
    public String importCSV() throws Exception {
        service.readFromCSV();
        return "Contacts loaded from CSV";
    }
}