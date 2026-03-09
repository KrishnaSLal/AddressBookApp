package com.addressbookapp;

import com.addressbookapp.model.ContactPerson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddressBookRestService {

    private List<ContactPerson> contactList;

    public AddressBookRestService() {
        this.contactList = new ArrayList<>();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;   // JSON Server default port
        RestAssured.basePath = "/contacts";
    }

    public List<ContactPerson> getContactList() {
        return contactList;
    }

    public void setContactList(List<ContactPerson> contactList) {
        this.contactList = contactList;
    }

    public void loadContactsFromJsonServer() {
        ContactPerson[] contacts = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .as(ContactPerson[].class);

        this.contactList = new ArrayList<>(Arrays.asList(contacts));
    }

    public ContactPerson addContactToJsonServer(ContactPerson contact) {
        ContactPerson addedContact = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(contact)
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .as(ContactPerson.class);

        contactList.add(addedContact);
        return addedContact;
    }

    public void addMultipleContactsToJsonServer(List<ContactPerson> contacts) {
        contacts.forEach(this::addContactToJsonServer);
    }

    public int countContactsInMemory() {
        return contactList.size();
    }
}