package com.addressbookapp;

import com.addressbookapp.model.ContactPerson;
import com.addressbookapp.service.AddressBookService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AddressBookRestServiceTest {

    @Autowired
    private AddressBookService addressBookService;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
        RestAssured.basePath = "/contacts";
    }

    @Test
    void givenContact_WhenUpdatedInJsonServer_ShouldSyncWithMemory() {
        ContactPerson newContact = new ContactPerson(
                "Rahul", "Sharma", "Street1",
                "Mumbai", "MH", "400001",
                "9876543210", "rahul@test.com"
        );

        ContactPerson addedContact = given()
                .contentType(ContentType.JSON)
                .body(newContact)
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .as(ContactPerson.class);

        addressBookService.addContact(addedContact);

        addedContact.setCity("Pune");
        addedContact.setState("Maharashtra");
        addedContact.setPhoneNumber("9998887776");

        given()
                .contentType(ContentType.JSON)
                .body(addedContact)
                .when()
                .put("/{id}", addedContact.getId())
                .then()
                .statusCode(200)
                .body("city", equalTo("Pune"))
                .body("state", equalTo("Maharashtra"))
                .body("phoneNumber", equalTo("9998887776"));

        addressBookService.updateContact(addedContact);

        ContactPerson updatedInMemory = addressBookService.getContactById(addedContact.getId());

        assertNotNull(updatedInMemory);
        assertEquals("Pune", updatedInMemory.getCity());
        assertEquals("Maharashtra", updatedInMemory.getState());
        assertEquals("9998887776", updatedInMemory.getPhoneNumber());
    }
    
    @Test
    void given3Contacts_WhenAddedUsingThreads_ShouldMatchEntryCount() {
        AddressBookService service = new AddressBookService();

        List<ContactPerson> contacts = Arrays.asList(
                new ContactPerson("Rahul", "Sharma", "Street1", "Mumbai", "MH", "400001", "9876543210", "rahul@test.com"),
                new ContactPerson("Anita", "Singh", "Street2", "Delhi", "Delhi", "110001", "9999999999", "anita@test.com"),
                new ContactPerson("Arun", "Das", "Street3", "Kochi", "Kerala", "682001", "8888888888", "arun@test.com")
        );

        service.addContactsToAddressBookWithThreads(contacts);

        Assertions.assertEquals(3, service.countEntries());
        Assertions.assertTrue(service.isContactAdded("Rahul"));
        Assertions.assertTrue(service.isContactAdded("Anita"));
        Assertions.assertTrue(service.isContactAdded("Arun"));
    }
}
