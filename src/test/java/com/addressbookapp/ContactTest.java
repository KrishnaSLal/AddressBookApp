package com.addressbookapp;

import com.addressbookapp.model.ContactPerson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    public void givenContactDetails_WhenObjectCreated_ShouldReturnCorrectValues() {

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

        assertEquals("Krishna", contact.getFirstName());
        assertEquals("Lal", contact.getLastName());
        assertEquals("123 Street", contact.getAddress());
        assertEquals("Bhopal", contact.getCity());
        assertEquals("MP", contact.getState());
        assertEquals("462001", contact.getZip());
        assertEquals("9876543210", contact.getPhoneNumber());
        assertEquals("krishna@gmail.com", contact.getEmail());
    }

    @Test
    public void givenContact_WhenSettersUsed_ShouldUpdateValues() {

        ContactPerson contact = new ContactPerson();

        contact.setFirstName("Krishna");
        contact.setLastName("Lal");
        contact.setAddress("123 Street");
        contact.setCity("Bhopal");
        contact.setState("MP");
        contact.setZip("462001");
        contact.setPhoneNumber("9876543210");
        contact.setEmail("krishna@gmail.com");

        assertEquals("Krishna", contact.getFirstName());
        assertEquals("Lal", contact.getLastName());
        assertEquals("123 Street", contact.getAddress());
        assertEquals("Bhopal", contact.getCity());
        assertEquals("MP", contact.getState());
        assertEquals("462001", contact.getZip());
        assertEquals("9876543210", contact.getPhoneNumber());
        assertEquals("krishna@gmail.com", contact.getEmail());
    }
    

    @Test
    public void givenEmptyConstructor_whenSettersUsed_shouldReturnCorrectValues() {

        ContactPerson contact = new ContactPerson();

        contact.setFirstName("John");
        contact.setLastName("Doe");
        contact.setCity("Delhi");

        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("Delhi", contact.getCity());
    }

    @Test
    public void givenContactDetails_whenObjectCreated_shouldReturnCorrectValues() {

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

        assertEquals("Krishna", contact.getFirstName());
        assertEquals("Lal", contact.getLastName());
        assertEquals("123 Street", contact.getAddress());
        assertEquals("Bhopal", contact.getCity());
        assertEquals("MP", contact.getState());
        assertEquals("462001", contact.getZip());
        assertEquals("9876543210", contact.getPhoneNumber());
        assertEquals("krishna@gmail.com", contact.getEmail());
    }

    @Test
    public void givenContact_whenUpdatingPhoneNumber_shouldReturnUpdatedValue() {

        ContactPerson contact = new ContactPerson();

        contact.setPhoneNumber("1111111111");
        contact.setPhoneNumber("9999999999");

        assertEquals("9999999999", contact.getPhoneNumber());
    }

    @Test
    public void givenContact_whenUpdatingEmail_shouldReturnUpdatedEmail() {

        ContactPerson contact = new ContactPerson();

        contact.setEmail("old@mail.com");
        contact.setEmail("new@mail.com");

        assertEquals("new@mail.com", contact.getEmail());
    }

    @Test
    public void givenContactWithNullValues_shouldHandleGracefully() {

        ContactPerson contact = new ContactPerson();

        assertNull(contact.getFirstName());
        assertNull(contact.getLastName());
        assertNull(contact.getCity());
    }

}
