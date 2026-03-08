package com.addressbookapp;

import com.addressbookapp.model.ContactPerson;
import com.addressbookapp.service.AddressBookService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddressBookServiceTest {

    @Test
    public void givenContact_whenAdded_shouldBePresentInAddressBook() {

        AddressBookService service = new AddressBookService();

        service.createAddressBook("Family");

        ContactPerson contact = new ContactPerson(
                "AP","Sharma","","","","","",""
        );

        service.addContact("Family", contact);

        assertEquals(1, service.getContacts("Family").size());
    }

    @Test
    public void givenMultipleContacts_whenAdded_shouldReturnCorrectCount() {

        AddressBookService service = new AddressBookService();

        service.createAddressBook("Friends");

        ContactPerson c1 = new ContactPerson("AP","Sharma","","","","","","");
        ContactPerson c2 = new ContactPerson("Rahul","Verma","","","","","","");

        service.addContact("Friends", c1);
        service.addContact("Friends", c2);

        assertEquals(2, service.getContacts("Friends").size());
    }

    @Test
    public void givenExistingContact_whenDeleted_shouldReduceListSize() {

        AddressBookService service = new AddressBookService();

        service.createAddressBook("Family");

        ContactPerson contact = new ContactPerson("AP","Sharma","","","","","","");

        service.addContact("Family", contact);

        service.deleteContact("Family","AP","Sharma");

        assertEquals(0, service.getContacts("Family").size());
    }

    @Test
    public void givenNonExistingContact_whenDeleted_shouldReturnFalse() {

        AddressBookService service = new AddressBookService();

        service.createAddressBook("Friends");

        boolean result = service.deleteContact("Friends","Rahul","Verma");

        assertFalse(result);
    }
}