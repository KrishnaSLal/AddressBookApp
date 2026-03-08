package com.addressbookapp;

import com.addressbookapp.controller.AddressBookController;
import com.addressbookapp.dto.ContactDTO;
import com.addressbookapp.model.ContactPerson;
import com.addressbookapp.service.AddressBookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.*;


@WebMvcTest(AddressBookController.class)
public class AddressBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressBookService service;

    ObjectMapper mapper = new ObjectMapper();

    // Create AddressBook
    @Test
    void givenAddressBookName_whenCreated_shouldReturnSuccess() throws Exception {

        mockMvc.perform(post("/addressbook/createBook")
                .param("bookName","Family"))
                .andExpect(status().isOk())
                .andExpect(content().string("Address Book Created"));
    }

    // Add Contact
    @Test
    void givenContact_whenAdded_shouldReturnSuccessMessage() throws Exception {

        ContactPerson contact = new ContactPerson(
                "AP","Sharma","Street1",
                "Delhi","Delhi","110001",
                "9876543210","ap@test.com"
        );

        mockMvc.perform(post("/addressbook/add")
                .param("bookName","Family")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(contact)))
                .andExpect(status().isOk())
                .andExpect(content().string("Contact Added Successfully"));
    }

    // View Contacts
    @Test
    void givenBookName_whenContactsRequested_shouldReturnList() throws Exception {

        when(service.getContacts("Family")).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/addressbook/contacts")
                .param("bookName","Family"))
                .andExpect(status().isOk());
    }

    // Delete Contact
    @Test
    void givenContact_whenDeleted_shouldReturnMessage() throws Exception {

        when(service.deleteContact("Family","AP","Sharma")).thenReturn(true);

        mockMvc.perform(delete("/addressbook/delete")
                .param("bookName","Family")
                .param("firstName","AP")
                .param("lastName","Sharma"))
                .andExpect(status().isOk())
                .andExpect(content().string("Contact Deleted"));
    }


    // Get All Contacts
    @Test
    void givenContacts_whenRequested_shouldReturnList() throws Exception {

        ContactPerson p1 = new ContactPerson(
                "John","Doe","Street1",
                "Delhi","Delhi","110001",
                "9999999999","john@test.com"
        );

        ContactPerson p2 = new ContactPerson(
                "Jane","Smith","Street2",
                "Mumbai","MH","400001",
                "8888888888","jane@test.com"
        );

        when(service.getAllContacts()).thenReturn(Arrays.asList(p1,p2));

        mockMvc.perform(get("/addressbook/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    // Delete by ID
    @Test
    void givenContactId_whenDeleted_shouldReturnSuccessMessage() throws Exception {

        doNothing().when(service).deleteContact(1L);

        mockMvc.perform(delete("/addressbook/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Contact deleted successfully"));
    }
 // UC19 Test – Update Contact
    @Test
    void givenContact_whenUpdated_shouldReturnUpdatedContact() throws Exception {

        ContactDTO dto = new ContactDTO();
        dto.setFirstName("Rahul");
        dto.setLastName("Sharma");

        ContactPerson updated = new ContactPerson(
                "Rahul","Sharma","Street1",
                "Mumbai","MH","400001",
                "9876543210","rahul@test.com"
        );

        when(service.updateContact(Mockito.eq(1L), Mockito.any(ContactDTO.class)))
        .thenReturn(updated);

        mockMvc.perform(put("/addressbook/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Rahul"));
    }
}