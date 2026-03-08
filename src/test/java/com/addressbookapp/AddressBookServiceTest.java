package com.addressbookapp;

import com.addressbookapp.model.ContactPerson;
import com.addressbookapp.service.AddressBookService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

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
    
    @Test
    public void givenDuplicateContact_whenAdded_shouldReturnFalse() {

        AddressBookService service = new AddressBookService();

        service.createAddressBook("Family");

        ContactPerson contact1 = new ContactPerson(
                "AP","Sharma","","","","","",""
        );

        ContactPerson contact2 = new ContactPerson(
                "AP","Sharma","","","","","",""
        );

        service.addContact("Family", contact1);

        boolean result = service.addContact("Family", contact2);

        assertFalse(result);
    }
    
    @Test
    public void givenCity_whenSearched_shouldReturnContacts() {

        AddressBookService service = new AddressBookService();

        service.createAddressBook("Family");

        ContactPerson contact = new ContactPerson(
                "AP","Sharma","","Mumbai","Maharashtra","","",""
        );

        service.addContact("Family", contact);

        List<ContactPerson> result = service.searchByCity("Mumbai");

        assertEquals(1, result.size());
    }
    
    @Test
    public void givenCity_whenViewed_shouldReturnPersons() {

        AddressBookService service = new AddressBookService();
        service.createAddressBook("Family");

        ContactPerson contact = new ContactPerson(
                "AP","Sharma","","Mumbai","Maharashtra","","",""
        );

        service.addContact("Family", contact);

        List<ContactPerson> result = service.getPersonsByCity("Mumbai");

        assertEquals(1, result.size());
    }
    
    @Test
    public void givenCity_whenCounted_shouldReturnCorrectCount() {

        AddressBookService service = new AddressBookService();
        service.createAddressBook("Family");

        ContactPerson c1 = new ContactPerson(
                "AP","Sharma","","Mumbai","Maharashtra","","",""
        );

        ContactPerson c2 = new ContactPerson(
                "Rahul","Verma","","Mumbai","Maharashtra","","",""
        );

        service.addContact("Family", c1);
        service.addContact("Family", c2);

        long count = service.getCountByCity("Mumbai");

        assertEquals(2, count);
    }
    
    @Test
    public void givenContacts_whenSortedByName_shouldReturnAlphabeticalOrder() {

        AddressBookService service = new AddressBookService();

        service.createAddressBook("Family");

        ContactPerson c1 = new ContactPerson("Rahul","Verma","","","","","","");
        ContactPerson c2 = new ContactPerson("AP","Sharma","","","","","","");
        ContactPerson c3 = new ContactPerson("Anita","Sharma","","","","","","");

        service.addContact("Family", c1);
        service.addContact("Family", c2);
        service.addContact("Family", c3);

        List<ContactPerson> sorted = service.sortByName("Family");

        assertEquals(3, sorted.size());
        assertEquals("AP", sorted.get(0).getFirstName());
        assertEquals("Anita", sorted.get(1).getFirstName());
        assertEquals("Rahul", sorted.get(2).getFirstName());
    }
    
    @Test
    public void givenContacts_whenSortedByCity_shouldReturnSortedList() {

        AddressBookService service = new AddressBookService();

        service.createAddressBook("Family");

        ContactPerson c1 = new ContactPerson("Rahul","Verma","","Delhi","Delhi","110001","","");
        ContactPerson c2 = new ContactPerson("AP","Sharma","","Mumbai","Maharashtra","400001","","");
        ContactPerson c3 = new ContactPerson("Anita","Singh","","Bangalore","Karnataka","560001","","");

        service.addContact("Family", c1);
        service.addContact("Family", c2);
        service.addContact("Family", c3);

        List<ContactPerson> sorted = service.sortByCity("Family");

        assertEquals("Bangalore", sorted.get(0).getCity());
    }
}