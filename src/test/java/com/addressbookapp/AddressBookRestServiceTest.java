package com.addressbookapp;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressBookRestServiceTest {

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
        RestAssured.basePath = "/contacts";
    }

    @Test
    void givenMultipleContacts_whenAddedToJsonServer_shouldIncreaseCount() {

        int beforeCount =
                given()
                        .contentType(ContentType.JSON)
                .when()
                        .get()
                .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getList("$")
                        .size();

        String contact1 = "{"
                + "\"firstName\":\"Rahul\","
                + "\"lastName\":\"Sharma\","
                + "\"address\":\"Street1\","
                + "\"city\":\"Mumbai\","
                + "\"state\":\"MH\","
                + "\"zip\":\"400001\","
                + "\"phoneNumber\":\"9876543210\","
                + "\"email\":\"rahul@test.com\""
                + "}";

        String contact2 = "{"
                + "\"firstName\":\"Anita\","
                + "\"lastName\":\"Singh\","
                + "\"address\":\"Street2\","
                + "\"city\":\"Delhi\","
                + "\"state\":\"Delhi\","
                + "\"zip\":\"110001\","
                + "\"phoneNumber\":\"9999999999\","
                + "\"email\":\"anita@test.com\""
                + "}";

        given()
                .contentType(ContentType.JSON)
                .body(contact1)
        .when()
                .post()
        .then()
                .statusCode(201)
                .body("firstName", equalTo("Rahul"));

        given()
                .contentType(ContentType.JSON)
                .body(contact2)
        .when()
                .post()
        .then()
                .statusCode(201)
                .body("firstName", equalTo("Anita"));

        int afterCount =
                given()
                        .contentType(ContentType.JSON)
                .when()
                        .get()
                .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getList("$")
                        .size();

        assertEquals(beforeCount + 2, afterCount);
    }
}