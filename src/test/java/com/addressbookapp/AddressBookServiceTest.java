package com.addressbookapp;

import com.addressbookapp.model.ContactPerson;
import com.addressbookapp.service.AddressBookService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddressBookServiceTest {

    @Test
    public void givenContact_whenAdded_shouldReturnContactListSizeOne() {

        AddressBookService service = new AddressBookService();

        ContactPerson contact = new ContactPerson(
                "Krishna",
                "Lal",
                "123 Street",
                "Bhopal",
                "MP",
                "462001",
                "9876543210",
                "krishna@gmail.com"
        );

        service.addContact(contact);

        assertEquals(1, service.getContacts().size());
    }

    @Test
    public void givenMultipleContacts_whenAdded_shouldReturnCorrectSize() {

        AddressBookService service = new AddressBookService();

        ContactPerson c1 = new ContactPerson("A", "B", "", "", "", "", "", "");
        ContactPerson c2 = new ContactPerson("C", "D", "", "", "", "", "", "");

        service.addContact(c1);
        service.addContact(c2);

        assertEquals(2, service.getContacts().size());
    }

    @Test
    public void givenContact_whenAdded_shouldBePresentInAddressBook() {

        AddressBookService service = new AddressBookService();

        ContactPerson contact = new ContactPerson("Krishna", "Lal", "", "", "", "", "", "");

        service.addContact(contact);

        assertTrue(service.getContacts().contains(contact));
    }
}