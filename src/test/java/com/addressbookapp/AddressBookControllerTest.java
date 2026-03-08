package com.addressbookapp;

import com.addressbookapp.controller.AddressBookController;
import com.addressbookapp.model.ContactPerson;
import com.addressbookapp.service.AddressBookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddressBookController.class)
public class AddressBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressBookService service;

    private ObjectMapper mapper = new ObjectMapper();

    // Create AddressBook
    @Test
    public void givenAddressBookName_whenCreated_shouldReturnSuccess() throws Exception {

        mockMvc.perform(post("/addressbook/createBook")
                .param("bookName", "Family"))
                .andExpect(status().isOk())
                .andExpect(content().string("Address Book Created"));
    }

    // Add Contact
    @Test
    public void givenContact_whenAdded_shouldReturnSuccessMessage() throws Exception {

        ContactPerson contact = new ContactPerson(
                "AP","Sharma","Street1","Delhi","Delhi","110001","9876543210","ap@test.com"
        );

        mockMvc.perform(post("/addressbook/add")
                .param("bookName","Family")
                .contentType("application/json")
                .content(mapper.writeValueAsString(contact)))
                .andExpect(status().isOk())
                .andExpect(content().string("Contact Added Successfully"));
    }

    // View Contacts
    @Test
    public void givenBookName_whenContactsRequested_shouldReturnList() throws Exception {

        when(service.getContacts("Family")).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/addressbook/contacts")
                .param("bookName","Family"))
                .andExpect(status().isOk());
    }

    // Delete Contact
    @Test
    public void givenContact_whenDeleted_shouldReturnMessage() throws Exception {

        when(service.deleteContact("Family","AP","Sharma")).thenReturn(true);

        mockMvc.perform(delete("/addressbook/delete")
                .param("bookName","Family")
                .param("firstName","AP")
                .param("lastName","Sharma"))
                .andExpect(status().isOk())
                .andExpect(content().string("Contact Deleted"));
    }
}